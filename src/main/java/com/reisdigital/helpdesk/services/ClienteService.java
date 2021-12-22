package com.reisdigital.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reisdigital.helpdesk.domain.Pessoa;
import com.reisdigital.helpdesk.domain.Cliente;
import com.reisdigital.helpdesk.domain.dtos.ClienteDTO;
import com.reisdigital.helpdesk.repositories.PessoaRepository;
import com.reisdigital.helpdesk.repositories.ClienteRepository;
import com.reisdigital.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.reisdigital.helpdesk.services.exceptions.ObjectNotFoundException;




@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id){
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		
		Cliente obj = findById(id);
		validaPorCpfEEmail(objDTO);
		obj = new Cliente(objDTO);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de servió e nõ pode ser deletado");
		}
		repository.deleteById(id);
	}
	
	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
	}


}
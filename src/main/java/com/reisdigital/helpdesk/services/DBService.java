package com.reisdigital.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reisdigital.helpdesk.Tecnico;
import com.reisdigital.helpdesk.domain.Chamado;
import com.reisdigital.helpdesk.domain.Cliente;
import com.reisdigital.helpdesk.domain.enums.Perfil;
import com.reisdigital.helpdesk.domain.enums.Prioridade;
import com.reisdigital.helpdesk.domain.enums.Status;
import com.reisdigital.helpdesk.repositories.ChamadoRepository;
import com.reisdigital.helpdesk.repositories.ClienteRepository;
import com.reisdigital.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "André", "63653230268", "andre@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "torvalds@gmail.com", "80527954780", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}

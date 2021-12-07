package com.reisdigital.helpdesk;

import java.util.ArrayList;
import java.util.List;

import com.reisdigital.helpdesk.domain.Chamado;
import com.reisdigital.helpdesk.domain.Pessoa;

public class Tecnico extends Pessoa{
	
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
	

}

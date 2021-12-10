package com.reisdigital.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reisdigital.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}

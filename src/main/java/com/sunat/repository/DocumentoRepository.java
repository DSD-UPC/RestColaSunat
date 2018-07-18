package com.sunat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.sunat.entidad.UsuariosRuc;




public interface DocumentoRepository extends CrudRepository<UsuariosRuc, Long>{
	
	List<UsuariosRuc> findByNroruc (String nroruc);
	
	
}






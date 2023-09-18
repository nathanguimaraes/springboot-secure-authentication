package com.projectfinal.entitycrud.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projectfinal.entitycrud.modelo.Papel;
import com.projectfinal.entitycrud.repositorio.PapelRepository;

@Component
public class CarregadoraDados implements CommandLineRunner {

	@Autowired
	private PapelRepository papelRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] papeis = {"ADMIN", "USER", "RELATOR"};
		
		for (String papelString: papeis) {
			Papel papel = papelRepository.findByPapel(papelString);
			if (papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
		}				
	}

}


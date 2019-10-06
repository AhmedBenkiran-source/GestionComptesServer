package org.controllers;

import java.util.List;

import org.entities.Compte;
import org.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class CompteController {
	
	@Autowired
	private CompteRepository compteRepository;

	@GetMapping("/comptes")
	public List<Compte> getComptes() {
		return compteRepository.findAll();
	}

	@GetMapping("/compte/{id}")
	public Compte getCompte(@PathVariable Long id) {
		return compteRepository.findOne(id);
	}

	@DeleteMapping("/compte/{id}")
	public boolean deleteCompte(@PathVariable Long id) {
		compteRepository.delete(id);
		return true;
	}

	@PutMapping("/compte")
	public Compte updateCompte(@RequestBody Compte compte) {
		return compteRepository.save(compte);
	}

	@PostMapping("/compte")
	public Compte createCompte(@RequestBody Compte compte) {
		System.out.println("ooooooooooooooooooo" + compte.getUser_id());
		return compteRepository.save(compte);
	}

	
}

package org.controllers;

import java.util.Date;
import java.util.List;

import org.entities.Carnet;
import org.repositories.CarnetRepository;
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
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class CarnetController {

	@Autowired
	private CarnetRepository carnetRepository;

	@GetMapping("/carnets")
	public List<Carnet> getCarnets() {
		return carnetRepository.findAll();
	}

	@GetMapping("/carnet/{id}")
	public Carnet getCarnet(@PathVariable Long id) {
		return carnetRepository.findOne(id);
	}

	@DeleteMapping("/carnet/{id}")
	public boolean deleteCarnet(@PathVariable Long id) {
		carnetRepository.delete(id);
		return true;
	}

	@PutMapping("/carnet")
	public Carnet updateCarnet(@RequestBody Carnet carnet) {
		return carnetRepository.save(carnet);
	}

	@PostMapping("/carnet")
	public Carnet createCarnet(@RequestBody Carnet carnet) {
		carnet.setDate(new Date());
		return carnetRepository.save(carnet);
	}

}

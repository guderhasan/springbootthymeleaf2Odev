package com.bilgeadam.SpringBootRestHibernate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgretmenRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;
import com.bilgeadam.SpringBootRestHibernate.service.OgretmenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ogretmen")
@RestControllerAdvice(basePackageClasses = OgretmenRepository.class)
@AllArgsConstructor
public class OgretmenController {
	private OgretmenService ogretmenService;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> getAll() {
		return ogretmenService.getAll();
	}

	@GetMapping(value = { "/getById/{id}", "/findById/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getById(@PathVariable(value = "id") Long id) {
		return ogretmenService.getById(id);
	}

	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getBy(@RequestParam(value = "id") Long id) {
		return ogretmenService.getById(id);
	}

	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findByName(@RequestParam(value = "name") String name) {
		return ogretmenService.findByName(name);
	}

	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogretmen getByWithHeader(@RequestHeader(value = "id") Long id) {
		return ogretmenService.getById(id);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen) {

		if (ogretmenService.save(ogretmen)) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Başarı ile kaydedildi\n http://localhost:8080/ogretmen/getById/" + ogretmen.getID()
							+ "\n http://localhost:8080/ogretmen/deleteById/" + ogretmen.getID());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (ogretmenService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	@GetMapping(value = "/findByOgretmenName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findByOgretmenName(@RequestParam(value = "name") String name) {
		return ogretmenService.findByOgretmenNAME(name);

	}

	@GetMapping(value = "/findAllByNAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findAllByNAMELike(@RequestParam(value = "name") String name) {
		return ogretmenService.findAllByNAMELike(name);

	}

	@GetMapping(value = "/findAllByNAMELike2", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findAllByNAMELike(@RequestParam(value = "expression") String expression,
			@RequestParam(value = "sort") org.springframework.data.domain.Sort sort) {
		return ogretmenService.findAllByNAMELike(expression, sort);

	}

	@GetMapping(value = "/findAllByNAMELikeOrderByIDDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogretmen> findAllByNAMELikeOrderByIDDesc(@RequestParam(value = "name") String name) {
		return ogretmenService.findAllByNAMELikeOrderByIDDesc(name);

	}

}

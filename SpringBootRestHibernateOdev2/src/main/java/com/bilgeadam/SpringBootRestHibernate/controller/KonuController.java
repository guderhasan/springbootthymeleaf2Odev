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

import com.bilgeadam.SpringBootRestHibernate.jparepository.KonuRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Konu;
import com.bilgeadam.SpringBootRestHibernate.service.KonuService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "konu")
@RestControllerAdvice(basePackageClasses = KonuRepository.class)
@AllArgsConstructor
public class KonuController {
	private KonuService konuService;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> getAll() {
		return konuService.getAll();
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getById(@PathVariable(value = "id") Long id) {
		return konuService.getById(id);
	}

	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getBy(@RequestParam(value = "id") Long id) {
		return konuService.getById(id);
	}

	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findByName(@RequestParam(value = "name") String name) {
		return konuService.findByName(name);
	}

	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Konu getByWithHeader(@RequestHeader(value = "id") Long id) {
		return konuService.getById(id);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Konu konu) {
		if (konuService.save(konu)) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Başarı ile kaydedildi\n http://localhost:8080/konu/getById/" + konu.getID()
							+ "\n http://localhost:8080/konu/deleteById/" + konu.getID());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (konuService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	@GetMapping(value = "/findByKonuName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findByKonuName(@RequestParam(value = "name") String name) {
		return konuService.findByKonuNAME(name);

	}

	@GetMapping(value = "/findAllByNAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findAllByNAMELike(@RequestParam(value = "name") String name) {
		return konuService.findAllByNAMELike(name);

	}

	@GetMapping(value = "/findAllByNAMELike2", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findAllByNAMELike(@RequestParam(value = "expression") String expression,
			@RequestParam(value = "sort") org.springframework.data.domain.Sort sort) {
		return konuService.findAllByNAMELike(expression, sort);

	}

	@GetMapping(value = "/findAllByNAMELikeOrderByIDDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Konu> findAllByNAMELikeOrderByIDDesc(@RequestParam(value = "name") String name) {
		return konuService.findAllByNAMELikeOrderByIDDesc(name);

	}

}

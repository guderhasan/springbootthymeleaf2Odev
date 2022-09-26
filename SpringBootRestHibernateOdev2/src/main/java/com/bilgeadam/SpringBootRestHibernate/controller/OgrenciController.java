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

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgrenciRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;
import com.bilgeadam.SpringBootRestHibernate.service.OgrenciService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ogrenci")
@RestControllerAdvice(basePackageClasses = OgrenciRepository.class)
@AllArgsConstructor
public class OgrenciController {
	private OgrenciService ogrenciService;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> getAll() {
		return ogrenciService.getAll();
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getById(@PathVariable(value = "id") Long id) {
		return ogrenciService.getById(id);
	}

	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getBy(@RequestParam(value = "id") Long id) {
		return ogrenciService.getById(id);
	}

	@GetMapping(value = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findByName(@RequestParam(value = "name") String name) {
		return ogrenciService.findByNAME(name);
	}

	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ogrenci getByWithHeader(@RequestHeader(value = "id") Long id) {
		return ogrenciService.getById(id);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogrenci ogrenci) {
		if (ogrenciService.save(ogrenci)) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Başarı ile kaydedildi\n http://localhost:8080/ogrenci/getById/" + ogrenci.getID()
							+ "\n http://localhost:8080/ogrenci/deleteById/" + ogrenci.getID());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (ogrenciService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	@GetMapping(value = "/findByOgrenciName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findByOgrenciName(@RequestParam(value = "name") String name) {
		return ogrenciService.findByOgrenciNAME(name);

	}

	@GetMapping(value = "/findAllByNAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findAllByNAMELike(@RequestParam(value = "name") String name) {
		return ogrenciService.findAllByNAMELike(name);

	}

	@GetMapping(value = "/findAllByNAMELike2", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findAllByNAMELike(@RequestParam(value = "expression") String expression,
			@RequestParam(value = "sort") org.springframework.data.domain.Sort sort) {
		return ogrenciService.findAllByNAMELike(expression, sort);

	}

	@GetMapping(value = "/findAllByNAMELikeOrderByIDDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ogrenci> findAllByNAMELikeOrderByIDDesc(@RequestParam(value = "name") String name) {
		return ogrenciService.findAllByNAMELikeOrderByIDDesc(name);

	}

}

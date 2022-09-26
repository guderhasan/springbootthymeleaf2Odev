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

import com.bilgeadam.SpringBootRestHibernate.jparepository.DersRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ders;
import com.bilgeadam.SpringBootRestHibernate.service.DersService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "ders")
@RestControllerAdvice(basePackageClasses = DersRepository.class)
@AllArgsConstructor
public class DersController {
	private DersService dersService;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getAll() {
		return dersService.getAll();
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getById(@PathVariable(value = "id") Long id) {
		return dersService.getById(id);
	}

	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getBy(@RequestParam(value = "id") Long id) {
		return dersService.getById(id);
	}

	@GetMapping(value = "/getByOgrNameAndKonuName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getByOgrNameAndKonuName(@RequestParam(value = "name") String name,
			@RequestParam(value = "konu") String konu) {
		return dersService.getDerslerByOgretmenNAMEAndKonuNAME(name, konu);
	}

	@GetMapping(value = "/getByOgrName", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> getBy(@RequestParam(value = "name") String name) {
		return dersService.getDerslerByOgretmenNAME(name);
	}

	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ders getByWithHeader(@RequestHeader(value = "id") Long id) {
		return dersService.getById(id);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ders ders) {

		if (dersService.save(ders)) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Başarı ile kaydedildi\n http://localhost:8080/ders/getById/" + ders.getID()
							+ "\n http://localhost:8080/ders/deleteById/" + ders.getID());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (dersService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	@GetMapping(value = "/findAllDersBykonu_NAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> findAllDersBykonu_NAMELike(@RequestParam(value = "name") String name) {
		return dersService.findAllDersBykonu_NAMELike(name);

	}

	@GetMapping(value = "/findAllDersByogretmen_NAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ders> findAllDersByogretmen_NAMELike(@RequestParam(value = "name") String name) {
		return dersService.findAllDersByogretmen_NAMELike(name);

	}
}

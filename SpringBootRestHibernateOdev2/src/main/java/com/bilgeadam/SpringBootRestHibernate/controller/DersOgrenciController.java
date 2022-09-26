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

import com.bilgeadam.SpringBootRestHibernate.jparepository.DersOgrenciRepository;
import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;
import com.bilgeadam.SpringBootRestHibernate.service.DersOgrenciService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "dersogrenci")
@RestControllerAdvice(basePackageClasses = DersOgrenciRepository.class)
@AllArgsConstructor
public class DersOgrenciController {
	private DersOgrenciService dersOgrenciService;

	@GetMapping(value = "/getByOgrtNameAndDersId", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> getByOgrtNAMEAndDersID(@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Long id) {
		return dersOgrenciService.getDersOgrencilerByOgrenciNAMEAndDersID(name, id);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> getAll() {
		return dersOgrenciService.getAll();
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getById(@PathVariable(value = "id") Long id) {
		return dersOgrenciService.getById(id);
	}

	@GetMapping(value = "/getBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getBy(@RequestParam(value = "id") Long id) {
		return dersOgrenciService.getById(id);
	}

	@GetMapping(value = "/getByWithHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public DersOgrenci getByWithHeader(@RequestHeader(value = "id") Long id) {
		return dersOgrenciService.getById(id);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody DersOgrenci dersOgrenci) {

		if (dersOgrenciService.save(dersOgrenci)) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Başarı ile kaydedildi\n http://localhost:8080/dersOgrenci/getById/" + dersOgrenci.getID()
							+ "\n http://localhost:8080/dersOgrenci/deleteById/" + dersOgrenci.getID());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedilemedi");
		}
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
		if (dersOgrenciService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.IM_USED).body("Başarı ile silindi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile silinemedi");
		}
	}

	@GetMapping(value = "/findAllDersOgrenciByogrenci_NAMELike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> findAllDersOgrenciByogrenci_NAMELike(@RequestParam(value = "name") String name) {
		return dersOgrenciService.findAllDersOgrenciByogrenci_NAMELike(name);

	}

	@GetMapping(value = "/findAllDersOgrenciByders_ID", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DersOgrenci> findAllDersOgrenciByders_ID(@RequestParam(value = "id") Long id) {
		return dersOgrenciService.findAllDersOgrenciByders_ID(id);

	}
}

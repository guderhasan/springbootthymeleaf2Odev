package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgrenciRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgrenciService {
	private OgrenciRepository jpaRepo;

	public List<Ogrenci> findByNAME(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc("%" + name + "%");
	}

	public boolean deleteById(Long id) {
		try {
			jpaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Ogrenci Ogrenci) {
		try {
			return jpaRepo.save(Ogrenci) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Ogrenci getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<Ogrenci> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public List<Ogrenci> findByOgrenciNAME(String name) {
		return jpaRepo.findByOgrenciNAME(name);
	}

	public List<Ogrenci> findAllByNAMELike(String name) {
		return jpaRepo.findAllByNAMELike(name);

	}

	public List<Ogrenci> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort) {
		return jpaRepo.findAllByNAMELike(expression, sort);

	}

	public List<Ogrenci> findAllByNAMELikeOrderByIDDesc(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc(name);

	}

}
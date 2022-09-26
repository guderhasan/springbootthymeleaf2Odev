package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernate.jparepository.KonuRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Konu;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KonuService {
	KonuRepository jpaRepo;

	public List<Konu> findByName(String name) {
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

	public boolean save(Konu Konu) {
		try {
			return jpaRepo.save(Konu) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Konu getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<Konu> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public List<Konu> findByKonuNAME(String name) {
		return jpaRepo.findByKonuNAME(name);
	}

	public List<Konu> findAllByNAMELike(String name) {
		return jpaRepo.findAllByNAMELike(name);

	}

	public List<Konu> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort) {
		return jpaRepo.findAllByNAMELike(expression, sort);

	}

	public List<Konu> findAllByNAMELikeOrderByIDDesc(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc(name);

	}

}
package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.bilgeadam.SpringBootRestHibernate.jparepository.DersOgrenciRepository;
import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class DersOgrenciService {

	private DersOgrenciRepository jpaRepo;

	public List<DersOgrenci> getDersOgrencilerByOgrenciNAMEAndDersID(String ogrtName, Long dersId) {
		Specification<DersOgrenci> specDers = new Specification<DersOgrenci>() {
			private static final long serialVersionUID = -3616159186705609038L;

			@Override
			public Predicate toPredicate(Root<DersOgrenci> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("ders").get("ID"), dersId);
			}
		};
		Specification<DersOgrenci> specOgrenci = new Specification<DersOgrenci>() {
			private static final long serialVersionUID = -3616159186705609038L;

			@Override
			public Predicate toPredicate(Root<DersOgrenci> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("ogrenci").get("NAME"), ogrtName);
			}
		};

		return jpaRepo.findAll(specOgrenci.and(specDers));
	}

	public List<DersOgrenci> getDersOgrencilerByOgrenciNAME(String name) {
		return jpaRepo.findAllDersOgrenciByogrenci_NAMELike("%" + name + "%");
	}

	public List<DersOgrenci> getDersOgrencilerByDersID(Long id) {
		return jpaRepo.findAllDersOgrenciByders_ID(id);
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

	public boolean save(DersOgrenci DersOgrenci) {
		try {
			return jpaRepo.save(DersOgrenci) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public DersOgrenci getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<DersOgrenci> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public List<DersOgrenci> findAllDersOgrenciByogrenci_NAMELike(String name) {
		return jpaRepo.findAllDersOgrenciByogrenci_NAMELike(name);
	}

	public List<DersOgrenci> findAllDersOgrenciByders_ID(Long id) {
		return jpaRepo.findAllDersOgrenciByders_ID(id);

	}
}
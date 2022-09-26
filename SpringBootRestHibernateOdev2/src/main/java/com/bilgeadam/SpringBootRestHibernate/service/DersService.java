package com.bilgeadam.SpringBootRestHibernate.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernate.jparepository.DersRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ders;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DersService {

	private DersRepository jpaRepo;

	public List<Ders> getDerslerByOgretmenNAMEAndKonuNAME(String ogrName, String konuName) {
		Specification<Ders> specKonu = new Specification<Ders>() {
			private static final long serialVersionUID = -3616159186705609038L;

			@Override
			public Predicate toPredicate(Root<Ders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("konu").get("NAME"), konuName);
			}
		};
		Specification<Ders> specOgretmen = new Specification<Ders>() {
			private static final long serialVersionUID = -3616159186705609038L;

			@Override
			public Predicate toPredicate(Root<Ders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("ogretmen").get("NAME"), ogrName);
			}
		};

		return jpaRepo.findAll(specOgretmen.and(specKonu));
	}

	public List<Ders> getDerslerByOgretmenNAME(String name) {
		return jpaRepo.findAllDersByogretmen_NAMELike("%" + name + "%");
	}

	public List<Ders> getDerslerByKonuNAME(String name) {
		return jpaRepo.findAllDersByogretmen_NAMELike("%" + name + "%");
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

	public boolean save(Ders Ders) {
		try {
			return jpaRepo.save(Ders) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Ders getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<Ders> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public List<Ders> findAllDersByogretmen_NAMELike(String name) {
		return jpaRepo.findAllDersByogretmen_NAMELike(name);
	}

	public List<Ders> findAllDersBykonu_NAMELike(String name) {
		return jpaRepo.findAllDersBykonu_NAMELike(name);

	}
}
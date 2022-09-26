package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bilgeadam.SpringBootRestHibernate.model.DersOgrenci;

@org.springframework.stereotype.Repository
@org.springframework.transaction.annotation.Transactional
public interface DersOgrenciRepository extends JpaRepository<DersOgrenci, Long>, JpaSpecificationExecutor<DersOgrenci> {

	public List<DersOgrenci> findAllDersOgrenciByogrenci_NAMELike(String name);

	public List<DersOgrenci> findAllDersOgrenciByders_ID(Long id);

}
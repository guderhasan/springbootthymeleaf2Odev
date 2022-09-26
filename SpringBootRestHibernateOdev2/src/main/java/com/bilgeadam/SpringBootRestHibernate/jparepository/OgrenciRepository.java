package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;

@org.springframework.stereotype.Repository
@org.springframework.transaction.annotation.Transactional
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

	@Query(name = "findByOgrenciNAME", value = "SELECT * FROM obsh.ogrenci WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Ogrenci> findByOgrenciNAME(@Param("NAME") String name);

	public List<Ogrenci> findAllByNAMELike(String name);

	public List<Ogrenci> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort);

	public List<Ogrenci> findAllByNAMELikeOrderByIDDesc(String name);
}
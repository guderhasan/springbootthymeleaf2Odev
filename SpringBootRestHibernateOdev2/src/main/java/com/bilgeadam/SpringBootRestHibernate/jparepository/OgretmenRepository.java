package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;

@org.springframework.stereotype.Repository
@org.springframework.transaction.annotation.Transactional
public interface OgretmenRepository extends JpaRepository<Ogretmen, Long> {

	@Query(name = "findByOgretmenNAME", value = "SELECT * FROM obsh.ogretmen WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Ogretmen> findByOgretmenNAME(@Param("NAME") String name);

	public List<Ogretmen> findAllByNAMELike(String name);

	public List<Ogretmen> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort);

	public List<Ogretmen> findAllByNAMELikeOrderByIDDesc(String name);
}
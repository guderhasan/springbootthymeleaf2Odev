package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bilgeadam.SpringBootRestHibernate.model.Konu;

@org.springframework.stereotype.Repository
@org.springframework.transaction.annotation.Transactional
public interface KonuRepository extends JpaRepository<Konu, Long> {

	@Query(name = "findByKonuNAME", value = "SELECT * FROM obsh.konu WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Konu> findByKonuNAME(@Param("NAME") String name);

	public List<Konu> findAllByNAMELike(String name);

	public List<Konu> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort);

	public List<Konu> findAllByNAMELikeOrderByIDDesc(String name);
}
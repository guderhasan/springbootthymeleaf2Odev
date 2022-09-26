package com.bilgeadam.SpringBootRestHibernate.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bilgeadam.SpringBootRestHibernate.model.Ders;

public interface DersRepository extends JpaRepository<Ders, Long>, JpaSpecificationExecutor<Ders> {

	public List<Ders> findAllDersByogretmen_NAMELike(String name);

	public List<Ders> findAllDersBykonu_NAMELike(String name);

}
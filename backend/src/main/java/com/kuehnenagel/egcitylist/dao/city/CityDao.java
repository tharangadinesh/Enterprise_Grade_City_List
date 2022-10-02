package com.kuehnenagel.egcitylist.dao.city;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kuehnenagel.egcitylist.model.city.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

	@Query("FROM City WHERE name = :name")
	City findByName(@Param("name") String name);

	@Query("FROM City WHERE name = :name AND photo = :photo")
	List<City> findByNameAndPhoto(@Param("name") String name, @Param("photo") String photo);

	//	@Query("SELECT city FROM City city WHERE LOWER(city.name) LIKE %:name% ")
	//	Page<City> findByNameContaining(@Param("name") String name, Pageable pageable);
	Page<City> findByNameContaining(String name, Pageable pageable);

}

package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query(value = "SELECT * FROM opinions LIMIT 10", nativeQuery = true)
    List<Opinion> getAllOpinions();
}

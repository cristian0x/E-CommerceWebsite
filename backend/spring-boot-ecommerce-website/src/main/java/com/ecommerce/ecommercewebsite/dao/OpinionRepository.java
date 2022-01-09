package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    @Query(value = "SELECT * FROM opinions LIMIT 10", nativeQuery = true)
    List<Opinion> getAllOpinions();

    @Query(value = "SELECT * FROM opinions WHERE product_id = :product_id", nativeQuery = true)
    List<Opinion> getOpinionsByProductId(@Param("product_id") Long product_id);
}

package com.hardware.api.Repository;

import com.hardware.api.Model.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>
{
    @Query(value = "SELECT DISTINCT * FROM brands WHERE brands.name = :brand", nativeQuery = true)
    Brand findByBrandName(@Param("brand") String brand);
}

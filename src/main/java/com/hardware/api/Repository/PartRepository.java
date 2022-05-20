package com.hardware.api.Repository;


import java.util.List;

import com.hardware.api.Model.Part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>
{
    @Query(value = "SELECT DISTINCT * FROM parts p JOIN brands ON p.id_brand = :brand", nativeQuery = true)
    List<Part> findPartByBrand(@Param("brand") Long brand);
}

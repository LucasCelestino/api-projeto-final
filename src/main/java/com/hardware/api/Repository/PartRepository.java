package com.hardware.api.Repository;


import com.hardware.api.Model.Part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>
{
    // @Query(value = "SELECT * FROM parts INNER JOIN brands ON parts.id_brand = brands.id WHERE brands.name = ?1", nativeQuery = true)
    // List<Part> findByBrandName(String brand);
}

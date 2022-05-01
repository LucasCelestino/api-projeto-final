package com.hardware.api.Repository;

import java.util.List;

import com.hardware.api.Model.Brand;
import com.hardware.api.Model.Part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{}
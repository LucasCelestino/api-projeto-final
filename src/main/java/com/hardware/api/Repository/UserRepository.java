package com.hardware.api.Repository;

import com.hardware.api.Model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Admin, Long> {}

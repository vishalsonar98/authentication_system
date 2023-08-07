package com.authentication_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authentication_system.entity.Role;

/**
 * Manages data of Role entity class and provides various methods to operate
 * over data
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

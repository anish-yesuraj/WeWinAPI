package com.ay.wewin.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ay.wewin.api.model.Role;
import com.ay.wewin.api.model.RoleName;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}

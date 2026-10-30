package com.SecurityProto.DeppSecured.Repository;

import com.SecurityProto.DeppSecured.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}

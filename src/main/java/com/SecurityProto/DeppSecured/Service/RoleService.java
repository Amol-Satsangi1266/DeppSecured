package com.SecurityProto.DeppSecured.Service;

import com.SecurityProto.DeppSecured.Entity.Role;
import com.SecurityProto.DeppSecured.Repository.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepo repo;

    public RoleService(RoleRepo repo) {
        this.repo = repo;
    }

    public void addRole(Role role)
    {
        repo.save(role);
    }
}

package com.SecurityProto.DeppSecured.Controller;

import com.SecurityProto.DeppSecured.Entity.Role;
import com.SecurityProto.DeppSecured.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> addRoles(@Valid @RequestBody Role role)
    {
        service.addRole(role);
        return ResponseEntity.ok("DONE");
    }
}

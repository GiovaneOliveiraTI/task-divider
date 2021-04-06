package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.ERole;
import com.br.pos.taskdivider.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(ERole name);
}

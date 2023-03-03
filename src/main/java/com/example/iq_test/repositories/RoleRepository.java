package com.example.iq_test.repositories;

import com.example.iq_test.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
    List<Role> findByNameOrName(String name1, String name2);
}

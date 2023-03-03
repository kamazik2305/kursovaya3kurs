package com.example.iq_test.repositories;

import com.example.iq_test.models.Role;
import com.example.iq_test.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String userName);
    List<User> findByRoles(Role role);
    List<User> findByRolesIn(List<Role> roles);
}

package com.example.iq_test.repositories;

import com.example.iq_test.models.ChildGroup;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<ChildGroup, Long> {
}

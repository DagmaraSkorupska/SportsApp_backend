package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.SportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends CrudRepository<SportEntity, Long> {
}

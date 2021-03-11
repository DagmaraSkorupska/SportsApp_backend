package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Override
    List<UserEntity> findAll();

    @Override
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByFirstName(String firstName);

    Optional<UserEntity> findByLastName(String lastName);

    Optional<UserEntity> findByEmail(String email);

    @Override
    <User2 extends UserEntity> User2 save(User2 user);

    @Override
    void deleteById(Long id);

    @Override
    void delete(UserEntity userEntity);
}

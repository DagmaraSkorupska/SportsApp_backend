package com.project.sports_app_backend.repository;

import com.project.sports_app_backend.domain.SportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SportRepository extends CrudRepository<SportEntity, Long> {
   @Override
   List<SportEntity> findAll();

   @Override
   Optional<SportEntity> findById(Long id);


   <Sport2 extends SportEntity> Sport2 saveSport(Sport2 sport);

   @Override
   void deleteById(Long id);




}

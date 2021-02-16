package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportService {
    @Autowired
    SportRepository sportRepository;

    public List<SportEntity> getAllSports(){
        return sportRepository.findAll();
    }

    public Optional<SportEntity> getSport(Long id){
        return sportRepository.findById(id);
    }

    public SportEntity saveSport(SportEntity sportEntity){
        return sportRepository.saveSport(sportEntity);
    }

    public void deleteSport(Long id){
        sportRepository.deleteById(id);
    }

}

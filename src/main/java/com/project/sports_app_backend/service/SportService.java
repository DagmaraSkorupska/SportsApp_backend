package com.project.sports_app_backend.service;

import com.project.sports_app_backend.domain.SportDto;
import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class SportService {

    SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public List<SportEntity> getAllSports(){
        return sportRepository.findAll();
    }

    public Optional<SportEntity> getSport(Long id){
        return sportRepository.findById(id);
    }

//    public SportEntity getSportByName(String name){
//        return sportRepository.findByName(name);
//    }
//
////    public Optional<SportEntity> getSportByName(String name){
////       return sportRepository.findByName(name);
////   }

    public SportEntity saveSport(SportEntity sportEntity){
        return sportRepository.save(sportEntity);
    }

    public void deleteSport(Long id){
        sportRepository.deleteById(id);
    }

//    @PostConstruct
////    @EventListener(ApplicationReadyEvent.class)
//    public void addSport(){
//        SportEntity sportEntity1 = new SportEntity("Swim", "Learning swim in the pool", null, null);
//        SportEntity sportEntity2 = new SportEntity("Run", "Learning to run marathons", null, null);
//        SportEntity sportEntity3 = new SportEntity("Gym", "Gym classes", null, null);
//        sportRepository.save(sportEntity1);
//        sportRepository.save(sportEntity2);
//        sportRepository.save(sportEntity3);
//    }


}

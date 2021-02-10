package com.project.sports_app_backend.controller;

import com.project.sports_app_backend.domain.SportDto;
import com.project.sports_app_backend.mapper.SportMapper;
import com.project.sports_app_backend.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("v1/sports")
public class SportsController {
//    @Autowired
//    private SportService sportService;
//
//    @Autowired
//    private SportMapper sportMapper;
//
//    @RequestMapping(method = RequestMethod.GET, value = "getAllSports")
//    public List<SportDto> getAllSports(){
//        return sportMapper.costam(sportService.getAllSports());
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "createSport", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createSport(@RequestBody SportDto sportDto){
//       sportService.saveSport(mapper+sportDto);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "getSport")
//    public SportDto getSport(@RequestParam Long sportId){
//        return sportMapper.mapcostam(sportService.getSport(sportId));
//    }

//    @RequestMapping(method = RequestMethod.PUT, value = "updateSport")
//    public SportDto updateSport(@RequestBody SportDto sportDto){
//        return sportMapper/mapcostam(sportService.)
//    }


}

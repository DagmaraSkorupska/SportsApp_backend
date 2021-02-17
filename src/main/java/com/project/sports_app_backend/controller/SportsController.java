package com.project.sports_app_backend.controller;

import com.project.sports_app_backend.domain.SportDto;
import com.project.sports_app_backend.mapper.SportMapper;
import com.project.sports_app_backend.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/sports")
public class SportsController {
    @Autowired
    private SportService sportService;

    @Autowired
    private SportMapper sportMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/sports")
    public List<SportDto> getAllSports(){
        return sportMapper.mapToSportDtoList(sportService.getAllSports());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sports/id={sportId}")
    public SportDto getSport(@RequestParam Long sportId) throws SportNotFoundException{
        return sportMapper.mapToSportDto(sportService.getSport(sportId).orElseThrow(SportNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sports", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSport(@RequestBody SportDto sportDto){
       sportService.saveSport(sportMapper.mapToSportEntity(sportDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sports")
    public SportDto updateSport(@RequestBody SportDto sportDto){
        return sportMapper.mapToSportDto(sportService.saveSport(sportMapper.mapToSportEntity(sportDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sports/{sportId}")
    public void deleteSport(@PathVariable Long sportId){
        sportService.deleteSport(sportId);
    }



}

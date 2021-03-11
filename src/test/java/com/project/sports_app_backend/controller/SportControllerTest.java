package com.project.sports_app_backend.controller;

import com.google.gson.Gson;
import com.project.sports_app_backend.domain.*;
import com.project.sports_app_backend.mapper.SportMapper;
import com.project.sports_app_backend.service.SportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(SportsController.class)
public class SportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportService service;

    @MockBean
    private SportMapper sportMapper;

    @Test
    public void shouldGetSports() throws Exception {
        //given
        List<SportDto> sportDtos = new ArrayList<>();
        List<UserDto> userDtos = new ArrayList<>();
        sportDtos.add(new SportDto(15L, "name", "description",userDtos, new WorkoutDto()));
        when(sportMapper.mapToSportDtoList(service.getAllSports())).thenReturn(sportDtos);
        //when&&then
        mockMvc.perform(get("/v1/sports")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(15)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].description", is("description")));
    }

    @Test
    public void shouldGetOneSport() throws Exception {
        //given
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        SportDto sportDto = new SportDto(15L, "name", "description",userDtos, new WorkoutDto());
        SportEntity sportEntity = new SportEntity("name", "description",userEntities, new WorkoutEntity());

        when(sportMapper.mapToSportDto(sportEntity)).thenReturn(sportDto);
        when(service.getSport(sportEntity.getId())).thenReturn(Optional.of(sportEntity));

        //when&then
        mockMvc.perform(get("/v1/sports/15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    public void shouldCreateSport() throws Exception {
        //given
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        SportDto sportDto = new SportDto(15L, "name", "description",userDtos, new WorkoutDto());
        SportEntity sportEntity = new SportEntity("name", "description",userEntities, new WorkoutEntity());

        when(service.saveSport(sportEntity)).thenReturn(sportEntity);
        when(sportMapper.mapToSportEntity(sportDto)).thenReturn(sportEntity);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(sportDto);

        //when&then
        mockMvc.perform(post("/v1/sports")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateSport() throws Exception{
        //given
        List<UserDto> userDtos = new ArrayList<>();
        List<UserEntity> userEntities = new ArrayList<>();
        SportDto sportDto = new SportDto(15L, "name", "description",userDtos, new WorkoutDto());
        SportEntity sportEntity = new SportEntity("name", "description",userEntities, new WorkoutEntity());

        when(sportMapper.mapToSportDto(sportEntity)).thenReturn(sportDto);
        when(service.saveSport(sportEntity)).thenReturn(sportEntity);
        when(sportMapper.mapToSportEntity(any(SportDto.class))).thenReturn(sportEntity);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(sportDto);

        //when&then
        mockMvc.perform(put("/v1/sports")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))

                .andExpect(jsonPath("$.id", is(15)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    public void shouldDeleteSport() throws Exception {
        //given&when&then
        mockMvc.perform(delete("/v1/sports/15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

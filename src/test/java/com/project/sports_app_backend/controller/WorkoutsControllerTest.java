package com.project.sports_app_backend.controller;

import com.google.gson.Gson;
import com.project.sports_app_backend.domain.SportDto;
import com.project.sports_app_backend.domain.SportEntity;
import com.project.sports_app_backend.domain.WorkoutDto;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.mapper.WorkoutMapper;
import com.project.sports_app_backend.service.WorkoutService;
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

@WebMvcTest(WorkoutsController.class)
public class WorkoutsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkoutService workoutService;

    @MockBean
    private WorkoutMapper workoutMapper;

    @Test
    public void shouldGetWorkouts() throws Exception {
        //given
        List<WorkoutDto> workoutDtos = new ArrayList<>();
        List<SportDto> sportDtos = new ArrayList<>();
        workoutDtos.add(new WorkoutDto(78L, "name", "desc", 23, 45, "address", sportDtos ));
        when(workoutMapper.mapToWorkoutDtoList(workoutService.getAllWorkout())).thenReturn(workoutDtos);
        //when&then
        mockMvc.perform(get("/v1/workouts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(78)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].description", is("desc")))
                .andExpect(jsonPath("$[0].durationMin", is(23)))
                .andExpect(jsonPath("$[0].price1h", is(45.0)))
                .andExpect(jsonPath("$[0].address", is("address")));
    }

    @Test
    public void shouldGetOneWorkouts()throws Exception {
        //given
        List<SportDto> sportDtos = new ArrayList<>();
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutDto workoutDto = new WorkoutDto(78L, "name", "desc", 23, 45, "address", sportDtos);
        WorkoutEntity workoutEntity = new WorkoutEntity( "name", "desc", 23, 45, "address", sportEntities);

        when(workoutMapper.mapToWorkoutDto(workoutEntity)).thenReturn(workoutDto);
        when(workoutService.getWorkoutById(workoutEntity.getId())).thenReturn(Optional.of(workoutEntity));
        //when&then
        mockMvc.perform(get("/v1/workouts/78")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(78)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.durationMin", is(23)))
                .andExpect(jsonPath("$.price1h", is(45.0)))
                .andExpect(jsonPath("$.address", is("address")));
    }

    @Test
    public void shouldCreateWorkout() throws Exception{
        //given
        List<SportDto> sportDtos = new ArrayList<>();
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutDto workoutDto = new WorkoutDto(78L, "name", "desc", 23, 45, "address", sportDtos);
        WorkoutEntity workoutEntity = new WorkoutEntity("name", "desc", 23, 45, "address", sportEntities);

        when(workoutService.saveWorkout(workoutEntity)).thenReturn(workoutEntity);
        when(workoutMapper.mapToWorkoutEntity(workoutDto)).thenReturn(workoutEntity);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(workoutDto);

        //when&then
        mockMvc.perform(post("/v1/workouts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateWorkout() throws Exception{
        //given
        List<SportDto> sportDtos = new ArrayList<>();
        List<SportEntity> sportEntities = new ArrayList<>();
        WorkoutDto workoutDto = new WorkoutDto(78L, "name", "desc", 23, 45, "address", sportDtos);
        WorkoutEntity workoutEntity = new WorkoutEntity("name", "desc", 23, 45, "address", sportEntities);

        when(workoutMapper.mapToWorkoutDto(workoutEntity)).thenReturn(workoutDto);
        when(workoutService.saveWorkout(workoutEntity)).thenReturn(workoutEntity);
        when(workoutMapper.mapToWorkoutEntity(any(WorkoutDto.class))).thenReturn(workoutEntity);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(workoutDto);

        //when&then
        mockMvc.perform(put("/v1/workouts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(78)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("desc")))
                .andExpect(jsonPath("$.durationMin", is(23)))
                .andExpect(jsonPath("$.price1h", is(45.0)))
                .andExpect(jsonPath("$.address", is("address")));
    }

    @Test
    public void deleteWorkouts() throws Exception {
        //given&when&then
        mockMvc.perform(delete("/v1/workouts/78")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}

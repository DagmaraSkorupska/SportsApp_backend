package com.project.sports_app_backend.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.sports_app_backend.domain.*;
import com.project.sports_app_backend.mapper.ReservationMapper;
import com.project.sports_app_backend.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.sports_app_backend.domain.UserType.USER;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private ReservationMapper reservationMapper;

    @Test
    public void shouldGetReservation() throws Exception {
        //given
        List<ReservationDto> reservationDtos = new ArrayList<>();
        reservationDtos.add(new ReservationDto(78L, 23, LocalDateTime.now(), new UserDto(), new WorkoutDto()));
        when(reservationMapper.mapToReservationDtoList(reservationService.getAllReservation())).thenReturn(reservationDtos);
        //when&then
        mockMvc.perform(get("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(78)))
                .andExpect(jsonPath("$[0].toPay", is(23.0)));

    }

    @Test
    public void shouldGetOneReservationFromId() throws Exception {
        //given
        ReservationDto reservationDto = new ReservationDto(78L, 23, LocalDateTime.now(), new UserDto(), new WorkoutDto());
        Reservation reservation = new Reservation( 23, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());

        when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);
        when(reservationService.getReservationById(reservation.getId())).thenReturn(Optional.of(reservation));
        //when&then
        mockMvc.perform(get("/v1/reservations/id = 78")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toPay", is(23.0)));
    }

//    @Test
//    public void shouldGetOneReservationFromDate() throws Exception {
//        //given
//        ReservationDto reservationDto = new ReservationDto(78L, 23, LocalDateTime.now(), new UserDto(), new WorkoutDto());
//        Reservation reservation = new Reservation(78L, 23, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());
//
//        when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);
//        when(reservationService.getReservationByDate(reservation.getDate())).thenReturn(Optional.of(reservation));
//        //when&then
//        mockMvc.perform(get("/v1/reservations/date = 2021-02/-19 20:03")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(78)))
//                .andExpect(jsonPath("$.toPay", is(23.0)));
//    }

    @Test
    public void shouldGetOneReservationFromEmail() throws Exception {
        //given
        List<ReservationDto> reservationDtos = new ArrayList<>();
        UserEntity userEntity = new UserEntity(USER, "user2", "abc2", "test@mail.com", "password1", "desc", "1324679", null, null);
        UserDto userDto = new UserDto(10L, USER, "user2", "abc2", "test@mail.com", "password1", "desc", "1324679", null, null);

        reservationDtos.add(new ReservationDto(78L, 23, LocalDateTime.now(), userDto, new WorkoutDto()));
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation( 23, LocalDateTime.now(), userEntity, new WorkoutEntity()));

        when(reservationMapper.mapToReservationDtoList(reservations)).thenReturn(reservationDtos);
        when(reservationService.getReservationByUserLogin(reservations.get(0).getUserEntity().getEmail())).thenReturn(reservations);

        //when&then
        mockMvc.perform(get("/v1/reservations/login = test@mail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toPay", is(23.0)));
    }

    @Test
    public void shouldCreateReservation() throws Exception {
        //given
        ReservationDto reservationDto = new ReservationDto(78L, 23, LocalDateTime.now(), new UserDto(), new WorkoutDto());
        Reservation reservation = new Reservation( 23, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());

        when(reservationService.saveReservation(reservation)).thenReturn(reservation);
        when(reservationMapper.mapToReservationEntity(reservationDto)).thenReturn(reservation);

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).create();
        String jsonContent = gson.toJson(reservationDto);

        //when&then
        mockMvc.perform(post("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdateReservation() throws Exception {
        //given
        ReservationDto reservationDto = new ReservationDto(78L, 23, LocalDateTime.now(), new UserDto(), new WorkoutDto());
        Reservation reservation = new Reservation( 23, LocalDateTime.now(), new UserEntity(), new WorkoutEntity());

        when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);
        when(reservationService.saveReservation(reservation)).thenReturn(reservation);
        when(reservationMapper.mapToReservationEntity(any(ReservationDto.class))).thenReturn(reservation);

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).create();
        String jsonContent = gson.toJson(reservationDto);

        //when&then
        mockMvc.perform(put("/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toPay", is(23.0)));
    }

    @Test
    public void deleteReservation() throws Exception {
        //given&when&then
        mockMvc.perform(delete("/v1/reservations/78")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}

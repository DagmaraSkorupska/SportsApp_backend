//package com.project.sports_app_backend.controller;
//
//import com.project.sports_app_backend.controller.exception.ReservationNotFoundException;
//import com.project.sports_app_backend.domain.ReservationDto;
//import com.project.sports_app_backend.mapper.ReservationMapper;
//import com.project.sports_app_backend.service.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/v1")
//public class ReservationController {
//    @Autowired
//    private ReservationService reservationService;
//
//    @Autowired
//    private ReservationMapper reservationMapper;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
//    public List<ReservationDto> getAllReservation(){
//        return reservationMapper.mapToReservationDtoList(reservationService.getAllReservation());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/reservations/id = {reservationId}")
//    public ReservationDto getReservationFromId(@PathVariable Long reservationId) throws ReservationNotFoundException {
//        return reservationMapper.mapToReservationDto(reservationService.getReservationById(reservationId).orElseThrow(() -> new ReservationNotFoundException(reservationId)));
//    }
//
////    @RequestMapping(method = RequestMethod.GET, value = "/reservations/date = {date}")
////    public ReservationDto getReservationFromDate(@PathVariable LocalDateTime date) throws ReservationNotFoundException{
////        return reservationMapper.mapToReservationDto(reservationService.getReservationByDate(date).orElseThrow(ReservationNotFoundException::new));
////    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/reservations/login = {email}")
//    public List<ReservationDto> getReservationFromEmail(@PathVariable String email){
//        return reservationMapper.mapToReservationDtoList(reservationService.getReservationByUserLogin(email));
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/reservations", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void createReservation(@RequestBody ReservationDto reservationDto){
//        reservationService.saveReservation(reservationMapper.mapToReservationEntity(reservationDto));
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/reservations")
//    public ReservationDto updateReservation(@RequestBody ReservationDto reservationDto){
//        return reservationMapper.mapToReservationDto(reservationService.saveReservation(reservationMapper.mapToReservationEntity(reservationDto)));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{reservationId}")
//    public void deleteReservation(@PathVariable Long reservationId){
//        reservationService.deleteById(reservationId);
//    }
//}

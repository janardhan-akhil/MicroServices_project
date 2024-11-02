package com.hotel_service.controller;


import com.hotel_service.entities.Hotel;
import com.hotel_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // create

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    // get all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
    }



    // get single hotel

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(id));
    }
}

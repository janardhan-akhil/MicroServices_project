package com.hotel_service.service;

import com.hotel_service.entities.Hotel;

import java.util.List;

public interface HotelService {

    // create

    Hotel create(Hotel hotel);

    // get all hotels

    List<Hotel> getAllHotels();

    // get single hotel

    Hotel get(String id);
}

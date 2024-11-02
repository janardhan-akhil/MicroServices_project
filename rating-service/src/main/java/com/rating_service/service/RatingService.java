package com.rating_service.service;

import com.rating_service.entities.Rating;

import java.util.List;

public interface RatingService {

    // create

    Rating createRating(Rating rating);

    // get all ratings
    List<Rating> getAllRatings();


    // get all ratings by userId
    List<Rating> getALlRatingsByUserId(String userId);


    // get all ratings by hotelId
    List<Rating> getAllRatingByHotelId(String hotelId);


}

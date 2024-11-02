package com.user_service.exteranl.service;


import com.user_service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    Rating createRating(Rating values);


    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);


    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}

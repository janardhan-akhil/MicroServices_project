package com.user_service.service;

import com.user_service.entities.Hotel;
import com.user_service.entities.Rating;
import com.user_service.entities.User;
import com.user_service.exception.ResourceNotFoundException;
import com.user_service.exteranl.service.HotelService;
import com.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

       String randomUserId = UUID.randomUUID().toString();
       user.setUserID(randomUserId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userID) {
        // Get user from database with the help of user repository
        User user = userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server"+userID));
        // Fetch rating for the above user form RATING SERVICE
        // http://localhost:8083/ratings/users/099928aa-1d74-4bf2-ab5a-5a082d6247a4
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserID(), Rating[].class);
        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            //http://localhost:8082/hotels/5e363ef4-e43d-4473-8bc2-81cacd656213
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
           // logger.info("response status code: {}", forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            // return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user;

    }
}

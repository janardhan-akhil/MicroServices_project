package com.user_service.controller;

import com.user_service.entities.User;
import com.user_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

int retryCout = 1;

    //single user get
    @GetMapping("/{userId}")
//    @CircuitBreaker(name= "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//   @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name= "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Get single User Handler: UserController");
        logger.info("Retry count {}:", retryCout);
        retryCout++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // creating fall back method for circuitbreaker

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = User.builder().email("Dummy@gmail.com")
                .name("Dummy")
                .about("This user is created because some service is down")
                .userID("141234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //get all users

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);

    }
}

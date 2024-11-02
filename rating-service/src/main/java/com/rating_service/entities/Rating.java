package com.rating_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;;
    private String feedback;
}

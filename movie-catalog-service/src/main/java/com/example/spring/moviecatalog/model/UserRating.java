package com.example.spring.moviecatalog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> ratings;

    //Default Constructor
    public UserRating(){}

    public UserRating(String userId, Rating rating){
        this.userId = userId;
        if(ratings == null){
            ratings = new ArrayList<Rating>();
        }
        ratings.add(rating);
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }
}

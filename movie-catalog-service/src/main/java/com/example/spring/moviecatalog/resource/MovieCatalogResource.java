package com.example.spring.moviecatalog.resource;

import com.example.spring.moviecatalog.model.CatalogItem;
import com.example.spring.moviecatalog.model.Movie;
import com.example.spring.moviecatalog.model.Rating;
import com.example.spring.moviecatalog.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = restTemplate.getForObject("http://localhost:10092/movieratings/user/"+ userId, UserRating.class);

        return (List<CatalogItem>) userRating.getRatings().stream().map(
                                rating -> {
                                    Movie movie = restTemplate.getForObject("http://localhost:10091/movies/" + rating.getMovieId(),
                                                    Movie.class);
                                    return new CatalogItem(movie.getName(),movie.getSummary(),rating.getRating());
                                    }
                                ).collect(Collectors.toList());

    }
}

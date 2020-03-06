package com.example.spring.moviecatalog.resource;

import com.example.spring.moviecatalog.model.CatalogItem;
import com.example.spring.moviecatalog.model.Movie;
import com.example.spring.moviecatalog.model.Rating;
import com.example.spring.moviecatalog.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        String movieRatingsService = getPropertyValue("ds.movie.ratings.service.name");
        String movieInfoService = getPropertyValue("ds.movie.info.service.name");
        UserRating userRating = restTemplate.getForObject(
                "http://"+ movieRatingsService + "/movieratings/user/"+ userId, UserRating.class);
        return (List<CatalogItem>) userRating.getRatings().stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject(
                            "http://" +movieInfoService + "/movies/" + rating.getMovieId(),
                            Movie.class);
                    return new CatalogItem(movie.getName(),movie.getSummary(),rating.getRating());
                }
        ).collect(Collectors.toList());

    }


    private String getPropertyValue(String key)
    {
        String returnValue = "No value";

        String keyValue = env.getProperty(key);

        if( keyValue!= null && !keyValue.isEmpty())
        {
            returnValue = keyValue;
        }
        return returnValue;
    }

}

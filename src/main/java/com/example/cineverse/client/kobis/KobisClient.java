package com.example.cineverse.client.kobis;

import com.example.cineverse.model.CinemaListModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        configuration = MovieDataFeignClientConfiguration.class,
        url = "http://kobis.or.kr/kobisopenapi/webservice/rest",
        name = "movie", contextId= "feignClientForMovie"
)
public interface KobisClient {
    @GetMapping("/movie/searchMovieList.json")
    CinemaListModel sendEmail(
            @PathVariable("appKey") final String appKey);

}

package com.example.cineverse.process;

import com.example.cineverse.common.Utility;
import com.example.cineverse.model.CinemaListModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class CinemaAPIConnect {
    private String _url = "http://kobis.or.kr";

    @Value("${spring.data.openapi.key}")
    private String _key;

    public CinemaListModel.MovieListResponse SearchMovieList(CinemaListModel.MovieListRequest movieListRequest) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String urlPath = "/kobisopenapi/webservice/rest/movie/searchMovieList.json";
        String result = "";
        CinemaListModel.MovieListResponse response= null;

        params.add("key", _key);
        //params.add("repNationCd", movieListRequest.getRepNationCd());
        //params.add("movieTypeCd", movieListRequest.getMovieTypeCd());
        params.add("itemPerPage", movieListRequest.getItemPerPage());
        params.add("curPage", movieListRequest.getCurPage());

        result = Utility.GetHttp(_url, urlPath, params);

        response = objectMapper.readValue(result, CinemaListModel.MovieListResponse.class);

        return response;
    }
}

package com.example.cineverse.process;

import com.example.cineverse.common.Utility;
import com.example.cineverse.model.CinemaListModel;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class CinemaAPIConnect {
    static String _url = "http://kobis.or.kr";
    static final String _key = "aa439f24465fd638440fab41c9614382";

    public CinemaListModel.MovieListResponse SearchMovieList(CinemaListModel.MovieListRequest movieListRequest) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String urlPath = "/kobisopenapi/webservice/rest/movie/searchMovieList.json";
        String result = "";
        CinemaListModel.MovieListResponse response= null;

        params.add("key", _key);
        params.add("repNationCd", movieListRequest.getRepNationCd());
        params.add("movieTypeCd", movieListRequest.getMovieTypeCd());

        result = Utility.GetHttp(_url, urlPath, params);

        response = objectMapper.readValue(result, CinemaListModel.MovieListResponse.class);

        return response;
    }
}

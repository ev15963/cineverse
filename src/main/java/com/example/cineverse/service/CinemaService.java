package com.example.cineverse.service;

import com.example.cineverse.model.CinemaListModel;
import com.example.cineverse.process.CinemaAPIConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class CinemaService {
    @Autowired
    private CinemaAPIConnect CinemaAPIConnect;

    private static final Logger LOG = LoggerFactory.getLogger(CinemaService.class);

    public HashMap<String, Object> RunSearch(String param, int curPage)
    {
        CinemaListModel.MovieListRequest movieListRequest = new CinemaListModel.MovieListRequest();
        CinemaListModel.MovieListResponse movieListResponse = null;

        HashMap<String, Object> result = new HashMap<String, Object>();

        try{
            /*
            공통코드 조회
            * .../searchCodeList.json?comCode=2204
            * 국적코드 2204
            * 영화유형코드 2201
            */
            //movieListRequest.setRepNationCd("22041011"); //한국
            //movieListRequest.setMovieTypeCd("220101"); //장편

            movieListRequest.setItemPerPage("100");
            movieListRequest.setCurPage(String.valueOf(curPage));
            movieListResponse = CinemaAPIConnect.SearchMovieList(movieListRequest);

            //result.put("MovieListCard", MovieListCard(movieListResponse));
            result.put("MovieList", movieListResponse);

        } catch (Exception e){
            LOG.info("Exception : " + e.getMessage());
            LOG.info("에러 page : " + movieListRequest.getCurPage());
            e.getStackTrace();
        } finally {
        }

        return result;
    }

    public String MovieListCard(CinemaListModel.MovieListResponse movieListResponse){
        StringBuffer movieListCard = new StringBuffer();

        for(CinemaListModel.Movie movie : movieListResponse.getMovieListResult().getMovieList()){
            // 대표장르 | 영화명
            movieListCard.append(movie.getRepGenreNm()  + " | " + movie.getMovieNm());

            movieListCard.append(System.lineSeparator());
        }

        LOG.info("card : " + movieListCard.toString());

        return movieListCard.toString();
    }
}

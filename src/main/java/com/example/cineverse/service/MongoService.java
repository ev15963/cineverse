package com.example.cineverse.service;

import com.example.cineverse.model.CinemaListModel;
import com.example.cineverse.repos.CinemaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MongoService {
    private static final Logger LOG = LoggerFactory.getLogger(MongoService.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public int bulkInsertProducts(HashMap<String, Object> result) {

        Instant start = Instant.now();
        mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(true));

        CinemaListModel.MovieListResponse movieListResponse = (CinemaListModel.MovieListResponse) result.get("MovieList");
        BulkOperations bulkInsertion = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, CinemaListModel.Movie.class);

        LOG.info("totcnt : " + String.valueOf(movieListResponse.getMovieListResult().getTotCnt()));
        LOG.info("source : " + movieListResponse.getMovieListResult().getSource());

        for(CinemaListModel.Movie movie : movieListResponse.getMovieListResult().getMovieList()) {
            //bulkInsertion.insert(movie);
            LOG.info("제목 : " + movie.getMovieNm());
            LOG.info("장르 : " + movie.getGenreAlt());
            //LOG.info("제작사 : " + movie.getCompanys());
        }

        //BulkWriteResult bulkWriteResult = bulkInsertion.execute();
        //LOG.info("Bulk insert of "+bulkWriteResult.getInsertedCount()+" documents completed in "+ Duration.between(start, Instant.now()).toMillis() + " milliseconds");

        //return bulkWriteResult.getInsertedCount();
        return 0;
    }
}
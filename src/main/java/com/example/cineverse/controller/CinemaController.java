package com.example.cineverse.controller;

import com.example.cineverse.service.CinemaService;
import com.example.cineverse.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;

/*
 * 영화박스오피스 DB (일 3000회 제한)
 * 일별 박스오피스
 * 주간/주말 박스오피스
 * 공통코드 조회
 * 영화목록
 * 영화 상세정보
 * 영화사목록
 * 영화사 상세정보
 * 영화인목록
 * 영화인 상세정보
 */

@RestController
@RequestMapping("/api/cinema/*")
public class CinemaController {
    @Autowired
    private CinemaService CinemaService;

    @Autowired
    private MongoService mongoService;

    @RequestMapping("/search")
    public HashMap<String, Object> SearchCinema(@RequestParam(value="param",
            required = false, defaultValue="") String param)
    {
        HashMap<String, Object> result;

        result = CinemaService.RunSearch(param);
        mongoService.bulkInsertProducts(result);

        return result;
    }
}



package com.faithfulolaleru.schoolResult.controllers;

import com.faithfulolaleru.schoolResult.dtos.ScoreRequest;
import com.faithfulolaleru.schoolResult.dtos.ScoreResponse;
import com.faithfulolaleru.schoolResult.response.AppResponse;
import com.faithfulolaleru.schoolResult.services.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@AllArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping("/")
    public AppResponse<?> createScore(@RequestBody ScoreRequest requestDto) {

        ScoreResponse response = scoreService.createOrUpdateScore(requestDto);

        return AppResponse.builder()
                .status(AppResponse.ResponseStatus.SUCCESSFUL)
                .message("Created/Updated Score successfully")
                .data(response)
                .build();
    }

    @GetMapping("/{studentId}")
    public AppResponse<?> getAllScoresByStudentId(@PathVariable("studentId") Integer studentId) {

        List<ScoreResponse> scoreList = scoreService.getAllScoresByStudentId(studentId);

        return AppResponse.builder()
                .status(AppResponse.ResponseStatus.SUCCESSFUL)
                .message("Successful")
                .data(scoreList)
                .build();
    }

    

}
package com.faithfulolaleru.schoolResult.services;

import com.faithfulolaleru.schoolResult.dtos.ScoreRequest;
import com.faithfulolaleru.schoolResult.dtos.ScoreResponse;
import com.faithfulolaleru.schoolResult.exception.ErrorResponse;
import com.faithfulolaleru.schoolResult.exception.GeneralException;
import com.faithfulolaleru.schoolResult.models.Score;
import com.faithfulolaleru.schoolResult.repositories.ScoreRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreService {

    private final StudentService studentService;

    private final ScoreRepository scoreRepository;

    private final ModelMapper modelMapper;

    public ScoreResponse createOrUpdateScore(ScoreRequest requestDto) {
        boolean studentExist = studentService.isStudentExist(requestDto.getStudentId());

        if(!studentExist) {
            throw new GeneralException(HttpStatus.NOT_FOUND,
                    ErrorResponse.ERROR_STUDENT_NOT_EXIST,
                    "Student with id doesn't exist");
        }

        Optional<Score> scoreOptional = scoreRepository.
                findByStudentIdAndTerm(requestDto.getStudentId(), requestDto.getTerm());

        Score score = scoreOptional.orElse(null);
        Score newScore;
        ScoreResponse responseDto;

        if(scoreOptional.isPresent()) {
            // update score
            score.setSubject1Score((requestDto.getSubject1Score() != null) ? requestDto.getSubject1Score() : score.getSubject1Score());
            score.setSubject2Score((requestDto.getSubject2Score() != null) ? requestDto.getSubject2Score() : score.getSubject2Score());
            score.setSubject3Score((requestDto.getSubject3Score() != null) ? requestDto.getSubject3Score() : score.getSubject3Score());
            score.setSubject4Score((requestDto.getSubject4Score() != null) ? requestDto.getSubject4Score() : score.getSubject4Score());
            score.setSubject5Score((requestDto.getSubject5Score() != null) ? requestDto.getSubject5Score() : score.getSubject5Score());
            score.setSubject6Score((requestDto.getSubject6Score() != null) ? requestDto.getSubject6Score() : score.getSubject6Score());

            Score updatedScore = scoreRepository.save(score);
            responseDto = modelMapper.map(updatedScore, ScoreResponse.class);
            responseDto.setMessage("Score updated successfully");

            return responseDto;
        }

        // create score
        newScore = new Score();
        newScore.setStudentId(requestDto.getStudentId());
        newScore.setTerm(requestDto.getTerm());
        newScore.setSubject1Score(requestDto.getSubject1Score());
        newScore.setSubject2Score(requestDto.getSubject2Score());
        newScore.setSubject3Score(requestDto.getSubject3Score());
        newScore.setSubject4Score(requestDto.getSubject4Score());
        newScore.setSubject5Score(requestDto.getSubject5Score());
        newScore.setSubject6Score(requestDto.getSubject6Score());

        Score savedScore = scoreRepository.save(newScore);
        responseDto = modelMapper.map(savedScore, ScoreResponse.class);
        responseDto.setMessage("Score created successfully");

        return responseDto;
    }

    public List<ScoreResponse> getAllScoresByStudentId(Integer studentId) {

        List<Score> allByStudentId = scoreRepository.findAllByStudentId(studentId);

        List<ScoreResponse> scoreResponseList = allByStudentId.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return scoreResponseList;
    }

    private ScoreResponse entityToDto(Score score) {
        ScoreResponse response = new ScoreResponse();
        response.setId(score.getId());
        response.setStudentId(score.getStudentId());
        response.setTerm(score.getTerm());
        response.setSubject1Score(score.getSubject1Score());
        response.setSubject2Score(score.getSubject2Score());
        response.setSubject3Score(score.getSubject3Score());
        response.setSubject4Score(score.getSubject4Score());
        response.setSubject5Score(score.getSubject5Score());
        response.setSubject6Score(score.getSubject6Score());
        response.setCreatedAt(score.getCreatedAt());

        return response;
    }
}

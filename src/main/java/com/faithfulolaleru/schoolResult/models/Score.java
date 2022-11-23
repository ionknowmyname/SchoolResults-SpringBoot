package com.faithfulolaleru.schoolResult.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score extends Audit implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "term")
    private int term;

    @Column(name = "subject1_score")
    private Integer subject1Score;

    @Column(name = "subject2_score")
    private Integer subject2Score;

    @Column(name = "subject3_score")
    private Integer subject3Score;

    @Column(name = "subject4_score")
    private Integer subject4Score;

    @Column(name = "subject5_score")
    private Integer subject5Score;

    @Column(name = "subject6_score")
    private Integer subject6Score;
}

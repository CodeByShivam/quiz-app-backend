package com.quizapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question text is required")
    @Column(name = "question_title", nullable = false, columnDefinition = "TEXT")
    private String questionTitle;

    @NotBlank(message = "Option A is required")
    @Column(name = "option1", nullable = false)
    private String option1;

    @NotBlank(message = "Option B is required")
    @Column(name = "option2", nullable = false)
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @NotBlank(message = "Correct option is required")
    @Column(name = "right_answer", nullable = false)
    private String rightAnswer; // e.g. "A", "B", "C", or "D"

    @Column(name = "category")
    private String category;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    public Question() {
    }

    public Question(String questionText, String optionA, String optionB, String optionC,
                     String optionD, String correctOption, String category, String difficultyLevel) {
        this.questionTitle = questionText;
        this.option1 = optionA;
        this.option2 = optionB;
        this.option3 = optionC;
        this.option4 = optionD;
        this.rightAnswer = correctOption;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionTitle;
    }

    public void setQuestionText(String questionText) {
        this.questionTitle = questionText;
    }

    public String getOptionA() {
        return option1;
    }

    public void setOptionA(String optionA) {
        this.option1 = optionA;
    }

    public String getOptionB() {
        return option2;
    }

    public void setOptionB(String optionB) {
        this.option2 = optionB;
    }

    public String getOptionC() {
        return option3;
    }

    public void setOptionC(String optionC) {
        this.option3 = optionC;
    }

    public String getOptionD() {
        return option4;
    }

    public void setOptionD(String optionD) {
        this.option4 = optionD;
    }

    public String getCorrectOption() {
        return rightAnswer;
    }

    public void setCorrectOption(String correctOption) {
        this.rightAnswer = correctOption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

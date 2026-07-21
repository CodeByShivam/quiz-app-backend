package com.quizapp.controller;

import com.quizapp.entity.Question;
import com.quizapp.repository.QuestionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*") // allow the frontend (served separately) to call this API
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) {
        Question saved = questionRepository.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionRepository.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id,
                                                     @Valid @RequestBody Question updated) {
        return questionRepository.findById(id)
                .map(existing -> {
                    existing.setQuestionText(updated.getQuestionText());
                    existing.setOptionA(updated.getOptionA());
                    existing.setOptionB(updated.getOptionB());
                    existing.setOptionC(updated.getOptionC());
                    existing.setOptionD(updated.getOptionD());
                    existing.setCorrectOption(updated.getCorrectOption());
                    existing.setCategory(updated.getCategory());
                    existing.setDifficultyLevel(updated.getDifficultyLevel());
                    Question saved = questionRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteQuestion(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        questionRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Question deleted successfully"));
    }
}

package org.lanadvice.surveyapi.spring.rest;

import lombok.RequiredArgsConstructor;
import org.lanadvice.surveyapi.spring.model.Questionnaire;
import org.lanadvice.surveyapi.spring.model.Survey;
import org.lanadvice.surveyapi.spring.service.QuestionnaireService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(QuestionnaireController.ROUTE_ROOT)
@RequiredArgsConstructor
public class QuestionnaireController {

    public static final String ROUTE_ROOT = "/api";
    public static final String ROUTE_Q = "/questionnaire";
    public static final String ROUTE_S = "/survey";
    public static final String ROUTE_SEARCH = "/search";
    public static final String SOURCE_PARAM = "s";
    public static final String ANSWER_PARAM = "a";
    public static final String QUESTION_PARAM = "q";

    private final QuestionnaireService questionnaireService;

    @GetMapping(value = QuestionnaireController.ROUTE_Q, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Questionnaire> findAll() {
        return questionnaireService.findAll();
    }

    @GetMapping(value = QuestionnaireController.ROUTE_SEARCH, produces = "application/vnd.ms-excel")
    public ResponseEntity getAsCsv(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                   @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        try {
            File f = questionnaireService.getAsCsv(start, end);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(f));
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noCache())
                    .header("Pragma", "must-revalidate")
                    .header("Content-Disposition", "attachment;filename=report.csv")
                    .contentLength(f.length())
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = QuestionnaireController.ROUTE_Q)
    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireService.create(questionnaire);
    }

    @PostMapping(value = QuestionnaireController.ROUTE_S)
    public Survey survey(@RequestParam(SOURCE_PARAM) String source,
                         @RequestParam(ANSWER_PARAM) String answer,
                         @RequestParam(QUESTION_PARAM) Long question) {
        return questionnaireService.addSurvey(source, answer, question);
    }
}

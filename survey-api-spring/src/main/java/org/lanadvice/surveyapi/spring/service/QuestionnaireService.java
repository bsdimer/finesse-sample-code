package org.lanadvice.surveyapi.spring.service;

import lombok.RequiredArgsConstructor;
import org.lanadvice.surveyapi.spring.model.Questionnaire;
import org.lanadvice.surveyapi.spring.model.Survey;
import org.lanadvice.surveyapi.spring.repository.QuestionRepository;
import org.lanadvice.surveyapi.spring.repository.QuestionnaireRepository;
import org.lanadvice.surveyapi.spring.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    private final QuestionRepository questionRepository;

    private final SurveyRepository surveyRepository;

    @Transactional
    public Questionnaire create(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
        return questionnaire;
    }

    public List<Questionnaire> findAll() {
        return (List<Questionnaire>) questionnaireRepository.findAll();
    }

    @Transactional
    public Survey addSurvey(String clid, String answer, Long question) {
        Survey survey = new Survey();
        survey.setSource(clid);
        survey.setAnswer(answer);
        questionRepository.findById(question).ifPresent(survey::setQuestion);
        surveyRepository.save(survey);
        return survey;
    }

    public List<Survey> findByCreatedBetween(LocalDateTime periodStart,
                                             LocalDateTime periodEnd) {
        return surveyRepository.findByCreatedBetween(periodStart, periodEnd);
    }

    public File getAsCsv(LocalDateTime start, LocalDateTime end) throws IOException {
        List<Survey> list = findByCreatedBetween(start, end);
        String string = "id,clid,questionId,questionText,answer,created\n";
        string = string + list.stream()
                .map(s -> MessageFormat.format("{0},{1},{2},{3},{4},{5}\n",
                        s.getId(),
                        s.getSource(),
                        s.getQuestion().getId(),
                        s.getQuestion().getText(),
                        s.getAnswer(),
                        s.getCreated().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )
                )
                .collect(Collectors.joining());
        File file = File.createTempFile(UUID.randomUUID().toString(), ".csv");
        OutputStream os = new FileOutputStream(file);
        os.write(string.getBytes(), 0, string.length());
        return file;
    }
}

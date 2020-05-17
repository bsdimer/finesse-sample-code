package org.lanadvice.rest;

import org.lanadvice.model.Questionnaire;
import org.lanadvice.model.Survey;
import org.lanadvice.service.QuestionnaireService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(QuestionnaireController.ROUTE_ROOT)
public class QuestionnaireController {

    public static final String ROUTE_ROOT = "/api";
    public static final String ROUTE_Q = "/questionnaire";
    public static final String ROUTE_S = "/survey";
    public static final String ROUTE_SEARCH = "/survey";

    @Inject
    QuestionnaireService questionnaireService;

    @GET
    @Path(QuestionnaireController.ROUTE_Q)
    @Produces("application/json")
    public List<Questionnaire> findAll() {
        return questionnaireService.findAll();
    }
/*
    @GET
    @Path(QuestionnaireController.ROUTE_SEARCH)
    @Produces("text/csv")
    public Response getCsv() {
        return Response.ok()
                .header("Content-Disposition", "attachment;filename=report.csv")
                .build();
    }*/

    @POST
    @Path(QuestionnaireController.ROUTE_Q)
    @Consumes("application/json")
    @Produces("application/json")
    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireService.create(questionnaire);
    }

    @POST
    @Path(QuestionnaireController.ROUTE_S)
    @Consumes("application/json")
    @Produces("application/json")
    public Survey survey(@QueryParam("s") String s, @QueryParam("a") String a, @QueryParam("q") Long q) {
        return questionnaireService.addSurvey(s, a, q);
    }
}

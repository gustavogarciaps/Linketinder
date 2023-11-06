package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import domain.Candidate
import repository.CandidateDAO
import repository.connection.DatabaseSingleton
import services.CandidateService
import utils.OperationStatus

@WebServlet("/candidates")
class CandidateController extends HttpServlet {

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private CandidateDAO candidateDAO = new CandidateDAO(database.getDatabaseConnection())
    private CandidateService candidateService = new CandidateService(candidateDAO);
    private ObjectMapper objectMapper = new ObjectMapper()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        println("JSON Request: " + jsonRequest.toString());

        Candidate candidate = objectMapper.readValue(jsonRequest.toString(), Candidate.class);
        OperationStatus status = candidateService.save(candidate);

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage());
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Candidate> candidates = []

        candidateService.findAll().each {it->
            candidates.add(candidateService.findAll(it))
        }

        String jsonResponse = objectMapper.writeValueAsString(candidates);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        Candidate candidate = objectMapper.readValue(jsonRequest.toString(), Candidate.class);
        OperationStatus status = candidateService.updateById(candidate)

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonRequest = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonRequest.append(line);
        }

        Candidate candidate = objectMapper.readValue(jsonRequest.toString(), Candidate.class);
        OperationStatus status = candidateService.deleteById(candidate.getId())

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

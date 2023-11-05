package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Jobs
import repository.JobsDAO
import repository.connection.DatabaseSingleton
import services.JobsService
import utils.OperationStatus

@WebServlet("/jobs")
class JobsController extends HttpServlet{

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private JobsDAO jobsDAO = new JobsDAO(database.getDatabaseConnection())
    private JobsService jobsService = new JobsService(jobsDAO)
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

        Jobs jobs = objectMapper.readValue(jsonRequest.toString(), Jobs.class);
        OperationStatus status = jobsService.save(jobs);

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage());
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Jobs> jobs = []

        jobsService.findAll().each { it->
            jobs.add(jobsService.findAll(it))
        }

        String jsonResponse = objectMapper.writeValueAsString(jobs);

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

        Jobs jobs = objectMapper.readValue(jsonRequest.toString(), Jobs.class);
        OperationStatus status = jobsService.updateById(jobs)

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

        Jobs jobs = objectMapper.readValue(jsonRequest.toString(), Jobs.class);
        OperationStatus status = jobsService.deleteById(jobs.getId())

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

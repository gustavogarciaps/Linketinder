package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Jobs
import repository.JobsDAO
import repository.connection.DatabaseSingleton
import services.JobsService

@WebServlet("/jobs")
class JobsController extends HttpServlet{

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private JobsDAO jobsDAO = new JobsDAO(database.getDatabaseConnection())
    private JobsService jobsService = new JobsService(jobsDAO)
    private ObjectMapper objectMapper = new ObjectMapper()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Jobs> jobs = jobsService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(jobs);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

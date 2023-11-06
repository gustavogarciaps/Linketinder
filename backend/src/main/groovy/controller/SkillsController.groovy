package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import domain.Skills
import repository.SkillsDAO
import repository.connection.DatabaseSingleton
import services.SkillsService
import utils.OperationStatus

@WebServlet("/skills")
class SkillsController extends HttpServlet {

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private SkillsDAO skillsDAO = new SkillsDAO(database.getDatabaseConnection())
    private SkillsService skillsService = new SkillsService(skillsDAO)
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

        Skills skills = objectMapper.readValue(jsonRequest.toString(), Skills.class);
        OperationStatus status = skillsService.save(skills);

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage());
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Skills> skills = skillsService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(skills);

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

        Skills skills = objectMapper.readValue(jsonRequest.toString(), Skills.class);
        OperationStatus status = skillsService.updateById(skills)

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

        Skills skills = objectMapper.readValue(jsonRequest.toString(), Skills.class);
        OperationStatus status = skillsService.deleteById(skills.getId())

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);

    }

}

package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Skills
import repository.SkillsDAO
import repository.connection.DatabaseSingleton
import services.SkillsService

@WebServlet("/skills")
class SkillsController extends HttpServlet {

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private SkillsDAO skillsDAO = new SkillsDAO(database.getDatabaseConnection())
    private SkillsService skillsService = new SkillsService(skillsDAO)
    private ObjectMapper objectMapper = new ObjectMapper()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Skills> skills = skillsService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(skills);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

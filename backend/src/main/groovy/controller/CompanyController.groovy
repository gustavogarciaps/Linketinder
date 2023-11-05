package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Company
import repository.CompanyDAO
import repository.connection.DatabaseSingleton
import services.CompanyService

@WebServlet("/companies")
class CompanyController extends HttpServlet {

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private CompanyDAO companyDAO = new CompanyDAO(database.getDatabaseConnection())
    private CompanyService companyService = new CompanyService(companyDAO)
    private ObjectMapper objectMapper = new ObjectMapper()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(companies);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

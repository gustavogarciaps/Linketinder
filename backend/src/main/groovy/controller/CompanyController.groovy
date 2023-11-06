package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import domain.Company
import repository.CompanyDAO
import repository.connection.DatabaseSingleton
import services.CompanyService
import utils.OperationStatus

@WebServlet("/companies")
class CompanyController extends HttpServlet {

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private CompanyDAO companyDAO = new CompanyDAO(database.getDatabaseConnection())
    private CompanyService companyService = new CompanyService(companyDAO)
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

        Company company = objectMapper.readValue(jsonRequest.toString(), Company.class);
        OperationStatus status = companyService.save(company);

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage());
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(companies);

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

        Company company = objectMapper.readValue(jsonRequest.toString(), Company.class);
        OperationStatus status = companyService.updateById(company)

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

        Company company = objectMapper.readValue(jsonRequest.toString(), Company.class);
        OperationStatus status = companyService.deleteById(company.getId())

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

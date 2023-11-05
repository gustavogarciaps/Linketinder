package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Person
import repository.PersonDAO
import repository.connection.DatabaseSingleton
import services.PersonService
import utils.OperationStatus

@WebServlet("/people")
class PersonController extends HttpServlet{

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private PersonDAO personDAO = new PersonDAO(database.getDatabaseConnection())
    private PersonService personService = new PersonService(personDAO)
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

        Person person = objectMapper.readValue(jsonRequest.toString(), Person.class);
        OperationStatus status = personService.save(person);

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage());
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> people = personService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(people);

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

        Person person = objectMapper.readValue(jsonRequest.toString(), Person.class);
        OperationStatus status = personService.updateById(person)

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

        Person person = objectMapper.readValue(jsonRequest.toString(), Person.class);
        OperationStatus status = personService.deleteById(person.getId())

        String jsonResponse = objectMapper.writeValueAsString(status.getMessage())
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

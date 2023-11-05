package controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.*
import model.Person
import repository.PersonDAO
import repository.connection.DatabaseSingleton
import services.PersonService

@WebServlet("/people")
class PersonController extends HttpServlet{

    private DatabaseSingleton database = DatabaseSingleton.getInstance()
    private PersonDAO personDAO = new PersonDAO(database.getDatabaseConnection())
    private PersonService personService = new PersonService(personDAO)
    private ObjectMapper objectMapper = new ObjectMapper()

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> people = personService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(people);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}

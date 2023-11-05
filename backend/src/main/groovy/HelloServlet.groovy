import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
class HelloServlet extends HttpServlet {
    private String message;

    void init() {
        message = "Hello World!";
    }

    void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json"); // Definir o tipo de conteúdo como JSON

        // Criar um objeto com a mensagem
        HelloMessage helloMessage = new HelloMessage(message);

        // Criar um objeto Gson
        Gson gson = new Gson();

        // Converter o objeto para JSON
        String jsonResponse = gson.toJson(helloMessage);

        // Escrever a resposta JSON no HttpServletResponse
        response.getWriter().write(jsonResponse);
    }

    void destroy() {
    }
}

class HelloMessage {
    private String message;

    public HelloMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

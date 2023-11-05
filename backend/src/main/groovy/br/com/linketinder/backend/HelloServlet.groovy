package br.com.linketinder.backend

import jakarta.servlet.http.*
import jakarta.servlet.annotation.*

@WebServlet(name = "helloServlet", value = "/hello-servlet")
class HelloServlet extends HttpServlet {
    private String message

    void init() {
        message = "Hello World!"
    }

    void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html")

        // Hello
        PrintWriter out = response.getWriter()
        out.println("<html><body>")
        out.println("<h1>" + message + "</h1>")
        out.println("</body></html>");
    }

    void destroy() {
    }
}
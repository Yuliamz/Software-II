package Taller3;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet (name = "ProcesarFormulario", urlPatterns = {"/ProcesarFormulario"})
public class ProcesarFormulario extends HttpServlet {  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Servlet Prueba</title>");
            out.print("</head>");
            out.print("<body>");
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("apellido");
            Integer edad = Integer.parseInt(req.getParameter("edad"));
            out.print("Bienvenido, " +nombre + " " + apellido +
                    ", usted es " +(edad > 18 ? "mayor" : "menor")+" de edad");
            out.print("</body>");
            out.print("</html>");
        } 
    }
}
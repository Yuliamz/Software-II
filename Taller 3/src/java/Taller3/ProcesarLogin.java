package Taller3;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet (name = "ProcesarLogin", urlPatterns = {"/ProcesarLogin"})
public class ProcesarLogin extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Inicia de Seccion con Exito</title>");
            out.print("</head>");
            out.print("<body>");
            String usuario = req.getParameter("Usuario");
            String contraseña = req.getParameter("Contrasena");
            /*Si hay una base de datos la consulta y
            validacion se deben realizar acá*/
            if ((usuario.compareTo("Arias")==0) &&
                    (contraseña.compareTo("abc123")==0)) {
                //Almacena usuario(?)
                req.getSession().setAttribute("nombre", usuario);
                //Avanza a el formulario si es correcto.
                resp.sendRedirect("./index.html");
            } else {
               resp.sendRedirect("./login.html");
            }
            out.print("</body>");
            out.print("</html>");
        } 
    }
}
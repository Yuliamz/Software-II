package Taller3;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Filtro que procesa los datos del Login.
 * @author Carlos Andrés Arias
 */
@WebFilter(filterName = "FiltroAutenticacion", 
        urlPatterns = {"/index.html","/ProcesarFormulario","/"})
public class FiltroAutenticacion implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String Usuario = (String) req.getSession().getAttribute("Usuario");
        String Contraseña = (String) req.getSession().getAttribute("Contraseña");
        if (req.getSession().getAttribute("nombre") == null) {
            // Usuario no valido.
            resp.sendRedirect("./login.html");
            //Agregar usuario..
        } else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }
}
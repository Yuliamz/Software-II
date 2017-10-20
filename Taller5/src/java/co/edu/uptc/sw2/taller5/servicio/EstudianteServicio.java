package co.edu.uptc.sw2.taller5.servicio;

import co.edu.uptc.sw2.taller5.dto.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author daperador
 * @generated
 */
@Path("/Estudiante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteServicio {

    /**
     * retorna una lista con los Estudiante que se encuentran en la base de
     * datos
     *
     * @return retorna una lista de EstudianteDTO
     * @generated
     */
    @GET
    public List<EstudianteDTO> obtenerTodosEstudiantes() {
        return ProveedorInformacion.instance().obtenerTodos(EstudianteDTO.class);
    }

    /**
     * retorna una lista con los Estudiantes filtrados por nombre y apellido
     *
     * @return retorna una lista de EstudianteDTO
     * @generated
     */
    @GET
    @Path("/buscar")
    public List<EstudianteDTO> obtenerTodosEstudiantesFiltrado(@QueryParam("nombre") String nombre,
                                                               @QueryParam("apellido") String apellido) {
        List<EstudianteDTO> dTOs = ProveedorInformacion.instance().obtenerTodos(EstudianteDTO.class);    
        
        if ((nombre==null || nombre.isEmpty()) && (apellido==null  || apellido.isEmpty())) {
            return new ArrayList<>();
        }
        
        if((apellido==null  || apellido.isEmpty()) && (nombre!=null && !nombre.isEmpty())){
            return estudiantesPorNombre(nombre);
        }
        
        if ((nombre==null || nombre.isEmpty()) && (apellido!=null && !apellido.isEmpty())) {
            return estudiantesPorApellido(apellido);
        }
        
        return dTOs.stream()
                .filter(e -> e.getNombres().contains(nombre) && e.getApellidos().contains(apellido))
                .collect(Collectors.toList());
    }

    /**
     * retorna una lista con los Estudiantes filtrados por nombre
     *
     * @return retorna una lista de EstudianteDTO
     * @generated
     */

    public List<EstudianteDTO> estudiantesPorNombre(String nombre) {
            List<EstudianteDTO> dTOs = ProveedorInformacion.instance().obtenerTodos(EstudianteDTO.class);
            return dTOs.stream()
                    .filter(e -> e.getNombres().contains(nombre))
                    .collect(Collectors.toList());
    }
    
    /**
     * retorna una lista con los Estudiantes filtrados por nombre
     *
     * @return retorna una lista de EstudianteDTO
     * @generated
     */
    public List<EstudianteDTO> estudiantesPorApellido(@QueryParam("apellido") String apellido) {
            List<EstudianteDTO> dTOs = ProveedorInformacion.instance().obtenerTodos(EstudianteDTO.class);
            return dTOs.stream()
                    .filter(e -> e.getApellidos().contains(apellido))
                    .collect(Collectors.toList());
    }

    /**
     * @param id identificador del elemento Estudiante
     * @return Estudiante del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public EstudianteDTO obtenerEstudiante(@PathParam("id") Long id) {
        return (EstudianteDTO) ProveedorInformacion.instance().obtener(EstudianteDTO.class, id);
    }

    /**
     * almacena la informacion de Estudiante
     *
     * @param dto Estudiante a guardar
     * @return Estudiante con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public EstudianteDTO guardarEstudiante(EstudianteDTO dto) {
        return (EstudianteDTO) ProveedorInformacion.instance().guardar(dto);
    }

    /**
     * elimina el registro Estudiante con el identificador dado
     *
     * @param id identificador del Estudiante
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarEstudiante(@PathParam("id") Long id) {
        ProveedorInformacion.instance().eliminar(EstudianteDTO.class, id);
    }

}

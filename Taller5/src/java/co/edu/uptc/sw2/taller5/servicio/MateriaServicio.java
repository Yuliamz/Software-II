package co.edu.uptc.sw2.taller5.servicio;

import co.edu.uptc.sw2.taller5.dto.*;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author daperador
 * @generated
 */
@Path("/Materia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MateriaServicio {

    /**
     * retorna una lista con los Materia que se encuentran en la base de datos
     *
     * @return retorna una lista de MateriaDTO
     * @generated
     */
    @GET
    public List<MateriaDTO> obtenerTodosMaterias() {
        return ProveedorInformacion.instance().obtenerTodos(MateriaDTO.class);
    }

    /**
     * @param id identificador del elemento Materia
     * @return Materia del id dado
     * @generated
     */
    @GET
    @Path("/{id}")
    public MateriaDTO obtenerMateria(@PathParam("id") Long id) {
        return (MateriaDTO) ProveedorInformacion.instance().obtener(MateriaDTO.class, id);
    }

    /**
     * almacena la informacion de Materia
     *
     * @param dto Materia a guardar
     * @return Materia con los cambios realizados por el proceso de guardar
     * @generated
     */
    @POST
    public MateriaDTO guardarMateria(MateriaDTO dto) {
        return (MateriaDTO) ProveedorInformacion.instance().guardar(dto);
    }

    /**
     * elimina el registro Materia con el identificador dado
     *
     * @param id identificador del Materia
     * @generated
     */
    @DELETE
    @Path("/{id}")
    public void borrarMateria(@PathParam("id") Long id) {
        ProveedorInformacion.instance().eliminar(MateriaDTO.class, id);
    }

}

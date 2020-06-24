package dominio.factorys;

import dominio.entidades.Docente;
import dominio.estrategias.asignacionDeDocentes.AsignadorDeDocente;
import dominio.estrategias.asignacionDeDocentes.PorMayorDisponibilidad;

import java.util.List;

public class AsignadorDocenteFactory {

    public static AsignadorDeDocente obtenerAsignador(String nombre, List<Docente> docentes) {
        AsignadorDeDocente asignador;
        switch (nombre) {
            case "menorDisponibilidad":
                asignador = new PorMayorDisponibilidad(docentes);
                break;
            default:
                asignador = new PorMayorDisponibilidad(docentes);
                break;
        }
        return asignador;
    }
}

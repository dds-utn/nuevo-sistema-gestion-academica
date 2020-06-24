package dominio.estrategias.asignacionDeDocentes;

import dominio.Repositorios.Repository;
import dominio.entidades.Curso;
import dominio.entidades.Docente;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PorMenorDisponibilidad extends AsignadorDeDocente {

    public PorMenorDisponibilidad(List<Docente> docentes) {
        super(docentes);
    }

    @Override
    public void asignarDocente(Curso curso) throws ExcepcionDeAsignadorDeDocente {

        List<Docente> docentes = super.docentesDisponibles(curso);

        Docente docenteSeleccionado = Collections.min(docentes,
                Comparator.comparing(Docente::cantidadCursos)
        );

        super.asignarDocente(curso,docenteSeleccionado);
    }
}

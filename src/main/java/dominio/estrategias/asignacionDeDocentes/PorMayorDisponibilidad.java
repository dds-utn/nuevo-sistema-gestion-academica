package dominio.estrategias.asignacionDeDocentes;

import dominio.Repositorios.Repository;
import dominio.entidades.Curso;
import dominio.entidades.Docente;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PorMayorDisponibilidad extends AsignadorDeDocente {

    public PorMayorDisponibilidad(List<Docente> docentes) {
        super(docentes);
    }

    @Override
    protected void ordenarDocentes(List<Docente> docentes) {
        Collections.sort(docentes, Comparator.comparing(Docente::cantidadCursos));
    }

    @Override
    protected Docente seleccionarDocente(List<Docente> docentesOrdenados){
        Docente docenteSeleccionado = docentesOrdenados.get(0);

        return docenteSeleccionado;
    }

    @Override
    protected Boolean puedeDarCurso(Curso unCurso, Docente unDocente) {
        return super.puedeDarCurso(unCurso, unDocente); //podriamos agregarle validaciones
    }
}

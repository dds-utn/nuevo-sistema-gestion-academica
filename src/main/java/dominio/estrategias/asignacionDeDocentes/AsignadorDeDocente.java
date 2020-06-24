package dominio.estrategias.asignacionDeDocentes;

import dominio.entidades.Curso;
import dominio.entidades.Docente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AsignadorDeDocente {
    protected List<Docente> docentes;

    public AsignadorDeDocente(List<Docente> docentes) {
        this.docentes = docentes;
    }

    /**
     * Template method
     */

    public void asignarDocente(Curso unCurso) throws ExcepcionDeAsignadorDeDocente {
        List<Docente> docentesDisponibles = this.docentesDisponibles(unCurso); //step 1

        this.ordenarDocentes(docentesDisponibles); //step  2

        Docente docenteSeleccionado = this.seleccionarDocente(docentesDisponibles); //step 3

        this.asignarDocente(unCurso, docenteSeleccionado); //step 4
    }

    protected abstract void ordenarDocentes(List<Docente> docentes);

    protected abstract Docente seleccionarDocente(List<Docente> docentes);

    /**
     *
     */
    public void asignarDocente(Curso curso, Docente unDocente) throws ExcepcionDeAsignadorDeDocente {
        if (!this.puedeDarCurso(curso, unDocente)) {
            throw new ExcepcionDeAsignadorDeDocente("El docente no puede dar este curso.");
        }
        curso.setDocente(unDocente);
        unDocente.agregarCursos(curso);
    }

    protected Boolean puedeDarCurso(Curso unCurso, Docente unDocente) {
        return unDocente.estasDisponible(unCurso.getDia(), unCurso.getTurno()) &&
                unDocente.dictasMateria(unCurso.getMateria());
    }

    protected List<Docente> docentesDisponibles(Curso unCurso) throws ExcepcionDeAsignadorDeDocente {
        List<Docente> docentes = new ArrayList<>();
        docentes.addAll(this.docentes.stream()
                .filter(docente -> this.puedeDarCurso(unCurso, docente))
                .collect(Collectors.toList())
        );

        if (docentes.size() <= 0) {
            throw new ExcepcionDeAsignadorDeDocente("No hay docentes disponibles");
        }
        return docentes;
    }

}

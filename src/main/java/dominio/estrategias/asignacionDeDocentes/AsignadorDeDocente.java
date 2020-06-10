package dominio.estrategias.asignacionDeDocentes;

import dominio.Repositorios.Repository;
import dominio.entidades.Curso;
import dominio.entidades.Docente;

import java.util.ArrayList;
import java.util.List;

public abstract class AsignadorDeDocente {
    protected List<Docente> docentes;

    public AsignadorDeDocente(List<Docente> docentes)
    {
        this.docentes = docentes;
    }

    public abstract void asignarDocente(Curso curso) throws ExcepcionDeAsignadorDeDocente;

    public void asignarDocente(Curso curso, Docente unDocente) throws ExcepcionDeAsignadorDeDocente
    {
        if(!this.puedeDarCurso(curso,unDocente))
        {
            throw new ExcepcionDeAsignadorDeDocente("El docente no puede dar este curso.");
        }
        curso.setDocente(unDocente);
        unDocente.agregarCursos(curso);
    }

    /**
     *  Protected functions
     *
     */
    protected Boolean puedeDarCurso(Curso unCurso, Docente unDocente)
    {
        return unDocente.estasDisponible(unCurso.getDia(), unCurso.getTurno()) &&
                unDocente.dictasMateria(unCurso.getMateria());
    };
    protected List<Docente> docentesDisponibles(Curso unCurso) throws ExcepcionDeAsignadorDeDocente
    {
        List<Docente> docentes = new ArrayList<>();
        docentes.addAll(this.docentes);

        if(docentes.size() <= 0)
        {
            throw new ExcepcionDeAsignadorDeDocente("No hay docentes disponibles");
        }
        return docentes;
    }

}

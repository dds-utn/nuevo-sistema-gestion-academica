package dominio.factorys;

import dominio.Repositorios.Repository;
import dominio.builders.CursoBuilder;
import dominio.entidades.Docente;
import dominio.estrategias.asignacionDeAlumnos.PuntajeMayor;
import dominio.estrategias.asignacionDeDocentes.PorMenorDisponibilidad;

import java.util.List;

public class CursoBuilderFactory {

    private Integer minimoDeAlumnos;
    private Integer maximoDeAlumnos;
    private List<Docente> docentes;
    private String algoritmoSelectorDeAlumnos = "puntajeMayor";
    private String algoritmoAsignadorDocente  = "menorDisponibilidad";

    public CursoBuilderFactory(List<Docente> docentes)
    {
        this.minimoDeAlumnos = 2;
        this.maximoDeAlumnos = 10;
        this.docentes = docentes;
    }

    public CursoBuilder createBuilder()
    {
        CursoBuilder builder = new CursoBuilder();

        builder.setSelectorDeAlumnos(SelectorDeAlumnosFactory.obtenerSelector(this.algoritmoSelectorDeAlumnos));
        builder.setCantidadMinimaAlumnos(this.getMinimoDeAlumnos());
        builder.setCapacidadAlumnos(this.getMaximoDeAlumnos());
        builder.setAsignadorDeDocente(AsignadorDocenteFactory.obtenerAsignador(this.algoritmoAsignadorDocente, this.docentes));

        return builder;
    }



    public void setMinimoDeAlumnos(Integer minimo)
    {
        this.minimoDeAlumnos = minimo;
    }

    public void setMaximoDeAlumnos(Integer maximo)
    {
        this.maximoDeAlumnos = maximo;
    }

    public void setAlgoritmoAsignadorDocente(String algoritmoAsignadorDocente) {
        this.algoritmoAsignadorDocente = algoritmoAsignadorDocente;
    }

    public void setAlgoritmoSelectorDeAlumnos(String algoritmoSelectorDeAlumnos) {
        this.algoritmoSelectorDeAlumnos = algoritmoSelectorDeAlumnos;
    }

    private Integer getMinimoDeAlumnos()
    {
        return this.minimoDeAlumnos; // puede salir de un config
    }

    private Integer getMaximoDeAlumnos()
    {
        return this.maximoDeAlumnos; // puede salir de un config
    }

    private List<Docente> getDocentes() {
        return docentes;
    }
}

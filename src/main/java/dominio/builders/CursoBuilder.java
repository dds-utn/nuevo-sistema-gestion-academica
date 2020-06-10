package dominio.builders;

import dominio.entidades.*;
import dominio.estrategias.asignacionDeAlumnos.SelectorDeAlumnos;
import dominio.estrategias.asignacionDeDocentes.ExcepcionDeAsignadorDeDocente;
import dominio.estrategias.asignacionDeDocentes.AsignadorDeDocente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CursoBuilder {
    private Integer capacidadAlumnos;
    private Integer cantidadMinimaAlumnos;
    private Curso curso;
    private SelectorDeAlumnos selectorDeAlumnos;
    private AsignadorDeDocente asignadorDeDocente;

    public CursoBuilder()
    {
        this.curso = new Curso();
    }

    /**
     *  Getters and setters
     *
    */
    public Integer getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public void setCapacidadAlumnos(Integer capacidadAlumnos) {
        this.capacidadAlumnos = capacidadAlumnos;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getCantidadMinimaAlumnos() {
        return cantidadMinimaAlumnos;
    }

    public void setCantidadMinimaAlumnos(Integer cantidadMinimaAlumnos) {
        this.cantidadMinimaAlumnos = cantidadMinimaAlumnos;
    }

    public SelectorDeAlumnos getSelectorDeAlumnos() {
        return selectorDeAlumnos;
    }

    public void setSelectorDeAlumnos(SelectorDeAlumnos selectorDeAlumnos) {
        this.selectorDeAlumnos = selectorDeAlumnos;
    }

    public AsignadorDeDocente getAsignadorDeDocente() {
        return asignadorDeDocente;
    }

    public void setAsignadorDeDocente(AsignadorDeDocente asignadorDeDocente) {
        this.asignadorDeDocente = asignadorDeDocente;
    }

    /**
     *  Functions! :D
     *
    */

    public CursoBuilder agregarTitulo(String titulo)
    {
        this.curso.setTitulo(titulo);
        return this;
    }

    public CursoBuilder agregarMateria(Materia materia)
    {
        this.curso.setMateria(materia);
        return this;
    }

    public CursoBuilder agregarCicloElectivo(CicloLectivo ciclo) throws ExcepcionDeCreacionDeCurso
    {
        Integer añoActual = LocalDate.now().getYear();
        if(añoActual.intValue() != ciclo.getAnio().intValue())
        {
            throw new ExcepcionDeCreacionDeCurso("No es un ciclo electivo valido ");
        }

        this.curso.setCicloLectivo(ciclo);

        return this;
    }

    public CursoBuilder agregarDocente() throws ExcepcionDeCreacionDeCurso{

        try {
            this.getAsignadorDeDocente().asignarDocente(this.curso);
        }
        catch(ExcepcionDeAsignadorDeDocente ex)
        {
            throw new ExcepcionDeCreacionDeCurso(ex.getMessage());
        }

        return this;
    }

    public CursoBuilder agregarDocente(Docente docente) throws ExcepcionDeCreacionDeCurso
    {

        try {
            this.getAsignadorDeDocente().asignarDocente(this.curso, docente);
        }
        catch(ExcepcionDeAsignadorDeDocente ex)
        {
            throw new ExcepcionDeCreacionDeCurso(ex.getMessage());
        }
        return this;
    }

    public CursoBuilder agregarDia(Dia dia)
    {
        this.curso.setDia(dia);

        return this;
    }

    public CursoBuilder agregarTurno(Turno turno)
    {
        this.curso.setTurno(turno);
        return this;
    }

    public CursoBuilder agregarHoraDeInicio(LocalTime hora)
    {
        this.curso.setHoraInicio(hora);
        return this;
    }

    public CursoBuilder agregarAyudantes(Ayudante ayudantes)
    {
        this.curso.agregarAyudantes(ayudantes);
        return this;
    }

    public CursoBuilder agregarAlumnos(Alumno ... _alumnos) throws ExcepcionDeCreacionDeCurso
    {
        List<Alumno> alumnos = Arrays.asList(_alumnos);

        Boolean algunoYaEstaInscripto = alumnos.stream().anyMatch(alumno -> alumno.tenesCurso(this.curso));
        if(algunoYaEstaInscripto)
        {
            throw new ExcepcionDeCreacionDeCurso("Un/Unos Alumnos ya estan inscriptos a este curso!");
        }

        Integer cuposDisponibles = this.getCapacidadAlumnos() - this.curso.cantidadAlumnos();

        /**checkeo por las dudas, no es necesario si usamos el builder!*/
        cuposDisponibles = cuposDisponibles < 0 ? 0 : cuposDisponibles;

        if( cuposDisponibles <= 0)
        {
            throw new ExcepcionDeCreacionDeCurso("El Curso ya alcanzo su capacidad maxima!");
        }

        List<Alumno> alumnosSeleccionados =
                this.selectorDeAlumnos.seleccionar(alumnos, cuposDisponibles);

        this.curso.agregarAlumnos(alumnosSeleccionados);

        return this;
    }

    public Curso build() throws ExcepcionDeCreacionDeCurso{
        if(this.esNull(this.curso::getDocente))
        {
            throw new ExcepcionDeCreacionDeCurso("No tiene docente asignado");
        }

        if(!this.tieneMinimoDeAlumnos())
        {
            throw new ExcepcionDeCreacionDeCurso("No hay la minima cantidad de alumnos");
        }

        if(this.esNull(this.curso::getCicloLectivo))
        {
            throw new ExcepcionDeCreacionDeCurso("No se asignó el ciclo electivo");
        }

        if(this.esNull(this.curso::getMateria))
        {
            throw new ExcepcionDeCreacionDeCurso("No se asignó la materia");
        }

        if(this.esNull(this.curso::getTitulo))
        {
            throw new ExcepcionDeCreacionDeCurso("No se asignó el titulo al cursoo");
        }

        if(this.esNull(this.curso::getDia))
        {
            throw new ExcepcionDeCreacionDeCurso("No se asignó el dia del curso");
        }

        if(this.esNull(this.curso::getTurno))
        {
            throw new ExcepcionDeCreacionDeCurso("No se especifico el turno");
        }

        if(this.esNull(this.curso::getHoraInicio))
        {
            throw new ExcepcionDeCreacionDeCurso("No se especifico el horario de inicio");
        }

        return this.curso;
    }
    /**
     *  private Functions! c:
     *
     */
    private Boolean esNull(Supplier<Object> callback)
    {
        return callback.get() == null;
    }

    private Boolean tieneMinimoDeAlumnos()
    {
        return this.curso.cantidadAlumnos() >= this.getCantidadMinimaAlumnos();
    }

}

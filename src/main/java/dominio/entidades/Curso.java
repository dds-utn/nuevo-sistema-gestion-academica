package dominio.entidades;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {
    private String titulo;
    private Materia materia;
    private CicloLectivo cicloLectivo;
    private Docente docente;
    private List<Ayudante> ayudantes;
    private List<Alumno> alumnos;
    private Dia dia;
    private LocalTime horaInicio;
    private Turno turno;

    public Curso() {
        this.alumnos    = new ArrayList<>();
        this.ayudantes  = new ArrayList<>();
    }

    /**
     *  Getters and setters
     *
     */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public CicloLectivo getCicloLectivo() {
        return cicloLectivo;
    }

    public void setCicloLectivo(CicloLectivo cicloLectivo) {
        this.cicloLectivo = cicloLectivo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public void agregarAlumnos(List<Alumno> alumnosSeleccionados) {
        alumnosSeleccionados.forEach(alumno -> this.agregarAlumno(alumno));
    }

    private void agregarAlumno(Alumno alumno) {
        this.getAlumnos().add(alumno);
    }

    public void agregarAyudantes(Ayudante ... ayudantes) {
        Collections.addAll(this.ayudantes, ayudantes);
    }

    public List<Ayudante> getAyudantes() {
        return this.ayudantes;
    }

    /**
     *  Functions! :D
     */

    public Boolean estasActivo()
    {
        return this.getCicloLectivo().sosCicloActual();
    }
    public Integer cantidadAlumnos() {return this.getAlumnos().size();}
    public Boolean tenesAlumno(Alumno alumno)
    {
        return this.getAlumnos().contains(alumno);
    }


}

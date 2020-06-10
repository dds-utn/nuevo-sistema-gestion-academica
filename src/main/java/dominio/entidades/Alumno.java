package dominio.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String nombre;
    private String apellido;
    private Integer legajo;
    private LocalDate anioDeIngreso;
    private LocalDate fechaDeNacimiento;
    private Genero genero;
    private List<Discapacidad> discapacidades;
    private Integer puntaje;
    private List<Curso> cursos;

    public Alumno() {
        this.cursos = new ArrayList<>();
        this.discapacidades = new ArrayList<>();
    }

    /**
     *  Getters and setters
     *
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public int getPuntajeAsInt()
    {
        return this.puntaje.intValue();
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     *
     *
     */
    public Boolean tenesCurso(Curso curso)
    {
        return this.cursos.contains(curso);
    }

}

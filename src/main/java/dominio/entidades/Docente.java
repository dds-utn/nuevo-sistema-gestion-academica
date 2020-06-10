package dominio.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Docente {

    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private String descripcionTituloHabilitante;
    private LocalDate anioDictadoPrimerMateria;
    private Genero genero;
    private String apodo;
    private List<Materia> materiasDeInteres;
    private List<Materia> materiasDictadas;
    private List<Discapacidad> discapacidadesTratadas;
    private List<Curso> cursos;

    public Docente()
    {
        this.materiasDeInteres      = new ArrayList<>();
        this.cursos                 = new ArrayList<>();
        this.materiasDictadas       = new ArrayList<>();
        this.discapacidadesTratadas = new ArrayList<>();
    }

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

    public List<Curso> getCursos() {
        return this.cursos.stream().filter(curso -> curso.estasActivo())
                .collect(Collectors.toList());
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Materia> getMateriasDeInteres() {
        return this.materiasDeInteres;
    }

    public void agregarMateriasDeInteres(Materia ... materias) {
        this.materiasDeInteres.addAll(Arrays.asList(materias));
    }

    public void agregarCursos(Curso ... curso)
    {
        this.cursos.addAll(Arrays.asList(curso));
    }

    public void agregarDiscapacidadConHabilidad(Discapacidad ... discapacidades) {
        this.discapacidadesTratadas.addAll(Arrays.asList(discapacidades));
    }

    /**
     *  Functions! :D
     */
    public Boolean estasDisponible(Dia unDia, Turno unTurno)
    {
        if((unDia == null) || (unTurno == null))
        {
            return false;
        }

        return this.getCursos().stream().noneMatch(curso ->
                (curso.getDia() == unDia) && (curso.getTurno() == unTurno)
        );
    }

    public Boolean dictasMateria(Materia materia)
    {
        return this.materiasDeInteres.contains(materia);
    }

    public Integer cantidadCursos() {
        return this.getCursos().size();
    }

    public List<Materia> getMateriasDictadas() {
        return materiasDictadas;
    }

    public boolean tenesHabilidadParaTratar(Discapacidad discapacidad) {
        return this.discapacidadesTratadas.contains(discapacidad);
    }

}

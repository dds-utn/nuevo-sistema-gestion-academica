package dominio.estrategias.asignacionDeAlumnos;

import dominio.entidades.Alumno;

import java.util.List;

public interface SelectorDeAlumnos {

    public List<Alumno> seleccionar(List<Alumno> alumnos, Integer cantidad);

}

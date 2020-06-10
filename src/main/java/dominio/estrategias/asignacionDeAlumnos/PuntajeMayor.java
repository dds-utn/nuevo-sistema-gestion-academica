package dominio.estrategias.asignacionDeAlumnos;

import dominio.entidades.Alumno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PuntajeMayor implements SelectorDeAlumnos {

    public PuntajeMayor()
    {

    }

    @Override
    public List<Alumno> seleccionar(List<Alumno> _alumnos, Integer cantidad) {

        List<Alumno> alumnos = new ArrayList<>();
        alumnos.addAll(_alumnos);

        Collections.sort(alumnos, Comparator.comparingInt(Alumno::getPuntaje).reversed());

        Integer cantidadSublista = cantidad - 1;

        cantidadSublista = cantidadSublista > alumnos.size() ? alumnos.size() : cantidadSublista; //para no pasarnos

        List<Alumno> alumnosSeleccionados = alumnos.subList(0, cantidadSublista);

        return alumnosSeleccionados;
    }

}

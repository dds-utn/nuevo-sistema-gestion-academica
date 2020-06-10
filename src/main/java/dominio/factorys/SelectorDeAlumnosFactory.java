package dominio.factorys;

import dominio.estrategias.asignacionDeAlumnos.PuntajeMayor;
import dominio.estrategias.asignacionDeAlumnos.SelectorDeAlumnos;

public class SelectorDeAlumnosFactory {

    public static SelectorDeAlumnos obtenerSelector(String selector){
        SelectorDeAlumnos selectorDeAlumnos;
        switch (selector){
            case "puntajeMayor": selectorDeAlumnos = new PuntajeMayor(); break;
            default: selectorDeAlumnos = new PuntajeMayor(); break;
        }
        return selectorDeAlumnos;
    }
}

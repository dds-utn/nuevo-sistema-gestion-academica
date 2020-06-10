package dominio.entidades;

import java.time.LocalDate;

public class CicloLectivo {
    private Integer anio;
    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean sosCicloActual()
    {
        return LocalDate.now().getYear() == this.getAnio() ;
    }
}

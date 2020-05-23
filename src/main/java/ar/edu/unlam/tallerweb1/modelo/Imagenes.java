package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Imagenes() {

    }
    public Imagenes(String link) {
        this.nombre=link;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

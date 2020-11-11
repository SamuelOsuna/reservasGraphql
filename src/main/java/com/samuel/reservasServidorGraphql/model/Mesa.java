package com.samuel.reservasServidorGraphql.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Mesa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nmesa")
    private int nmesa;

    @NotBlank
    @Column(name = "comensales")
    private int comensales;

    @NotBlank
    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    public Mesa() {
    }

    public Mesa(@NotBlank int nmesa, @NotBlank int comensales, @NotBlank String imagen, Restaurante restaurante) {
        this.nmesa = nmesa;
        this.comensales = comensales;
        this.imagen = imagen;
        this.restaurante = restaurante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNmesa() {
        return nmesa;
    }

    public void setNmesa(int nmesa) {
        this.nmesa = nmesa;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", nmesa=" + nmesa +
                ", comensales=" + comensales +
                ", imagen='" + imagen + '\'' +
                ", restaurante=" + restaurante +
                '}';
    }
}

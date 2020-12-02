package com.samuel.reservasServidorGraphql.model;

import javax.persistence.*;

@Entity
public class Reserva {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "fechacreacion")
    private String fechacreacion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "notas")
    private String notas;

    @Column(name = "aceptada")
    private Boolean aceptada;

    public Reserva() {
    }

    public Reserva(Usuario usuario, Mesa mesa, Restaurante restaurante, String nombre, String fecha, String tipo) {
        this.usuario = usuario;
        this.mesa = mesa;
        this.restaurante = restaurante;
        this.nombre = restaurante.getNombre();
        this.fecha = fecha;
        this.tipo = tipo;
        this.aceptada = null;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", mesa=" + mesa +
                ", restaurante=" + restaurante +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", fechacreacion='" + fechacreacion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", notas='" + notas + '\'' +
                ", aceptada=" + aceptada +
                '}';
    }
}

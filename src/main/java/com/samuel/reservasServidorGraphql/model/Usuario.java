package com.samuel.reservasServidorGraphql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "restaurante")
    private Integer restaurante;

    @Column(name = "telefono")
	private String telefono;

	@Column(name = "imagen", length=200000)
	private String imagen;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Reserva> reservas;

	public Usuario() {
	}

	public Usuario(String nombre, String email, String contrasena, int restaurante, String imagen) {
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.restaurante = restaurante;
		this.imagen = imagen;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setRestaurante(Integer restaurante) {
		this.restaurante = restaurante;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(int restaurante) {
		this.restaurante = restaurante;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Reserva> getReservas() {
		List<Reserva> devuelve = new ArrayList<>(this.reservas);
		return devuelve;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", email='" + email + '\'' +
				", contrasena='" + contrasena + '\'' +
				", restaurante=" + restaurante +
				", telefono='" + telefono + '\'' +
				", imagen='" + imagen + '\'' +
				", reservas=" + reservas +
				'}';
	}
}

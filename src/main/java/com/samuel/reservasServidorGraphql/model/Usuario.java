package com.samuel.reservasServidorGraphql.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "restaurante")
    private boolean restaurante;

	@Column(name = "imagen")
	private String imagen;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Reserva> reservas;

	public Usuario() {
	}

	public Usuario(String nombre, String email, String contrasena, boolean restaurante, String imagen) {
		this.nombre = nombre;
		this.email = email;
		this.contrasena = contrasena;
		this.restaurante = restaurante;
		this.imagen = imagen;
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

	public boolean isRestaurante() {
		return restaurante;
	}

	public void setRestaurante(boolean restaurante) {
		this.restaurante = restaurante;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
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
				", imagen='" + imagen + '\'' +
				", reservas=" + reservas +
				'}';
	}
}

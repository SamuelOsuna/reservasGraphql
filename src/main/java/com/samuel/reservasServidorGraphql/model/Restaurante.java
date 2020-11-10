package com.samuel.reservasServidorGraphql.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Restaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "direccion")
    private String direccion;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "imagen")
	private String imagen;

	@OneToMany(mappedBy = "restaurante")
	private List<Mesa> mesas;

	@OneToMany(mappedBy = "restaurante")
	private List<Reserva> reservas;
    
    public Restaurante() { }

	public Restaurante(String nombre, String direccion, String telefono, String imagen) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.imagen = imagen;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restaurante)) return false;
		Restaurante other = (Restaurante) obj;
		return this.nombre.equals(other.nombre);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		return "Restaurante{" + "id=" + id + ", mesas=" + mesas + ", reservas=" + reservas + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", imagen=" + imagen + '}';
	}
}

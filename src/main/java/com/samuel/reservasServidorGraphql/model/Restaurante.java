package com.samuel.reservasServidorGraphql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Restaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "direccion")
    private String direccion;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "imagen")
	private String imagen;

	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Mesa> mesas;

	@OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER)
	private Set<Reserva> reservas;
    
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
    	List devuelve = new ArrayList<>(this.mesas);
		return devuelve;
	}

	public void setMesas(Set<Mesa> mesas) {
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
    	List<Reserva> devuelve = new ArrayList<>(this.reservas);
		return devuelve;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "Restaurante{" + "id=" + id + ", mesas=" + mesas + ", reservas=" + reservas + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", imagen=" + imagen + '}';
	}
}

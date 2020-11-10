package com.samuel.reservasServidorGraphql.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "nickname", nullable = false)
    private String nickname;
    
    @Column(name = "occupation", nullable = false)
    private String occupation;
    
    @Column(name = "birthday")
    private LocalDate dateofbirth;
    
    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false, updatable = false)
    private Restaurante restaurante;

    
    public Usuario() {}
    
    public Usuario(String name, String nickname, String occupation, LocalDate dateofbirth) {
		this.name = name;
		this.nickname = nickname;
		this.occupation = occupation;
		this.dateofbirth = dateofbirth;
	}

    
	@Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Usuario)) return false;
    	Usuario other = (Usuario) obj;
    	return this.name.equals(other.name);
    }
    
    @Override
    public int hashCode() {
    	return name.length();
    }
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public LocalDate getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public void setSeries(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Restaurante getSeries() {
		return restaurante;
	}
    
}

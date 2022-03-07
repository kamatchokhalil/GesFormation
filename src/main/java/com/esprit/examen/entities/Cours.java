package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Cours implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	// @Enumerated(EnumType.STRING)
	private String typeCours;
	private String intitule;
	@ManyToMany(mappedBy="cours")
	private Set<Session> sessions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeCours() {
		return typeCours;
	}
	public void setTypeCours(String typeCours) {
		this.typeCours = typeCours;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	
	public Set<Session> getSessions() {
		return sessions;
	}
	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	@Override
	public String toString() {
		return "Cours [id=" + id + ", description=" + description + ", typeCours=" + typeCours + ", intitule="
				+ intitule + "]";
	}
	public Cours(Long id, String description, String typeCours, String intitule) {
		super();
		this.id = id;
		this.description = description;
		this.typeCours = typeCours;
		this.intitule = intitule;
	}
	
	
	
}

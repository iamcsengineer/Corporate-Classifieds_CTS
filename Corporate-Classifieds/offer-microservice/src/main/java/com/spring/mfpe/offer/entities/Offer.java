package com.spring.mfpe.offer.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Offer")
@Getter
@Setter
@NoArgsConstructor
public class Offer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//name of the offer
	private String name;
	
	//description of the offer
	private String description;
	
	//category of the offer
	private String category;

	// opening/creation date of the offer
	@Column(name="open_date")
	private Date openDate;
	
	// engaged date ( when a buyer shows interest)
	@Column(name="engaged_date")
	private Date engagedDate;
	
	//closing date(when the buyer buys)
	@Column(name="closed_date")
	private Date closedDate;
	
	//employee who created the Offer
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id")
	private Employee emp;
	
	//employee which showed interest in the offer
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="engaged_emp_id")
	private Employee engagedEmp;
	
	//many employees can like many offers
	@JsonIgnore
	@ManyToMany(mappedBy = "likedOffers", fetch = FetchType.EAGER)
	private Set<Employee> likedByEmployees = new HashSet<>();
	
	//no of likes on an offer
	private int likes;
	
	//customized toString method
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", openDate=" + openDate + ", engagedDate=" + engagedDate + ", closedDate=" + closedDate + ", likes="
				+ likes + "]" + likedByEmployees;
	}
	
	
}

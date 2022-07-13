package com.spring.mfpe.offer.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// name of the employee
	private String name;

	// department of the employee
	private String department;

	// gender
	private String gender;

	// age
	private int age;

	// contact info
	@Column(name="contact_number")
	private Long contactNumber;

	// email id
	private String email;

	// points gained by the employee
	@Column(name="points_gained")
	private int pointsGained;

	// one employee can have many offers
	@OneToMany(mappedBy = "emp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Offer> offers;

	// one employee can be engaged in many offers
	@OneToMany(mappedBy = "engagedEmp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Offer> engagedInOffers;

	// many employees can like many offers
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinTable(name="liked_by", 
		joinColumns = {
				@JoinColumn(name="emp_id",referencedColumnName="id")
		},
		inverseJoinColumns = {
				@JoinColumn(name="offer_id",referencedColumnName="id")
		})
	private Set<Offer> likedOffers = new HashSet<>();

	//customized toString
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", gender=" + gender + ", age="
				+ age + ", contactNumber=" + contactNumber + ", email=" + email + ", pointsGained=" + pointsGained+"]";
	}

}

package com.pictureshop.application.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shops")
public class Shop extends AuditModel {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    private int maxPictures;
    
    private int numPictures;

    
	public Shop(@NotNull @Size(max = 100) String name, @NotNull int maxPictures) {
		super();
		this.name = name;
		this.maxPictures = maxPictures;
		this.numPictures = 0;
	}
	
	public Shop() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPictures() {
		return maxPictures;
	}

	public void setMaxPictures(int maxPictures) {
		this.maxPictures = maxPictures;
	}

	public int getNumPictures() {
		return numPictures;
	}

	public void setNumPictures(int numPictures) {
		this.numPictures = numPictures;
	}


    
  
}
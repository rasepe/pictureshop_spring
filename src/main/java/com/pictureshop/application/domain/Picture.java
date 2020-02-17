package com.pictureshop.application.domain;

import com.pictureshop.application.domain.Shop;
import com.pictureshop.application.exception.PictureLimitException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pictures")
public class Picture extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String author;
    
    private String name;
    
    @NotNull
    private double price;
    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shop shop;


	public Picture(String author, @NotNull String name, @NotNull double price, Shop shop) throws PictureLimitException {
		
		super();
		
	//	if (shop.getPictures().size() >= shop.getMaxPictures()) {  
		//	throw new PictureLimitException(PictureLimitException.PICTURE_LIMIT);
	//	} else {
			
			this.author = author;
			this.name = name;
			this.price = price;
			this.shop = shop;
		//	shop.setNumPictures(shop.getNumPictures()+1);
	//	}
	}

	
	Picture() {}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}

    
    
	

    
}
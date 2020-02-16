package com.pictureshop.application.controller;



import com.pictureshop.application.exception.ResourceNotFoundException;
import com.pictureshop.application.domain.Shop;
import com.pictureshop.application.persistence.ShopRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

	
    @GetMapping("/shops")
    public Page<Shop> getAllShops(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }
	
	
	// Single item

	@GetMapping("/shops/{shopId}")
	Shop one(@PathVariable Long shopId) {
		
		return shopRepository.findById(shopId)
			.orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
	}
	
	

    @PostMapping("/shops")
    public Shop createShop(@Valid @RequestBody Shop shop) {
        return shopRepository.save(shop);
    }


    @PutMapping("/shops/{shopId}")
    public Shop updateShop(@PathVariable Long shopId, @Valid @RequestBody Shop shopRequest) {
        return shopRepository.findById(shopId).map(shop -> {
            shop.setName(shopRequest.getName());
            shop.setMaxPictures(shopRequest.getMaxPictures());
            return shopRepository.save(shop);
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }


    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<?> deleteShop(@PathVariable Long shopId) {
        return shopRepository.findById(shopId).map(shop -> {
            shopRepository.delete(shop);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }

}
package com.pictureshop.application.controller;


import com.pictureshop.application.exception.ResourceNotFoundException;
import com.pictureshop.application.domain.Picture;
import com.pictureshop.application.persistence.PictureRepository;
import com.pictureshop.application.persistence.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ShopRepository shopRepository;

	@CrossOrigin(origins = "http://localhost")
    @GetMapping("/shops/{shopId}/pictures")
    public Page<Picture> getAllPicturesByShopId(@PathVariable (value = "shopId") Long shopId,
                                                Pageable pageable) {
        return pictureRepository.findByShopId(shopId, pageable);
    }

	@CrossOrigin(origins = "http://localhost")
    @PostMapping("/shops/{shopId}/pictures")
    public Picture createPicture(@PathVariable (value = "shopId") Long shopId, @RequestBody Picture picture)  {
		
        return shopRepository.findById(shopId).map(shop -> {
            picture.setShop(shop);
            if (picture.getAuthor()=="") {
            picture.setAuthor("ANONYMOUS");
            };
            shop.setNumPictures(shop.getNumPictures()+1);
            return pictureRepository.save(picture);
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
		
		
		}
    

	@CrossOrigin(origins = "http://localhost")
    @PutMapping("/shops/{shopId}/pictures/{pictureId}")
    public Picture updatePicture(@PathVariable (value = "shopId") Long shopId,
                                 @PathVariable (value = "pictureId") Long pictureId,
                                 @Valid @RequestBody Picture pictureRequest) {
        if(!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return pictureRepository.findById(pictureId).map(picture -> {
        	picture.setAuthor(pictureRequest.getAuthor());
            picture.setName(pictureRequest.getName());
            picture.setPrice(pictureRequest.getPrice());
            return pictureRepository.save(picture);
        }).orElseThrow(() -> new ResourceNotFoundException("PictureId " + pictureId + "not found"));
    }

	

	@CrossOrigin(origins = "http://localhost")
    @DeleteMapping("/shops/{shopId}/pictures")
    public void deleteAllPicturesByShopId(@PathVariable (value = "shopId") Long shopId) {
        
	pictureRepository.deleteAll(pictureRepository.findByShopId(shopId, null));
        
    }

		
	
}

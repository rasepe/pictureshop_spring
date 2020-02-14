package com.pictureshop.application.persistence;



import com.pictureshop.application.domain.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Page<Picture> findByShopId(Long shopId, Pageable pageable);
    Optional<Picture> findByIdAndShopId(Long id, Long shopId);
    List<Picture> deleteByShopId(Long shopId);
}
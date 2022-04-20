package ru.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.senchenko.entities.Picture;
import ru.senchenko.entities.Product;

import java.util.List;
import java.util.Optional;

public interface PictureRepo extends JpaRepository<Picture, Integer> {

    @Query("SELECT p.pictureData.data FROM Picture p WHERE p.id = :id")
    byte[] findPictureDataById(@Param("id") Integer id);

    @Query("SELECT p.contentType FROM Picture p WHERE p.pictureData.id = :id")
    Optional<String> findPictureContentTypeByDataId(@Param("id") Integer id);

    @Query("SELECT p FROM Picture p WHERE p.product = :product")
    List<Picture> findAllPicturesFromProduct(@Param("product") Product product);

}

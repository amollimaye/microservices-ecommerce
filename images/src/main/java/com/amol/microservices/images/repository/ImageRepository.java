package com.amol.microservices.images.repository;

import com.amol.microservices.images.entity.Images;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Amol Limaye
 **/
public interface ImageRepository extends CrudRepository<Images,Long>{

    List<Images> findAll();
}

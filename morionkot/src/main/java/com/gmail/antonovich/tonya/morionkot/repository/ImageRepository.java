package com.gmail.antonovich.tonya.morionkot.repository;

import com.gmail.antonovich.tonya.morionkot.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<List<Image>> findByProductId(Long id);
}

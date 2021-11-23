package com.gmail.antonovich.tonya.morionkot.repository;

import com.gmail.antonovich.tonya.morionkot.entity.Characteristic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Long> {

    Optional<List<Characteristic>> findByProductId(Long id);
}
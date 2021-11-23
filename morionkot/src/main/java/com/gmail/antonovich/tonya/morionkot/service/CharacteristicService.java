package com.gmail.antonovich.tonya.morionkot.service;

import com.gmail.antonovich.tonya.morionkot.repository.CharacteristicRepository;
import com.gmail.antonovich.tonya.morionkot.dto.CharacteristicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicService(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public void deleteCharacteristic(CharacteristicDto characteristicDto) {
            characteristicRepository.deleteById(characteristicDto.id);
    }
}

package com.gmail.antonovich.tonya.morionkot.controller;

import com.gmail.antonovich.tonya.morionkot.dto.CharacteristicDto;
import com.gmail.antonovich.tonya.morionkot.service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @Autowired
    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping("delete-characteristic")
    public void deleteCharacteristic(@RequestBody CharacteristicDto characteristicDto) {
        characteristicService.deleteCharacteristic(characteristicDto);
    }
}

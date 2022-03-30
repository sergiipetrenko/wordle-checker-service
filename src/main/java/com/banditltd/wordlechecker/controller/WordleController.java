package com.banditltd.wordlechecker.controller;

import com.banditltd.wordlechecker.dto.WordleDto;
import com.banditltd.wordlechecker.records.Wordle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wordle")
public class WordleController {

    @PostMapping
    public String handleWordle(@RequestBody WordleDto wordleDto) {
        return new Wordle(wordleDto.getHidden()).guess(wordleDto.getGuess());
    }

}

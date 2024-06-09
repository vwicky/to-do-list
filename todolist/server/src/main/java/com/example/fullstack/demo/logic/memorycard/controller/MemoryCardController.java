package com.example.fullstack.demo.logic.memorycard.controller;

import com.example.fullstack.demo.logic.memorycard.MemoryCard;
import com.example.fullstack.demo.logic.memorycard.MemoryCardDto;
import com.example.fullstack.demo.logic.memorycard.repository.MemoryCardRepository;
import com.example.fullstack.demo.logic.urlconfig.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = UrlConfig.prefix + "/card")
public class MemoryCardController {

    @Autowired
    private MemoryCardRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<MemoryCard>> getAll() {
        return new ResponseEntity<> (
                repository.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<MemoryCard> getByParameter(@RequestParam String id) {
        return new ResponseEntity<>(
                repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(" -- Card with such element doesn't exists")),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<MemoryCard> createCard(
            @RequestBody MemoryCardDto memoryCardDto
    ) {
        System.out.println("\n\t-- adding invoked!");
        MemoryCard memoryCard = memoryCardDto.convertToEntity();
        repository.save(memoryCard);
        return new ResponseEntity<>(memoryCard, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCard(
            @PathVariable String id
    ) {
        System.out.println("\n\t-- id: " + id);
        repository.deleteById(id);
        System.out.println("\n\t-- delete invoked");
    }
}

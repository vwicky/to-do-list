package com.example.fullstack.demo.logic.memorycard.repository;

import com.example.fullstack.demo.logic.memorycard.MemoryCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemoryCardRepository extends MongoRepository<MemoryCard, String> {
}

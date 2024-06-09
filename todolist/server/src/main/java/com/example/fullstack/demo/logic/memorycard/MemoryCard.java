package com.example.fullstack.demo.logic.memorycard;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "cards")
@Data
@Builder
public class MemoryCard {
    @Id
    private String id;

    private LocalDateTime creationDate;
    private String content;
    private CardUrgency cardUrgency;
}

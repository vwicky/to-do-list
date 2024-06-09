package com.example.fullstack.demo.logic.memorycard;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document()
public class MemoryCardDto {
    @Id
    private String id;

    private String content;
    private CardUrgency cardUrgency;

    public MemoryCard convertToEntity() {
        return MemoryCard.builder()
                .id(id)
                .content(content)
                .creationDate(LocalDateTime.now())
                .cardUrgency(cardUrgency)
                .build();
    }

}

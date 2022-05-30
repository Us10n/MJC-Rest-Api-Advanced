package com.epam.esm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

/**
 * Data transfer object for Tag
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "tags")
public class TagDto {
    private long tagId;
    private String name;

    /**
     * Instantiates a new Tag dto.
     *
     * @param name the name
     */
    public TagDto(String name) {
        this.name = name;
    }
}

package com.epam.esm.web.controller;

import com.epam.esm.domain.dto.TagDto;
import com.epam.esm.service.service.TagService;
import com.epam.esm.web.hateoas.impl.TagHateoasAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

/**
 * Tag rest controller.
 */
@RestController
@Validated
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;
    private final TagHateoasAdder tagHateoasAdder;

    /**
     * Instantiates a new Tag controller.
     *
     * @param tagService the tag service
     */
    @Autowired
    public TagController(TagService tagService, TagHateoasAdder hateoasAdder) {
        this.tagService = tagService;
        this.tagHateoasAdder = hateoasAdder;
    }

    /**
     * Read all tags.
     *
     * @return the list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public PagedModel<TagDto> readAllTags(@RequestParam(name = "page", defaultValue = "1") @Positive Integer page,
                                          @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {
        PagedModel<TagDto> tagDtos = tagService.readAll(page, limit);
        tagHateoasAdder.addLinksToCollection(tagDtos);

        return tagDtos;
    }

    /**
     * Read tad by id.
     *
     * @param id the id
     * @return the tag dto
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TagDto readTadById(@PathVariable long id) {
        TagDto tagDto = tagService.readById(id);
        tagHateoasAdder.addLinksToEntity(tagDto);

        return tagDto;
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public TagDto readWidelyUsedTag() {
        TagDto tagDto = tagService.findWidelyUsedTagOfUserWithHighestCostOfAllOrders();
        tagHateoasAdder.addLinksToEntity(tagDto);

        return tagDto;
    }

    /**
     * Create tag.
     *
     * @param tagDto the tag dto
     * @return the tag dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@RequestBody TagDto tagDto) {
        TagDto createdTag = tagService.create(tagDto);
        tagHateoasAdder.addLinksToEntity(createdTag);

        return createdTag;
    }

    /**
     * Delete tag.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

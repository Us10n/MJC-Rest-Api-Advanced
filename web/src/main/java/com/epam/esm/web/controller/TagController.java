package com.epam.esm.web.controller;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Tag rest controller.
 */
@RestController
@Validated
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    /**
     * Instantiates a new Tag controller.
     *
     * @param tagService the tag service
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Read all tags.
     *
     * @return the list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<TagDto> readAllTags(@RequestParam(name = "page", defaultValue = "1") @Positive Integer page,
                                    @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {
        return tagService.readAll(page,limit);
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
        return tagService.readById(id);
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public TagDto findWidelyUsedTag() {
        return tagService.findWidelyUsedTagOfUserWithHighestCostOfAllOrders();
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
        return tagService.create(tagDto);
    }

    /**
     * Delete tag.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTag(@PathVariable long id) {
        tagService.delete(id);
    }
}

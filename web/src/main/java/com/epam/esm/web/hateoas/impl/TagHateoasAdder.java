package com.epam.esm.web.hateoas.impl;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.web.controller.TagController;
import com.epam.esm.web.hateoas.HateoasAdder;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagHateoasAdder implements HateoasAdder<TagDto> {
    private static final Class<TagController> TAG_CONTROLLER = TagController.class;

    @Override
    public void addLinksToEntity(TagDto entity) {
        entity.add(linkTo(methodOn(TAG_CONTROLLER).readTadById(entity.getTagId())).withSelfRel());
        entity.add(linkTo(methodOn(TAG_CONTROLLER).deleteTag(entity.getTagId())).withRel("delete"));
        entity.add(linkTo(methodOn(TAG_CONTROLLER).createTag(entity)).withRel("create"));
    }

    @Override
    public void addLinksToCollection(PagedModel<TagDto> model) {
        PagedModel.PageMetadata metadata = model.getMetadata();
        int page = (int) metadata.getNumber();
        int limit = (int) metadata.getSize();
        int totalPages = (int) metadata.getTotalPages();

        model.add(linkTo(methodOn(TAG_CONTROLLER).readAllTags(page, limit)).withSelfRel());
        if (page < totalPages) {
            model.add(linkTo(methodOn(TAG_CONTROLLER).readAllTags(page + 1, limit)).withRel("next"));
        }
        if (page > 1) {
            model.add(linkTo(methodOn(TAG_CONTROLLER).readAllTags(page - 1, limit)).withRel("prev"));
        }
        model.add(linkTo(methodOn(TAG_CONTROLLER).readWidelyUsedTag()).withRel("popular"));
        model.getContent().forEach(this::addLinksToEntity);

    }
}

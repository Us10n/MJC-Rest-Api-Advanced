package com.epam.esm.web.controller;

import com.epam.esm.repository.entity.criteria.GiftCertificateCriteria;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

/**
 * Gift certificate rest controller.
 */
@RestController
@Validated
@RequestMapping("/giftCertificates")
public class GiftCertificateController {

    private GiftCertificateService giftCertificateService;

    /**
     * Instantiates a new Gift certificate controller.
     *
     * @param giftCertificateService the gift certificate service
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Read by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate dto
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public GiftCertificateDto readById(@PathVariable long id) {
        return giftCertificateService.readById(id);
    }

    /**
     * Create gift certificate.
     *
     * @param giftCertificateDto the gift certificate dto
     * @return the gift certificate dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDto create(@RequestBody GiftCertificateDto giftCertificateDto) {
        return giftCertificateService.create(giftCertificateDto);
    }

    /**
     * Update gift certificate.
     *
     * @param id                 the id
     * @param giftCertificateDto the gift certificate dto
     * @return the gift certificate dto
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDto update(@PathVariable long id,
                                     @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setGiftCertificateId(id);
        return giftCertificateService.update(giftCertificateDto);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id) {
        giftCertificateService.delete(id);
    }

    /**
     * Gets all gift certificates.
     *
     * @param tagNames   the tag names
     * @param partName  the part name
     * @param partDesc  the part desc
     * @param sortBy    the sort by
     * @param sortOrder the sort order
     * @return the all gift certificates
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<GiftCertificateDto> getAllGiftCertificates(
            @RequestParam(name = "tag", required = false) Set<String> tagNames,
            @RequestParam(name = "name", required = false) String partName,
            @RequestParam(name = "description", required = false) String partDesc,
            @RequestParam(name = "sort", required = false) String sortBy,
            @RequestParam(name = "order", defaultValue = "ASC") String sortOrder,
            @RequestParam(name = "page", defaultValue = "1") @Positive Integer page,
            @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {

        GiftCertificateCriteria criteria = new GiftCertificateCriteria();
        criteria.setTagNames(tagNames);
        criteria.setPartName(partName);
        criteria.setPartDesc(partDesc);
        criteria.setSortBy(sortBy);
        criteria.setSortOrder(sortOrder);

        return giftCertificateService.readByCriteria(criteria, page, limit);
    }
}

package com.epam.esm.web.controller;

import com.epam.esm.service.criteria.GiftCertificateCriteria;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Gift certificate rest controller.
 */
@RestController
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
    public GiftCertificateDto update(@PathVariable long id, @RequestBody GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setGiftCertificateId(id);
        return giftCertificateService.update(giftCertificateDto);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        giftCertificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Gets all gift certificates.
     *
     * @param tagName   the tag name
     * @param partName  the part name
     * @param partDesc  the part desc
     * @param sortBy    the sort by
     * @param sortOrder the sort order
     * @return the all gift certificates
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDto> getAllGiftCertificates(
            @RequestParam(name = "tag", required = false) Set<String> tagNames,
            @RequestParam(name = "name", required = false) String partName,
            @RequestParam(name = "description", required = false) String partDesc,
            @RequestParam(name = "sort", required = false) String sortBy,
            @RequestParam(name = "order", required = false) String sortOrder) {

        GiftCertificateCriteria criteria = new GiftCertificateCriteria();
        criteria.setTagNames(tagNames);
        criteria.setPartName(partName);
        criteria.setPartDesc(partDesc);
        criteria.setSortBy(sortBy);
        criteria.setSortOrder(sortOrder);

        return giftCertificateService.readByCriteria(criteria);
    }
}

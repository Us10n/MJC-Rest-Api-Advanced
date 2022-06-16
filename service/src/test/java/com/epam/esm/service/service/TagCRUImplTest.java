//package com.epam.esm.service.crud;
//
//import com.epam.esm.repository.dao.GiftCertificateDao;
//import com.epam.esm.repository.dao.TagDao;
//import com.epam.esm.domain.entity.Tag;
//import com.epam.esm.service.config.ServiceConfigTest;
//import com.epam.esm.service.crud.impl.TagCRUImpl;
//import com.epam.esm.service.dto.TagDto;
//import com.epam.esm.service.exception.ResponseException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ServiceConfigTest.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class TagCRUImplTest {
//
//    @Autowired
//    private GiftCertificateDao giftCertificateDao;
//
//    @Autowired
//    private TagDao tagDao;
//
//    private TagCRU tagCRU;
//    List<TagDto> tagDtos = new ArrayList<>();
//
//    @BeforeAll
//    public void setup() {
//        List<Tag> tags = new ArrayList<>();
//        tags.add(new Tag(1, "funny"));
//        tags.add(new Tag(2, "useful"));
//        tags.add(new Tag(3, "great"));
//        tagDtos = tags.stream().map(
//                tag -> new TagDto(tag.getId(), tag.getName())
//        ).collect(Collectors.toList());
//
//        Mockito.when(tagDao.findByName("funny")).thenReturn(Optional.empty());
//        Mockito.when(tagDao.findByName("useful")).thenReturn(Optional.of(tags.get(1)));
//        Mockito.when(tagDao.create(tags.get(0))).thenReturn(Optional.of(tags.get(0)));
//        Mockito.when(tagDao.findAll()).thenReturn(tags);
//        Mockito.when(tagDao.findById(3)).thenReturn(Optional.of(tags.get(2)));
//        Mockito.when(tagDao.findById(1)).thenReturn(Optional.of(tags.get(1)));
//
//        tagCRU = new TagCRUImpl(tagDao);
//    }
//
//
//    @Test
//    void createPositive() {
//        TagDto expected = new TagDto(1, "funny");
//        TagDto actual = tagCRU.create(expected);
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void createNegative() {
//        TagDto expected = new TagDto(2, "useful");
//        Assertions.assertThrows(ResponseException.class, () -> {
//            tagCRU.create(expected);
//        });
//    }
//
//    @Test
//    void readAll() {
//        List<TagDto> actualTags = tagCRU.readAll();
//        Assertions.assertEquals(actualTags, tagDtos);
//    }
//
//    @Test
//    void readById() {
//        TagDto actual = tagCRU.readById(3);
//        TagDto expected = tagDtos.get(2);
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void deletePositive() {
//        Assertions.assertDoesNotThrow(() -> {
//            tagCRU.delete(1);
//        });
//    }
//
//    @Test
//    void deleteNegative() {
//        Assertions.assertThrows(ResponseException.class, () -> {
//            tagCRU.delete(2);
//        });
//    }
//
//    @Test
//    void convertToDto() {
//        Tag sample = new Tag(1, "name");
//        TagDto actual = tagCRU.convertToDto(sample);
//        TagDto expected = new TagDto(1, "name");
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void convertToModel() {
//        TagDto sample = new TagDto(1, "name");
//        Tag actual = tagCRU.convertToModel(sample);
//        Tag expected = new Tag(1, "name");
//        Assertions.assertEquals(expected, actual);
//    }
//}
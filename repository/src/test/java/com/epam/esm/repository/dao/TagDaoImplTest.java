//package com.epam.esm.repository.dao;
//
//import com.epam.esm.repository.config.TestConfig;
//import com.epam.esm.repository.entity.Tag;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfig.class)
//@ActiveProfiles("test")
//@Sql(value = "/createDb.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/dropDb.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//class TagDaoImplTest {
//
//    @Autowired
//    private TagDao tagDao;
//
//    @Test
//    void findById() {
//        Tag actual = tagDao.findById(1).get();
//        Tag expected = new Tag(1, "cinema");
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void findAll() {
//        int actual = tagDao.findAll().size();
//        int expected = 20;
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void create() {
//        Tag sample = new Tag(24, "33test");
//        Tag actual = tagDao.create(sample).get();
//        Assertions.assertEquals(sample, actual);
//    }
//
//    @Test
//    void findTagsByGiftCertificateId() {
//        int actual = tagDao.findTagsByGiftCertificateId(2).size();
//        int expected = 5;
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void findByName() {
//        Tag actual = tagDao.findByName("cash").get();
//        Tag expected = new Tag(3, "cash");
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void delete() {
//        int sizeBefore = tagDao.findAll().size();
//        tagDao.deleteById(23);
//        int sizeAfter = tagDao.findAll().size();
//        Assertions.assertEquals(sizeBefore - 1, sizeAfter);
//    }
//
//    @Test
//    void attachTag1() {
//        int before = tagDao.findTagsByGiftCertificateId(1).size();
//        tagDao.attachTagToCertificate(1, 10);
//        int after = tagDao.findTagsByGiftCertificateId(1).size();
//        Assertions.assertEquals(before + 1, after);
//    }
//
//    @Test
//    void detachTag1() {
//        int before = tagDao.findTagsByGiftCertificateId(1).size();
//        tagDao.detachTagFromCertificate(1, 23);
//        int after = tagDao.findTagsByGiftCertificateId(1).size();
//        Assertions.assertEquals(before - 1, after);
//    }
//
//
//}
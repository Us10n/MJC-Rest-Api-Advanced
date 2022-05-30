//package com.epam.esm.repository.dao;
//
//import com.epam.esm.repository.config.TestConfig;
//import com.epam.esm.repository.entity.GiftCertificate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfig.class)
//@ActiveProfiles("test")
//@Sql(value = "/createDb.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/dropDb.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//class GiftCertificateDaoImplTest {
//
//    @Autowired
//    GiftCertificateDao giftCertificateDao;
//
//    @Test
//    void findAll() {
//        int actual = giftCertificateDao.findAll().size();
//        int expected = 5;
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void create() {
//        GiftCertificate sample = new GiftCertificate(
//                6, "test", "tesd", 1.1, 2,
//                LocalDateTime.parse("2022-04-11T10:00:11.156"), LocalDateTime.parse("2022-04-11T10:00:11.156")
//        );
//        GiftCertificate actual = giftCertificateDao.create(sample).get();
//        Assertions.assertEquals(sample, actual);
//    }
//
//    @Test
//    void findById() {
//        GiftCertificate actual = giftCertificateDao.findById(1).get();
//        GiftCertificate expected = new GiftCertificate(
//                1, "Movie Enjoyer", "Watch movies whole day for free", 20.00, 1,
//                LocalDateTime.parse("2022-04-11T10:00:11"), LocalDateTime.parse("2022-04-11T18:37:17")
//        );
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void findByName() {
//        GiftCertificate actual = giftCertificateDao.findByName("Movie Enjoyer").get();
//        GiftCertificate expected = new GiftCertificate(
//                1, "Movie Enjoyer", "Watch movies whole day for free", 20.00, 1,
//                LocalDateTime.parse("2022-04-11T10:00:11"), LocalDateTime.parse("2022-04-11T18:37:17")
//        );
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void findByCriteria() {
//        String query = "SELECT DISTINCT gift_certificates.id, gift_certificates.name, gift_certificates.description, gift_certificates.price, " +
//                "gift_certificates.duration, gift_certificates.create_date, gift_certificates.last_update_date " +
//                "FROM module.gift_certificates " +
//                "JOIN module.gift_certificate_tags ON gift_certificates.id=gift_certificate_tags.gift_certificate_id " +
//                "JOIN module.tags ON gift_certificate_tags.tag_id=tags.id " +
//                "WHERE gift_certificates.name like concat('Movie', '%%')";
//        GiftCertificate expected = new GiftCertificate(
//                1, "Movie Enjoyer", "Watch movies whole day for free", 20.00, 1,
//                LocalDateTime.parse("2022-04-11T10:00:11"), LocalDateTime.parse("2022-04-11T18:37:17")
//        );
//        GiftCertificate actual=giftCertificateDao.findByCriteria(query).get(0);
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    void delete() {
//        int before = giftCertificateDao.findAll().size();
//        giftCertificateDao.deleteById(2);
//        int after = giftCertificateDao.findAll().size();
//        Assertions.assertEquals(before - 1, after);
//    }
//
//    @Test
//    void update() {
//        GiftCertificate expected = new GiftCertificate(
//                1, "test", "Watch movies whole day for free", 20.00, 1,
//                LocalDateTime.parse("2022-04-11T10:00:11"), LocalDateTime.parse("2022-04-11T18:37:17")
//        );
//        GiftCertificate actual = giftCertificateDao.update(expected).get();
//        Assertions.assertEquals(expected, actual);
//    }
//}
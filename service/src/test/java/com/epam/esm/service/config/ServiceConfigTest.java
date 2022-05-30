package com.epam.esm.service.config;

import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.repository.dao.impl.TagDaoImpl;
import com.epam.esm.service.handler.DateHandler;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ServiceConfigTest {

    @Bean
    @Primary
    public GiftCertificateDao giftCertificateDao() {
        return Mockito.mock(GiftCertificateDaoImpl.class);
    }

    @Bean
    @Primary
    public TagDao tagDao() {
        return Mockito.mock(TagDaoImpl.class);
    }

    @Bean
    @Primary
    public DateHandler dateHandler() {
        return Mockito.mock(DateHandler.class);
    }
}
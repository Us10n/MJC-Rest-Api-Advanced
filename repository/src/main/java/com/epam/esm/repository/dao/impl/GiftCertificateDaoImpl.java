package com.epam.esm.repository.dao.impl;

import com.epam.esm.repository.creator.GiftCertificateQueryCreator;
import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.entity.GiftCertificate;
import com.epam.esm.repository.entity.criteria.GiftCertificateCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private static final String FIND_ALL_QUERY = "SELECT gift_certificates.id, gift_certificates.name, gift_certificates.description, gift_certificates.price, " +
            "gift_certificates.duration, gift_certificates.create_date, gift_certificates.last_update_date " +
            "FROM module.gift_certificates";
    private static final String FIND_BY_NAME_QUERY = FIND_ALL_QUERY + " WHERE name=:certName";
    private static final String DETACH_ALL_TAGS_BY_ID_QUERY = "DELETE FROM module.gift_certificate_tags WHERE gift_certificate_id=:certificateId";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public GiftCertificate create(GiftCertificate object) {
        entityManager.persist(object);

        return object;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return Optional.ofNullable(entityManager.find(GiftCertificate.class, id));
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        return entityManager.createNativeQuery(FIND_BY_NAME_QUERY, GiftCertificate.class)
                .setParameter("certName", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<GiftCertificate> findAll(int page, int limit) {
        int offset = (page - 1) * limit;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> query = criteriaBuilder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> from = query.from(GiftCertificate.class);
        CriteriaQuery<GiftCertificate> criteriaQuery = query.select(from);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public long countAll() {
        return entityManager.createQuery("SELECT count(crt) FROM GiftCertificate crt", Long.class).getSingleResult();
    }

    @Override
    public List<GiftCertificate> findByCriteria(GiftCertificateCriteria criteria, int page, int limit) {
        int offset = (page - 1) * limit;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteriaQuery = GiftCertificateQueryCreator.buildGetQueryByCriteria(criteria, criteriaBuilder);
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        GiftCertificate object = entityManager.find(GiftCertificate.class, id);
        entityManager.createNativeQuery(DETACH_ALL_TAGS_BY_ID_QUERY)
                .setParameter("certificateId", id);

        entityManager.remove(object);
    }

    @Override
    @Transactional
    public GiftCertificate update(GiftCertificate object) {
        return entityManager.merge(object);
    }
}

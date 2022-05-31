package com.epam.esm.repository.dao.impl;

import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagDaoImpl implements TagDao {

    private static final String FIND_ALL_QUERY = "SELECT tags.id, tags.name FROM module.tags";
    private static final String FIND_BY_ID_QUERY = FIND_ALL_QUERY + " WHERE id=?";
    private static final String FIND_BY_GIFT_CERTIFICATE_ID_QUERY = "SELECT tags.id, tags.name FROM module.tags " +
            "JOIN module.gift_certificate_tags ON gift_certificate_tags.tag_id=tags.id " +
            "WHERE gift_certificate_id=:cerfId";
    private static final String FIND_BY_NAME_QUERY = FIND_ALL_QUERY + " WHERE name=:tagName";
    private static final String DELETE_TAG_HOLDER_BY_ID_QUERY = "DELETE FROM module.gift_certificate_tags WHERE tag_id=?";
    private static final String DELETE_TAG_BY_ID_QUERY = "DELETE FROM module.tags WHERE id=?";
    private static final String ATTACH_TAG_BY_ID_QUERY = "INSERT INTO module.gift_certificate_tags (gift_certificate_id, tag_id) VALUES (?, ?)";
    private static final String DETACH_TAG_BY_ID_QUERY = "DELETE FROM module.gift_certificate_tags WHERE tag_id=:tagId";
    private static final String FIND_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS="SELECT tags.id, tags.name, gfs.name FROM module.tags " +
            "JOIN module.gift_certificate_tags gfts on gfts.tag_id= tags.id " +
            "JOIN module.gift_certificates gfs on gfs.id=gfts.gift_certificate_id " +
            "JOIN module.orders ords on ords.gift_certificate_id=gfs.id " +
            "JOIN module.users usrs on ords.user_id=usrs.id " +
            "WHERE usrs.id=( " +
            "    SELECT users.id FROM module.users JOIN module.orders ON orders.user_id=users.id GROUP BY users.id ORDER BY sum(orders.price) DESC LIMIT 1 " +
            ") " +
            "GROUP BY tags.name " +
            "ORDER BY count(tags.name) DESC LIMIT 1";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Tag create(Tag object) {
        entityManager.persist(object);

        return object;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return entityManager.createNativeQuery(FIND_BY_NAME_QUERY, Tag.class)
                .setParameter("tagName", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Tag> findAll(int page, int limit) {
        int offset = (page - 1) * limit;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> from = query.from(Tag.class);
        CriteriaQuery<Tag> criteriaQuery = query.select(from);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Optional<Tag> findWidelyUsedTagOfUserWithHighestCostOfAllOrders() {
        return entityManager.createNativeQuery(FIND_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS, Tag.class)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Tag object = entityManager.find(Tag.class, id);
        entityManager.createNativeQuery(DETACH_TAG_BY_ID_QUERY)
                .setParameter("tagId", id)
                .executeUpdate();

        entityManager.remove(object);
    }
}

package com.epam.esm.repository.dao.impl;

import com.epam.esm.repository.dao.OrderDao;
import com.epam.esm.repository.entity.OrderDetail;
import com.epam.esm.repository.entity.constants.ColumnNames;
import com.epam.esm.repository.entity.constants.TableNames;
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
public class OrderDaoImpl implements OrderDao {

    private static final String FIND_ORDERS_BY_USER_ID="SELECT o FROM OrderDetail o WHERE o.user.id = :userId";

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public OrderDetail create(OrderDetail object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public Optional<OrderDetail> findById(long id) {
        return Optional.ofNullable(entityManager.find(OrderDetail.class, id));
    }

    @Override
    public List<OrderDetail> findAll(int page, int limit) {
        int offset = (page - 1) * limit;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> query = criteriaBuilder.createQuery(OrderDetail.class).where();
        Root<OrderDetail> from = query.from(OrderDetail.class);
        CriteriaQuery<OrderDetail> criteriaQuery = query.select(from);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<OrderDetail> findOrdersByUserId(long id, Integer page, Integer limit) {
        int offset = (page - 1) * limit;

        return entityManager.createQuery(FIND_ORDERS_BY_USER_ID,OrderDetail.class)
                .setParameter("userId",id)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}

package com.elearning.dao;

import com.elearning.entities.Batch;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class BatchDaoImpl extends BaseDaoImpl<Batch, Long> implements BatchDao{
    @Inject
    public BatchDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public Optional<Batch> findByExternalId(String externalId) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Batch> criteriaQuery = criteriaQuery();
        Root<Batch> root = criteriaQuery.from(Batch.class);
        criteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("externalId"), externalId)
                );

        List<Batch> batches = list(criteriaQuery);
        if(batches == null || batches.size() == 0) {
            return Optional.empty();
        }
        else if(batches.size() > 1) {
            throw new ElearningException("More than one step present for external id :: " + externalId);
        }
        return Optional.ofNullable(batches.get(0));
    }
}

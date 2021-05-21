package com.elearning.dao;

import com.elearning.entities.Instructor;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class InstructorDaoImpl extends BaseDaoImpl<Instructor, Long> implements InstructorDao{
    @Inject
    public InstructorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public Optional<Instructor> findByExternalId(String externalId) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Instructor> criteriaQuery = criteriaQuery();
        Root<Instructor> root = criteriaQuery.from(Instructor.class);
        criteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("externalId"), externalId)
                );

        List<Instructor> instructors = list(criteriaQuery);
        if(instructors == null || instructors.size() == 0) {
            return Optional.empty();
        }
        else if(instructors.size() > 1) {
            throw new ElearningException("More than one step present for external id :: " + externalId);
        }
        return Optional.ofNullable(instructors.get(0));
    }
}

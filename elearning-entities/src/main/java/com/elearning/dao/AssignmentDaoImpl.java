package com.elearning.dao;

import com.elearning.entities.Assignment;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class AssignmentDaoImpl extends BaseDaoImpl<Assignment, Long> implements AssignmentDao{
    @Inject
    public AssignmentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Assignment> getAssignment(String assignmentId) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Assignment> criteriaQuery = criteriaQuery();
        Root<Assignment> root = criteriaQuery.from(Assignment.class);
        criteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("externalId"), assignmentId)
                );

        List<Assignment> assignments = list(criteriaQuery);
        if(assignments == null || assignments.size() == 0) {
            return Optional.empty();
        }
        else if(assignments.size() > 1) {
            throw new ElearningException("More than one assignment present for external id :: " + assignmentId);
        }
        return Optional.ofNullable(assignments.get(0));
    }
}

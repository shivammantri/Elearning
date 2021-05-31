package com.elearning.dao;

import com.elearning.entities.Student;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl extends BaseDaoImpl<Student, Long> implements StudentDao{
    @Inject
    public StudentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public Optional<Student> findByExternalId(String externalId) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaQuery();
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("externalId"), externalId)
                );

        List<Student> students = list(criteriaQuery);
        if(students == null || students.size() == 0) {
            return Optional.empty();
        }
        else if(students.size() > 1) {
            throw new ElearningException("More than one student present for external id :: " + externalId);
        }
        return Optional.ofNullable(students.get(0));
    }
}

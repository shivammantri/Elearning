package com.elearning.dao;

import com.elearning.entities.Student;

import java.util.Optional;

public interface StudentDao extends BaseDao<Student, Long> {
    public Optional<Student> findByExternalId(String externalId);
}

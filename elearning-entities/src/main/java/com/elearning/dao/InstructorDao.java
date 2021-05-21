package com.elearning.dao;

import com.elearning.entities.Instructor;

import java.util.Optional;

public interface InstructorDao extends BaseDao<Instructor, Long> {
    public Optional<Instructor> findByExternalId(String externalId);
}

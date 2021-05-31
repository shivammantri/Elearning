package com.elearning.dao;

import com.elearning.entities.Assignment;

import java.util.Optional;

public interface AssignmentDao extends BaseDao<Assignment, Long> {
    public Optional<Assignment> getAssignment(String assignmentId);
}

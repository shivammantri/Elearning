package com.elearning.platform.services;

import com.elearning.dao.StudentDao;
import com.elearning.entities.Student;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


import javax.ws.rs.core.Response;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Inject
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public Student getStudent(String externalId) {
        Optional<Student> student = studentDao.findByExternalId(externalId);
        if(!student.isPresent()) {
            throw new ElearningException("Unable to find subscriber with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return student.get();
    }
}

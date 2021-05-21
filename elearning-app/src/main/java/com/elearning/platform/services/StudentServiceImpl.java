package com.elearning.platform.services;

import com.elearning.core.mappers.StudentMapper;
import com.elearning.dao.StudentDao;
import com.elearning.entities.Student;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.responses.StudentResponse;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


import javax.ws.rs.core.Response;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final StudentMapper studentMapper;

    @Inject
    public StudentServiceImpl(StudentDao studentDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public StudentResponse getStudent(String externalId) {
        Optional<Student> student = studentDao.findByExternalId(externalId);
        if(!student.isPresent()) {
            throw new ElearningException("Unable to find student with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return studentMapper.mapEntityToResponse(student.get());
    }
}

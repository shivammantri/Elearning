package com.elearning.platform.services;

import com.elearning.core.mappers.StudentMapper;
import com.elearning.dao.BatchDao;
import com.elearning.dao.StudentDao;
import com.elearning.entities.Batch;
import com.elearning.entities.Student;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.requests.StudentRequest;
import com.elearning.model.responses.BatchResponse;
import com.elearning.model.responses.StudentResponse;
import com.elearning.utility.IdGenerator;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


import javax.ws.rs.core.Response;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final StudentMapper studentMapper;
    private final BatchService batchService;
    private final BatchDao batchDao;

    @Inject
    public StudentServiceImpl(StudentDao studentDao, StudentMapper studentMapper, BatchService batchService, BatchDao batchDao) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
        this.batchService = batchService;
        this.batchDao = batchDao;
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

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        studentMapper.mapRequestToEntity(student, studentRequest);
        student.setExternalId(IdGenerator.generateStudentId());
        studentDao.create(student);
        return studentMapper.mapEntityToResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse updateStudentDetails(StudentRequest studentRequest) {
        Optional<Student> student = studentDao.findByExternalId(studentRequest.getExternalId());
        if(!student.isPresent()) {
            throw new ElearningException("Unable to find student with id :: " + studentRequest.getExternalId(), Response.Status.NOT_FOUND);
        }
        studentMapper.mapRequestToEntity(student.get(), studentRequest);
        studentDao.update(student.get());
        return studentMapper.mapEntityToResponse(student.get());
    }

    @Override
    @Transactional
    public BatchResponse enrollToBatch(String batchId, String studentId) {
        Optional<Student> student = studentDao.findByExternalId(studentId);
        if(!student.isPresent()) {
            throw new ElearningException("Unable to find student with id :: " + studentId, Response.Status.NOT_FOUND);
        }
        return batchService.addStudentToBatch(batchId, student.get());
    }

    @Override
    @Transactional
    public StudentResponse unenrollFromBatch(String batchId, String studentId) {
        Optional<Student> student = studentDao.findByExternalId(studentId);
        if(!student.isPresent()) {
            throw new ElearningException("Unable to find student with id :: " + studentId, Response.Status.NOT_FOUND);
        }
        Optional<Batch> enrolledBatch = student.get().getEnrolledBatches().stream()
                .filter(batch -> {
                    return batch.getExternalId().equalsIgnoreCase(batchId);
                })
                .findFirst();
        if(enrolledBatch.isPresent()) {
            student.get().getEnrolledBatches().remove(enrolledBatch.get());
            enrolledBatch.get().getEnrolledStudents().remove(student.get());
        }
        studentDao.update(student.get());
        batchDao.update(enrolledBatch.get());
        return studentMapper.mapEntityToResponse(student.get());
    }
}

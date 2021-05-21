package com.elearning.platform.services;

import com.elearning.dao.BatchDao;
import com.elearning.entities.Batch;
import com.elearning.model.exceptions.ElearningException;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class BatchServiceImpl implements BatchService {
    private final BatchDao batchDao;

    @Inject
    public BatchServiceImpl(BatchDao batchDao) {
        this.batchDao = batchDao;
    }

    @Override
    @Transactional
    public Batch getBatch(String externalId) {
        Optional<Batch> batch = batchDao.findByExternalId(externalId);
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find step with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return batch.get();
    }
}

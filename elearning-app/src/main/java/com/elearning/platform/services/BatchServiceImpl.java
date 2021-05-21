package com.elearning.platform.services;

import com.elearning.core.mappers.BatchMapper;
import com.elearning.dao.BatchDao;
import com.elearning.entities.Batch;
import com.elearning.model.exceptions.ElearningException;
import com.elearning.model.responses.BatchResponse;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class BatchServiceImpl implements BatchService {
    private final BatchDao batchDao;
    private final BatchMapper batchMapper;

    @Inject
    public BatchServiceImpl(BatchDao batchDao, BatchMapper batchMapper) {
        this.batchDao = batchDao;
        this.batchMapper = batchMapper;
    }

    @Override
    @Transactional
    public BatchResponse getBatch(String externalId) {
        Optional<Batch> batch = batchDao.findByExternalId(externalId);
        if(!batch.isPresent()) {
            throw new ElearningException("Unable to find batch with id :: " + externalId, Response.Status.NOT_FOUND);
        }
        return batchMapper.mapEntityToResponse(batch.get());
    }
}

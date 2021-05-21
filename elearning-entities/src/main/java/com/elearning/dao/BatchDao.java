package com.elearning.dao;

import com.elearning.entities.Batch;

import java.util.Optional;

public interface BatchDao extends BaseDao<Batch, Long> {
    public Optional<Batch> findByExternalId(String externalId);
}

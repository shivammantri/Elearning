package com.elearning.platform.bootstrap;

import com.codahale.metrics.MetricRegistry;
import com.elearning.module.EntityModule;
import com.elearning.platform.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

import com.google.inject.Singleton;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

public class ElearningModule extends AbstractModule{
    private final ObjectMapper objectMapper;
    private final MetricRegistry metricRegistry;
    private final HibernateBundle<ElearningConfiguration> hibernateBundle;
    public ElearningModule(HibernateBundle<ElearningConfiguration> hibernateBundle, ObjectMapper objectMapper, MetricRegistry metricRegistry) {
        this.objectMapper=objectMapper;
        this.hibernateBundle=hibernateBundle;
        this.metricRegistry = metricRegistry;
    }

    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(objectMapper);
        bind(SessionFactory.class).toInstance(hibernateBundle.getSessionFactory());
        bind(MetricRegistry.class).toInstance(metricRegistry);
        bind(BatchService.class).to(BatchServiceImpl.class).in(Singleton.class);
        bind(InstructorService.class).to(InstructorServiceImpl.class).in(Singleton.class);
        bind(StudentService.class).to(StudentServiceImpl.class).in(Singleton.class);
        bind(AssignmentService.class).to(AssignmentServiceImpl.class).in(Singleton.class);
        install(new EntityModule());
    }
}

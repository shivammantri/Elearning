package com.elearning.platform.bootstrap;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

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
    }
}

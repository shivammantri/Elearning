package com.elearning.platform.bootstrap;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.elearning.platform.filter.RequestFilter;
import com.elearning.platform.filter.ResponseFilter;
import com.elearning.platform.resource.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.TimeZone;

@Slf4j
public class ElearningApplication extends Application<ElearningConfiguration>{
    private MetricRegistry metricRegistry;
    private ObjectMapper objectMapper;
    private HibernateBundle<ElearningConfiguration> hibernateBundle;
    public static void main(String[] args) throws Exception {
        new ElearningApplication().run(args);
    }

    @Override
    public void run(ElearningConfiguration configuration, Environment environment)  {
        final JmxReporter reporter = JmxReporter.forRegistry(metricRegistry).build();
        reporter.start();
        Injector injector = Guice.createInjector(new ElearningModule(hibernateBundle,objectMapper,metricRegistry));
        environment.jersey().register(injector.getInstance(SampleResource.class));
        environment.jersey().register(injector.getInstance(RequestFilter.class));
        environment.jersey().register(injector.getInstance(ResponseFilter.class));
        environment.jersey().register(injector.getInstance(AssignmentResource.class));
        environment.jersey().register(injector.getInstance(BatchResource.class));
        environment.jersey().register(injector.getInstance(InstructorResource.class));
        environment.jersey().register(injector.getInstance(StudentResource.class));
        environment.jersey().register(injector.getInstance(MultiPartFeature.class));
        log.info("Elearning Application is up!!");
    }

    @Override
    public void initialize(Bootstrap<ElearningConfiguration> bootstrap) {
        metricRegistry = bootstrap.getMetricRegistry();
        objectMapper = bootstrap.getObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleModule simpleModule = new SimpleModule();
        objectMapper.registerModule(simpleModule);
        objectMapper.setTimeZone(TimeZone.getDefault());
        ImmutableList<Class<?>> entityClasses = ScanningHibernateBundle.findEntityClassesFromDirectory(new String[]{"com.elearning.entities"});
        hibernateBundle = new HibernateBundle<ElearningConfiguration>(entityClasses.get(0),
                entityClasses.subList(1, entityClasses.size()).toArray(new Class<?>[]{})) {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(ElearningConfiguration elearningConfiguration) {
                return elearningConfiguration.getElearningMasterDataSource();
            }

            @Override
            protected String name() {
                return "hibernate.elearningMasterDB";
            }
        };
        bootstrap.addBundle(hibernateBundle) ;
    }


}

package com.elearning.module;

import com.elearning.dao.*;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class EntityModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BatchDao.class).to(BatchDaoImpl.class).in(Singleton.class);
        bind(InstructorDao.class).to(InstructorDaoImpl.class).in(Singleton.class);
        bind(StudentDao.class).to(StudentDaoImpl.class).in(Singleton.class);
    }
}

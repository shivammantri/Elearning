package com.elearning.platform.bootstrap;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;

@Data
public class ElearningConfiguration extends Configuration {
    private DataSourceFactory elearningMasterDataSource=new DataSourceFactory();

}

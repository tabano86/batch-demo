package com.example.demo.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

    /**
     * override to do not set datasource even if a datasource exist. Initialize will use a Map based JobRepository (instead of database).
     *
     * @param dataSource
     */
    @Override
    public void setDataSource(DataSource dataSource) {
    }

}
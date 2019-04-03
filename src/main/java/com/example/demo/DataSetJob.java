package com.example.demo;

import com.example.demo.model.DataSet;
import com.example.demo.tasklets.DataSetItemProcessor;
import com.example.demo.tasklets.DataSetItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.stream.Stream;

@Configuration
public class DataSetJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("dataset.csv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob() throws Exception {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() throws Exception {
        return stepBuilderFactory
                .get("step")
                .<DataSet, DataSet>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<DataSet> reader() {
        return new FlatFileItemReaderBuilder<DataSet>()
                .name("dataSetItemReader")
                .resource(inputResource)
                .delimited()
                .names(Stream.of(DataSet.class.getDeclaredFields()).map(Field::getName).filter(f -> !f.equalsIgnoreCase("randomNo")).toArray(String[]::new))
                .linesToSkip(1)
                .targetType(DataSet.class)
                .recordSeparatorPolicy(new DefaultRecordSeparatorPolicy())
                .build();
    }

    @Bean
    public DataSetItemProcessor processor() {
        return new DataSetItemProcessor();
    }

    @Bean
    public DataSetItemWriter writer() {
        return new DataSetItemWriter();
    }
}
package com.example.demo.tasklets;

import com.example.demo.model.DataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DataSetItemWriter implements ItemWriter<DataSet> {

    @BeforeProcess
    public void before() {
        log.info("Before DataSetItemWriter");
    }

    @AfterProcess
    public void after() {
        log.info("Before DataSetItemWriter");
    }

    @Override
    public void write(List<? extends DataSet> list) throws Exception {
        log.info("Item Size:{}, names:{}", list.size(), list.stream().map(DataSet::getName).toArray(String[]::new));
        log.info("Average score: {}", list.stream().map(DataSet::getGrade).collect(Collectors.averagingDouble(Double::doubleValue)));
    }
}

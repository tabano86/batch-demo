package com.example.demo.tasklets;

import com.example.demo.model.DataSet;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.item.ItemProcessor;

import java.util.Random;

@Slf4j
@NoArgsConstructor
public class DataSetItemProcessor implements ItemProcessor<DataSet, DataSet> {

    @BeforeProcess
    public void before() {
        log.info("Before DataSetItemProcessor");
    }

    @AfterProcess
    public void after() {
        log.info("Before DataSetItemProcessor");
    }

    @Override
    public DataSet process(DataSet data) {
        data.setRandomNo(new Random().nextDouble());
        log.info("Processing data - i.e. adding a field and/or mapping");
        return data;
    }
}

package com.example.servicereaderjob.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ServiceReaderJobConfig {

  private final JobBuilderFactory jobBuilderFactory;

  @Bean
  public Job job(Step serviceReaderStep) {
    return jobBuilderFactory.get("serviceReaderJob")
            .start(serviceReaderStep)
            .incrementer(new RunIdIncrementer())
            .build();
  }
}

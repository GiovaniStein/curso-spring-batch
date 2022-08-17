package com.giovani.migracaobatchdb.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class MigracaoDadosJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job migracaoDadosJob(@Qualifier("migrarPessoaStep") Step migrarPessoaStep,
                                @Qualifier("migrarDadosBancariosStep") Step migrarDadosBancariosStep) {
        return jobBuilderFactory
                .get("migracaoDadosJob")
                //Executar steps paralelos
                .start(stepsParalelos(migrarPessoaStep, migrarDadosBancariosStep))
                .end()
                //Executar cada step separado
                //.start(migrarPessoaStep)
                //.next(migrarDadosBancariosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow stepsParalelos(Step migrarPessoaStep, Step migrarDadosBancariosStep) {

        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosStep")
                .start(migrarDadosBancariosStep)
                .build();

        Flow stepsParalelos = new FlowBuilder<Flow>("stepsParalelos")
                .start(migrarPessoaStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();

        return stepsParalelos;

    }

}

package com.giovani.migracaobatchdb.step;

import com.giovani.migracaobatchdb.dominio.DadosBancarios;
import com.giovani.migracaobatchdb.dominio.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MigrarDadosBancariosStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarDadosBancariosStep(ItemReader<DadosBancarios> arquivoDadosBancariosReader, ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {
        return stepBuilderFactory
                .get("migrarDadosBancariosStep")
                .<DadosBancarios, DadosBancarios>chunk(1000)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }


}

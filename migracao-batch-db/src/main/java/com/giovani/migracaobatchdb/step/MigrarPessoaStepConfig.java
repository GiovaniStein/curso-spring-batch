package com.giovani.migracaobatchdb.step;

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
public class MigrarPessoaStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarPessoaStep(ItemReader<Pessoa> arquivoPessoaReader, ItemWriter<Pessoa> bancoPessoaWriter) {
        return stepBuilderFactory
                .get("migrarPessoaStep")
                .<Pessoa, Pessoa>chunk(1000)
                .reader(arquivoPessoaReader)
                .writer(bancoPessoaWriter)
                .build();
    }

}

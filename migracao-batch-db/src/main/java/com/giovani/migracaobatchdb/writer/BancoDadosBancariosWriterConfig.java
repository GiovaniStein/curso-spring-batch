package com.giovani.migracaobatchdb.writer;

import com.giovani.migracaobatchdb.dominio.DadosBancarios;
import com.giovani.migracaobatchdb.dominio.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class BancoDadosBancariosWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<DadosBancarios> itemPreparedStatementSetter() {

        return new ItemPreparedStatementSetter<DadosBancarios>() {
            @Override
            public void setValues(DadosBancarios dadosBancarios, PreparedStatement ps) throws SQLException {
                ps.setLong(1, dadosBancarios.getId());
                ps.setLong(2, dadosBancarios.getPessoaId());
                ps.setInt(3, dadosBancarios.getAgencia());
                ps.setInt(4, dadosBancarios.getConta());
                ps.setInt(5, dadosBancarios.getBanco());
            }
        };

    }

}

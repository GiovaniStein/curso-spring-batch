package com.giovani.migracaobatchdb.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DadosBancarios {
    private Long id;
    private Long pessoaId;
    private Integer agencia;
    private Integer conta;
    private Integer banco;
}

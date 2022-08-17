package com.giovani.migracaobatchdb.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Pessoa {
    private Long id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Integer idade;
}

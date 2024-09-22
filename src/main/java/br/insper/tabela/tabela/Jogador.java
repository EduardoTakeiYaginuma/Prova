package br.insper.tabela.tabela;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    private String id;
    private String nome;
    private Integer idade;
    private List<Integer> times;



}

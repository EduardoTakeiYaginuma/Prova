package br.insper.tabela.jogador;


import br.insper.tabela.tabela.Jogador;
import br.insper.tabela.tabela.JogadorRepository;
import br.insper.tabela.tabela.JogadorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JogadorServiceTests {

    @InjectMocks
    JogadorService jogadorService;

    @Mock
    JogadorRepository jogadorRepository;

    @Test
    public void salvaJogadorWhenJogadorGetNomeIsNull(){
        Jogador jogador = new Jogador();
        jogador.setNome(null);
        jogador.setIdade(20);
        jogador.setTimes(null);
        assertThrows(ResponseStatusException.class,
                () -> jogadorService.salvarJogador(jogador));
    }
    @Test
    public void salvaJogadorWhenJogadorGetNomeIsEmpty(){
        Jogador jogador = new Jogador();
        jogador.setNome("");
        jogador.setIdade(20);
        jogador.setTimes(null);
        assertThrows(ResponseStatusException.class,
                () -> jogadorService.salvarJogador(jogador));
    }
}

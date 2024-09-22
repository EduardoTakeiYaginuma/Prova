package br.insper.tabela.jogador;


import br.insper.tabela.tabela.Jogador;
import br.insper.tabela.tabela.JogadorRepository;
import br.insper.tabela.tabela.JogadorService;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class jogadorServiceTest {

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


    }
}

package br.insper.tabela.projeto;


import br.insper.tabela.tabela.ProjetoRepository;
import br.insper.tabela.tabela.ProjetoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTests {

    @InjectMocks
    ProjetoService projetoService;

    @Mock
    ProjetoRepository projetoRepository;

//    @Test
//    public void salvaJogadorWhenJogadorGetNomeIsNull(){
//        Projeto jogador = new Projeto();
//        jogador.setNome(null);
//        jogador.setIdade(20);
//        jogador.setTimes(null);
//        assertThrows(ResponseStatusException.class,
//                () -> jogadorService.salvarJogador(jogador));
//    }
//    @Test
//    public void salvaJogadorWhenJogadorGetNomeIsEmpty(){
//        Projeto jogador = new Projeto();
//        jogador.setNome("");
//        jogador.setIdade(20);
//        jogador.setTimes(null);
//        assertThrows(ResponseStatusException.class,
//                () -> jogadorService.salvarJogador(jogador));
//    }
}

package br.insper.tabela.projeto;


import br.insper.tabela.tabela.Projeto;
import br.insper.tabela.tabela.ProjetoRepository;
import br.insper.tabela.tabela.ProjetoService;
import br.insper.tabela.tabela.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTests {

    @InjectMocks
    ProjetoService projetoService;

    @Mock
    private RestTemplate restTemplate; // Supondo que você use o RestTemplate para chamadas HTTP


    @Mock
    ProjetoRepository projetoRepository;

    @Test
    public void salvaProjetoWhenProjetoGetNomeIsNull(){
        Projeto projeto = new Projeto();
        projeto.setNome(null);
        projeto.setDescricao("descricao");
        assertThrows(ResponseStatusException.class,
                () -> projetoService.salvarProjeto(projeto));
    }
    @Test
    public void salvaProjetoWhenProjetoGetDescricaoIsNULL(){
        Projeto projeto = new Projeto();
        projeto.setNome("nome");
        projeto.setDescricao(null);
        assertThrows(ResponseStatusException.class,
                () -> projetoService.salvarProjeto(projeto));
    }



    @Test
    public void salvaProjetoWhenProjetoGetStatusIsNotValid(){
        Projeto projeto = new Projeto();
        projeto.setNome("nome");
        projeto.setDescricao("descricao");
        projeto.setStatus("a");
        projeto.setCpfGerente("123");
        assertThrows(ResponseStatusException.class,
                () -> projetoService.salvarProjeto(projeto));
    }

    @Test
    public void listarProjetosWhenStatusIsNotValid(){
        Projeto projeto = new Projeto();

        Mockito.when(projetoRepository.findAll())
                .thenReturn(List.of(projeto));

        Assertions.assertEquals(List.of(projeto), projetoService.listarProjetos(""));
    }
    @Test
    public void listarProjetosWhenStatusIsPLANEJAMENTO(){
        Projeto projeto = new Projeto();

        Mockito.when(projetoRepository.findByStatus("PLANEJAMENTO"))
                .thenReturn(List.of(projeto));

        Assertions.assertEquals(List.of(projeto), projetoService.listarProjetos("PLANEJAMENTO"));
    }
    @Test
    public void listarProjetosWhenStatusIsEXECUCAO(){
        Projeto projeto = new Projeto();

        Mockito.when(projetoRepository.findByStatus("EXECUCAO"))
                .thenReturn(List.of(projeto));

        Assertions.assertEquals(List.of(projeto), projetoService.listarProjetos("EXECUCAO"));
    }
    @Test
    public void listarProjetosWhenStatusIsFINALIZADO(){
        Projeto projeto = new Projeto();

        Mockito.when(projetoRepository.findByStatus("FINALIZADO"))
                .thenReturn(List.of(projeto));

        Assertions.assertEquals(List.of(projeto), projetoService.listarProjetos("FINALIZADO"));
    }

    @Test
    public void salvarProjeto(){
        Projeto projeto = new Projeto();
        projeto.setNome("nome");
        projeto.setDescricao("descricao");
        projeto.setStatus("PLANEJAMENTO");
        projeto.setCpfGerente("123");

        Mockito.when(projetoRepository.save(projeto))
                .thenReturn(projeto);

        projetoService.salvarProjeto(projeto);

    }
    @Test
    public void salvaProjetoWhenProjetoGetCpfGerenteIsNotFound() {
        Projeto projeto = new Projeto();
        projeto.setNome("nome");
        projeto.setDescricao("descricao");
        projeto.setStatus("PLANEJAMENTO");
        projeto.setCpfGerente("0000");



        // Verificando se a exceção ResponseStatusException é lançada ao salvar o projeto
        assertThrows(HttpServerErrorException.class,
                () -> projetoService.salvarProjeto(projeto));
    }

    @Test
    public void adicionaUsuarioNovoProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("nome");
        projeto.setDescricao("descricao");
        projeto.setStatus("PLANEJAMENTO");
        projeto.setCpfGerente("123");

        Usuario usuario = new Usuario();
        usuario.setCpf("123");

        Mockito.when(projetoRepository.save(projeto))
                .thenReturn(projeto);

        projetoService.adicionaUsuarioProjeto("1", "123");
    }







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

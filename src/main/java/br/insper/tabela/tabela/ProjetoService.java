package br.insper.tabela.tabela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public void salvarProjeto(Projeto projeto) {
        if (projeto.getNome() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do jogador não pode ser nulo");
        }
        if (projeto.getDescricao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do jogador não pode ser nula");
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Usuario> usuario = restTemplate.getForEntity(
                "http://184.72.80.215:8080/usuario/" + projeto.getCpfGerente(),
                Usuario.class);
        if (!usuario.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar usuário com cpf " + projeto.getCpfGerente());
        }

        if (projeto.getStatus().equals("PLANEJAMENTO") || projeto.getStatus().equals("EXECUCAO") || projeto.getStatus().equals("FINALIZADO")) {
            List<Usuario> members = new ArrayList<>();
            projeto.setMembros(members);
            projetoRepository.save(projeto);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status do jogador deve ser PLANEJAMENTO, DESENVOLVIMENTO ou CONCLUIDO");
        }

    }


    public List<Projeto> listarProjetos(String status) {
        if (status.equals("PLANEJAMENTO") || status.equals("EXECUCAO") || status.equals("FINALIZADO")) {
            return projetoRepository.findByStatus(status);
        }
        else {
            return projetoRepository.findAll();
        }
    }

    public void adicionaUsuarioProjeto(String id_projeto, String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Usuario> usuario = restTemplate.getForEntity(
                "http://184.72.80.215:8080/usuario/" + cpf,
                Usuario.class);
        if (!usuario.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar usuário com cpf " + cpf);
        }
        Projeto projeto = projetoRepository.findById(id_projeto).orElse(null);
        if (projeto != null) {
            List<Usuario> membros = projeto.getMembros();
            membros.add(usuario.getBody());
            projeto.setMembros(membros);
            projetoRepository.save(projeto);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar projeto com id " + id_projeto);
        }


    }
}

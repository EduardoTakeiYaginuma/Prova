package br.insper.tabela.tabela;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjetoRepository extends MongoRepository<Projeto, String> {
    List<Projeto> findByStatus(String status);
}

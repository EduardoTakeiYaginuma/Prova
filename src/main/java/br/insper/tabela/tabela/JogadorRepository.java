package br.insper.tabela.tabela;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JogadorRepository extends MongoRepository<Jogador, String> {
}

package br.insper.tabela.tabela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public void salvarProjeto(@RequestBody Projeto projeto) {
        projetoService.salvarProjeto(projeto);
    }

    @GetMapping("/{status}")
    public List<Projeto> listarProjetos(@PathVariable String status) {
        return projetoService.listarProjetos(status);
    }

    @PostMapping("/adicionaUsuario/{id_projeto}/{cpf}")
    public void adicionaJogadorTime(@PathVariable String id_projeto, @PathVariable String cpf) {
        projetoService.adicionaUsuarioProjeto(id_projeto, cpf);
    }

}

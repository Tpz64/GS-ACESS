package br.com.fiap.acess_funcionarios_api.controller;

import br.com.fiap.acess_funcionarios_api.dto.FuncionarioRequestDTO;
import br.com.fiap.acess_funcionarios_api.dto.FuncionarioResponseDTO;
import br.com.fiap.acess_funcionarios_api.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrar(
            @RequestBody @Valid FuncionarioRequestDTO request) {
        FuncionarioResponseDTO response = funcionarioService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        FuncionarioResponseDTO response = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        List<FuncionarioResponseDTO> response = funcionarioService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FuncionarioRequestDTO request) {

        FuncionarioResponseDTO response = funcionarioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

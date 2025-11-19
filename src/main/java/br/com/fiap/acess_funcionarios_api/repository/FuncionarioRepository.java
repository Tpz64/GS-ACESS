package br.com.fiap.acess_funcionarios_api.repository;

import br.com.fiap.acess_funcionarios_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

package br.com.befullstack.eiticrud.repositories;

import br.com.befullstack.eiticrud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Usuários
 */
public interface UserRepository extends JpaRepository<User, Integer> {


}

package br.com.befullstack.eiticrud.services;

import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.models.UserDTO;
import br.com.befullstack.eiticrud.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviçoes relacionados ao Usuário
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca Todos os usuários cadastrados
     *
     * @return lista de Usuários
     */
    public List<User> findAll() {

        LOGGER.info("Searching all users");

        List<User> foundUsers = userRepository.findAll();

        LOGGER.info("Found users: {}", foundUsers);

        return foundUsers;
    }

    /**
     * Busca usuário pelo Id
     *
     * @param id Id do usuário
     * @return Usuário localizado
     */
    public User findById(Integer id) {

        LOGGER.info("Finding user by Id: {}", id);

        User foundUser = userRepository.findOne(id);

        LOGGER.info("Found user: {}", foundUser);

        return foundUser;
    }

    /**
     * Salva o novo usuário
     *
     * @param user Novo Usuário
     */
    public User saveUser(User user) {

        LOGGER.info("Saving new user: {}", user);

        user.setRegisterDate(new Date());

        User savedUser = userRepository.save(user);

        LOGGER.info("Saving new user: {}", savedUser);

        return savedUser;
    }

    /**
     * Deletando usuário
     *
     * @param id Id do usuário
     */
    public User deleteUser(Integer id) {

        LOGGER.info("Deleting a user entry with id: {}", id);

        User deletedUser = findById(id);

        LOGGER.info("Deleting user entry: {}", deletedUser);

        userRepository.delete(deletedUser);

        return deletedUser;
    }

    /**
     * Busca usuário filtrando pelo UserDTO
     *
     * @param userDTO UserDTO
     * @return Lista de usuários filtrados
     */
    public List<User> findByUserDTO(UserDTO userDTO) {

        LOGGER.info("filtering the list with UserDTO: {}", userDTO);

        List<User> filteredUuserList = userRepository.findAll().stream()
                .filter(user -> user.getUsername().contains(userDTO.getUsername()))
                .filter(user -> user.getName().contains(userDTO.getName()))
                .filter(user -> user.getEmail().contains(userDTO.getEmail()))
                .collect(Collectors.toList());

        LOGGER.info("Filtered user list: {}", filteredUuserList);

        return filteredUuserList;
    }
}

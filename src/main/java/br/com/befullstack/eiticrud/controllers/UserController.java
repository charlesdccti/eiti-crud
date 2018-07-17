package br.com.befullstack.eiticrud.controllers;

import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.models.UserDTO;
import br.com.befullstack.eiticrud.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller de Usuários
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Página Home
     *
     * @param model Model do MVC
     * @return retorna para a página index.html
     */
    @RequestMapping("/")
    public String home(Model model) {

        LOGGER.info("Home");

        model.addAttribute("message", "Hello World!");

        return "index";
    }

    /**
     * Busca lista de Usuários
     *
     * @param model Model do MVC
     * @return Retorna para página de usuários
     */
    @RequestMapping("/users")
    public String listUsers(Model model) {

        LOGGER.info("Buscando todos os usuários");

        List<User> usersList = userService.findAll();

        model.addAttribute("users", usersList);

        model.addAttribute("userDTO", new UserDTO());

        return "users/user-list";
    }

    /**
     * Edita um usuário
     *
     * @param model Model do MVC
     * @param id    Id do usuário
     * @return retorna para o form se localizar o usuário
     */
    @RequestMapping(path = "/users/edit/{id}", method = RequestMethod.GET)
    public String editUser(Model model, @PathVariable(value = "id") Integer id) {

        LOGGER.info("Buscando usuário por Id");

        model.addAttribute("user", userService.findById(id));

        return "users/user-form";
    }

    /**
     * Gera novo usuário
     *
     * @param model Model do MVC
     * @return retorna para o form criando novo usuário
     */
    @RequestMapping(path = "/users/add", method = RequestMethod.GET)
    public String createUser(Model model) {

        LOGGER.info("Gerando novo usuário");

        model.addAttribute("user", new User());

        return "users/user-form";
    }

    /**
     * Salvar o novo usuário
     *
     * @param user Novo usuário
     * @return retorna para lista de usuários
     */
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String saveUser(User user) {

        LOGGER.info("Salvando novo usuário");

        userService.saveUser(user);

        return "redirect:/users";
    }

    @RequestMapping(path = "/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(name = "id") Integer id) {

        LOGGER.info("Apagando usuário");

        userService.deleteUser(id);

        return "redirect:/users";
    }

    @RequestMapping(path = "/users/filter", method = RequestMethod.POST)
    public String filterListUser(UserDTO userDTO, Model model) {

        LOGGER.info("Filtrando a lista");

        List<User> userList = userService.findByUserDTO(userDTO);

        model.addAttribute("users", userList);

        model.addAttribute("userDTO", new UserDTO());

        return "users/user-list";
    }

}

package br.com.befullstack.eiticrud.controllers;

import br.com.befullstack.eiticrud.errors.UserNotFoundException;
import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.models.UserDTO;
import br.com.befullstack.eiticrud.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller de Usuários
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private final MessageSource messageSource;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        messageSource = null;
    }

    /**
     * Página Home
     *
     * @param model Model do MVC
     * @return retorna para a página index.html
     */
    @GetMapping("/")
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
    @GetMapping("/users")
    public String listUsers(Model model) {

        LOGGER.info("Buscando todos os usuários");

        List<User> usersList = userService.findAll();

        model.addAttribute("users", usersList);

        model.addAttribute("userDTO", new UserDTO());

        return "users/user-list";
    }

    /**
     * Gera novo usuário
     *
     * @param model Model do MVC
     * @return retorna para o form criando novo usuário
     */
    @GetMapping("/users/add")
    public String createUser(Model model) {

        LOGGER.info("Gerando novo usuário");

        model.addAttribute("user", new User());

        return "users/user-form";
    }

    /**
     * Edita um usuário
     *
     * @param model Model do MVC
     * @param id    Id do usuário
     * @return retorna para o form se localizar o usuário
     */
    @GetMapping("/users/edit/{id}")
    public String editUser(Model model, @PathVariable(value = "id") Integer id, BindingResult result) {

        LOGGER.debug("Updating a user entry with id: {}", id);


        model.addAttribute("user", userService.findById(id));

        return "users/user-form";
    }

    /**
     * Salvar o novo usuário
     *
     * @param user Novo usuário
     * @return retorna para lista de usuários
     */
    @PostMapping("/users")
    public String saveUser(@Valid User user, BindingResult result, RedirectAttributes attributes) {

        LOGGER.debug("Adding a new user entry with information: {}", user);

        if (result.hasErrors()) {
            LOGGER.debug("Add user form was submitted with binding errors. Rendering form view.");
            return "users/user-form";
        }

        User savedUser = userService.saveUser(user);

        LOGGER.debug("Added a to-do entry with information: {}", savedUser);

        return createRedirectViewPath("/users");
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id, RedirectAttributes attributes) throws UserNotFoundException {

        LOGGER.debug("Deleting a user entry with id: {}", id);

        User deleteUser = userService.deleteUser(id);

        LOGGER.debug("Deleted user entry with information: {}", deleteUser);

        return createRedirectViewPath("/users");
    }

    @PostMapping("/users/filter")
    public String filterListUser(UserDTO userDTO, Model model) {

        LOGGER.info("Filtrando a lista");

        List<User> userList = userService.findByUserDTO(userDTO);

        model.addAttribute("users", userList);

        model.addAttribute("userDTO", new UserDTO());

        return "users/user-list";
    }

    private String createRedirectViewPath(String requestMapping) {

        return "redirect:" + requestMapping;
    }

}

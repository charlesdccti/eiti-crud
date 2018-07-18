package br.com.befullstack.eiticrud.controllers;

import br.com.befullstack.eiticrud.errors.UserNotFoundException;
import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService mockUserService;

    private User mockUser;

    private List<User> mockUserList = new ArrayList<>();

    @Before
    public void setup() {

        mockUser = new User().builder()
                .id(1)
                .username("deyvedvm")
                .name("Deyve")
                .surname("Vieira")
                .email("deyvedvm@hotmail.com")
                .phone("21981962657")
                .registerDate(LocalDate.now())
                .isEnabled(true)
                .build();

        mockUserList.add(mockUser);

        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldReturnToHome() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void shouldReturnUsersList() throws Exception {

        when(mockUserService.findAll())
                .thenReturn(mockUserList);

        mockMvc.perform(get("/users").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("users/user-list"));
    }

    @Test
    public void shouldInsertUser() throws Exception {

        when(mockUserService.saveUser(isA(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    public void shouldEditUser() throws Exception {

        when(mockUserService.findById(mockUser.getId())).thenReturn(mockUser);

        mockMvc.perform(
                get("/users/edit/{id}", mockUser.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("users/user-form"));

        verify(mockUserService, times(1)).findById(mockUser.getId());

        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        when(mockUserService.deleteUser(mockUser.getId())).thenReturn(mockUser);

        mockMvc.perform(get("/users/delete/{id}", mockUser.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));

        verify(mockUserService, times(1)).deleteUser(mockUser.getId());

        verifyNoMoreInteractions(mockUserService);
    }

    @Test
    public void findByIdUserNotFoundsShouldRender404View() throws Exception {

        when(mockUserService.findById(1)).thenThrow(new UserNotFoundException(""));

        mockMvc.perform(get("/users/edit/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404"));

        verify(mockUserService, times(1)).findById(1);

        verifyZeroInteractions(mockUserService);
    }
}
package br.com.befullstack.eiticrud.controllers;

import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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

    }

    @Test
    public void shouldInsertUser() throws Exception {

    }

    @Test
    public void shouldReturnUsersList() throws Exception {

        given(userService.findAll()).willReturn(mockUserList);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk());


    }
}
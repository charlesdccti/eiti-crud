package br.com.befullstack.eiticrud.services;

import br.com.befullstack.eiticrud.models.User;
import br.com.befullstack.eiticrud.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceTest {

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
    public void shouldFindAllUsers() {

        UserRepository mockUserRepository = mock(UserRepository.class);

        when(mockUserRepository.findAll()).thenReturn(mockUserList);

        UserService userService = new UserService(mockUserRepository);

        List<User> userList = userService.findAll();

        assertEquals(userList.size(), mockUserList.size());
    }

}
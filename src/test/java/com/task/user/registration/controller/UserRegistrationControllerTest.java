package com.task.user.registration.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.user.registration.dto.UserRequest;
import com.task.user.registration.exception.ExceptionCode;
import com.task.user.registration.exception.UserAlreadyExistsException;
import com.task.user.registration.entity.User;
import com.task.user.registration.repository.UserRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRegistrationController.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepositoryImpl userRepositoryMock;

    @InjectMocks
    private UserRegistrationController controller;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRegisterNewUser() throws Exception {
        UserRequest request = mockUserRequest("firstUser");
        given(userRepositoryMock.createUser(any(User.class))).willReturn(request.toUser());

        String response = mockMvc.perform(post("/userservice/register")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        User responseUser = mapper.readValue(response, User.class);
        Assert.assertEquals(request.toUser(), responseUser);
    }

    @Test
    public void testRegisterExistingUser() throws Exception {
        UserRequest request = mockUserRequest("firstUser");
        when(userRepositoryMock.createUser(eq(request.toUser()))).thenThrow(new UserAlreadyExistsException());

        String response = mockMvc.perform(post("/userservice/register")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = mapper.readTree(response);
        Assert.assertEquals(ExceptionCode.USER_ALREADY_EXISTS.name(), jsonNode.get("code").asText());
        Assert.assertEquals("A user with the given username already exists",
                jsonNode.get("description").asText());
    }

    @Test
    public void testRegisterUserEmptyArguments() throws Exception {
        UserRequest request = mockUserRequest("");
        given(userRepositoryMock.createUser(any(User.class))).willReturn(request.toUser());

        String response = mockMvc.perform(post("/userservice/register")
                     .content(mapper.writeValueAsString(request))
                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = mapper.readTree(response);
        Assert.assertEquals("Invalid userRequest", jsonNode.get("code").asText());
        Assert.assertNotNull(jsonNode.get("description").asText());
    }

    @Test
    public void testRegisterUserEmptyRequest() throws Exception {
        UserRequest request = new UserRequest();
        given(userRepositoryMock.createUser(any(User.class))).willReturn(request.toUser());

        String response = mockMvc.perform(post("/userservice/register")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = mapper.readTree(response);
        Assert.assertEquals("Invalid userRequest", jsonNode.get("code").asText());
        Assert.assertNotNull(jsonNode.get("description").asText());
    }

    private UserRequest mockUserRequest(String userName) {
        UserRequest request = new UserRequest();
        request.setFirstName("firstname");
        request.setLastName("lastname");
        request.setUserName(userName);
        request.setPassword("passwd");
        return request;
    }
}

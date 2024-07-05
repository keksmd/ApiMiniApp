package org.alexkekiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ApiRequestManagerTest {
    @InjectMocks

    ApiRequestManager apiRequestManager;
    @Mock
    ApiRequestService apiRequestService;
    @Mock
    UserDTO userDTO;

    @BeforeEach
    void init() {
        apiRequestService = mock(ApiRequestService.class);
        this.apiRequestManager = new ApiRequestManager(apiRequestService);
        userDTO = mock(UserDTO.class);
        when(userDTO.getEmail()).thenReturn("email");
    }

    @Test
    public void takeRoleTest() {
        String[] roles = {"role1", "role2", "role3"};
        when(apiRequestService.executeGet(anyString(), any(Map.class))).thenReturn(new ResponseEntity(roles, HttpStatus.OK));
        assert Arrays.asList(roles).contains(apiRequestManager.takeRole());
    }

    @Test
    public void registerUserTest() {
        ResponseEntity<String> rightAnswer = new ResponseEntity<>("\"Данные внесены\"", HttpStatus.OK);
        when(apiRequestService.executePost(anyString(), any(Map.class), any())).thenReturn(rightAnswer);
        assert this.apiRequestManager.registerUser(userDTO);
    }

    @Test
    public void takeCodeTest() {
        ResponseEntity<String> rightAnswer = new ResponseEntity<>("code", HttpStatus.OK);
        when(apiRequestService.executeGet(anyString(), any(LinkedMultiValueMap.class))).thenReturn(rightAnswer);

        verify(apiRequestService,times(1)).executeGet(anyString(),any(Map.class));
        assert apiRequestManager.takeCode(userDTO) != null;

    }
@Test
    public void setStatusIncreasedTest() {
        String code = "111";
        apiRequestManager.setStatusIncreased(code, userDTO);
        ResponseEntity<String> rightAnswer = new ResponseEntity<>("Данные внесены", HttpStatus.OK);
        when(apiRequestService.executePost(anyString(),any(Map.class),any())).thenReturn(rightAnswer);
        this.apiRequestManager.setStatusIncreased(code, userDTO);
        verify(apiRequestService,times(1)).executePost(anyString(),any(Map.class),any());

    }
}

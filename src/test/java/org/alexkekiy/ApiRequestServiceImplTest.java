package org.alexkekiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiRequestServiceImplTest {
    @InjectMocks
    ApiRequestServiceImpl apiRequestService;
    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void init() {
        restTemplate = mock(RestTemplate.class);
        apiRequestService = new ApiRequestServiceImpl(restTemplate);

    }

    @Test
    public void testExecutePost() {

        when(restTemplate.exchange(anyString(), HttpMethod.POST, any(), any(Class.class), any(Map.class))).thenReturn(new ResponseEntity<>("Response Body", HttpStatus.OK));
        ResponseEntity<String> rightAnswer = new ResponseEntity<>("Response Body", HttpStatus.OK);
        assertEquals(rightAnswer, apiRequestService.executePost("/users", null, null));
        verify(restTemplate, times(1)).exchange(anyString(), HttpMethod.POST, any(), any(Class.class), any(Map.class));
    }

    @Test
    void testExecuteGet() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", "example@example.com");

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Response Body", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), HttpMethod.GET, any(), any(Class.class), any(Map.class))).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = apiRequestService.executeGet("/users", queryMap);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(), any(Class.class), any(Map.class));
    }

}

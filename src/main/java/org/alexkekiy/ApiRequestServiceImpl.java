package org.alexkekiy;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiRequestServiceImpl implements ApiRequestService {

    private final RestTemplate restTemplate;

    public ApiRequestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<String> sendRequest(String url, HttpMethod method, Object requestBody, Map<String, ?> uriVariables, HttpHeaders headers) {

        if (requestBody != null) {
            HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, headers);
            return restTemplate.exchange(url, method, httpEntity, String.class, uriVariables);
        } else {
            return restTemplate.exchange(url, method, new HttpEntity<>(headers), String.class, uriVariables);
        }

    }

    @Override
    public ResponseEntity<String> executeGet(String adress, Map<String, ?> queryMap) {
        return this.sendRequest(
                adress, HttpMethod.GET,
                null,
                queryMap == null ? new HashMap<>() : queryMap
                , getHeaders());
    }

    @Override
    public ResponseEntity<String> executePost(String adress, Map<String, ?> queryMap, Object requestBody) {
        return this.sendRequest(
                adress, HttpMethod.POST,
                requestBody,
                queryMap == null ? new HashMap<>() : queryMap
                , getHeaders());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

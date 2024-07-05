package org.alexkekiy;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ApiRequestService {

    ResponseEntity<String> executeGet(String adress, Map<String, ?> queryMap);

    ResponseEntity<String> executePost(String adress, Map<String, ?> queryMap, Object requestBody);
}

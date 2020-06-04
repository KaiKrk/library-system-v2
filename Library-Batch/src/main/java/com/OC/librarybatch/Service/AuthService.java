package com.OC.librarybatch.Service;

import com.OC.librarybatch.Entity.AuthDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();



    public HttpHeaders getJwtForBatchService() throws JSONException, JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("username", "annalibraryoc@gmail.com");
        personJsonObject.put("password", "123");
        HttpEntity<String> request =
                new HttpEntity<>(personJsonObject.toString(), headers);

        AuthDetails authDetails = restTemplate.
                postForObject("http://localhost:8080/authenticate", request, AuthDetails.class);

        HttpHeaders authorizationHeader = new HttpHeaders();
        authorizationHeader.setContentType(MediaType.APPLICATION_JSON);

        JSONObject authorizationJsonObject = new JSONObject();
        authorizationJsonObject.put("Authorization", "Bearer " + authDetails.getToken());
        authorizationHeader.set("Authorization","Bearer " + authDetails.getToken());
        return authorizationHeader;
    }
}

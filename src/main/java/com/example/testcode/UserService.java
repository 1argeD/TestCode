package com.example.testcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import static org.springframework.http.RequestEntity.get;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class UserService {
    private final String serviceHost;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate rt,
                       @Value("${user-service.host:user-service}") String sh) {
        this.serviceHost = sh;
        this.restTemplate = rt;
    }

    public User getAuthenticatedUser() {
        URI url = URI.create(String.format("http://%s/uaa/v1/me", serviceHost));
        RequestEntity<Void> request = get(url).header(HttpHeaders.CONTENT_TYPE,
                APPLICATION_JSON_VALUE).build();
        return restTemplate.exchange(request, User.class).getBody();
    }
}

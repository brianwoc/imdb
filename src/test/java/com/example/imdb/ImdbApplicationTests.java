package com.example.imdb;

import com.example.imdb.json.Search;
import com.example.imdb.json.Search_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImdbApplicationTests {
    //    String URL = "http://www.omdbapi.com/?i=tt3896198&apikey=1d711ff6";
    String URL = "http://www.omdbapi.com/?apikey=1d711ff6&t=super";
    String searchUrl = "http://www.omdbapi.com/?apikey=1d711ff6&s=Batman Begins";
    RestTemplate restTemplate = new RestTemplate();

    @Test
    void try_connection_with_given_url() {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        String body = response.getBody();
        System.out.println(body);
        Assertions.assertNotNull(response);
    }
    @Test
    void try_search_with_given_url() {
        ResponseEntity<String> response = restTemplate.exchange(searchUrl, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        String body = response.getBody();
        System.out.println(body);
        final String responseJson = restTemplate.getForObject(searchUrl, String.class);
        Assertions.assertNotNull(response);
        System.out.println(responseJson);

    }
    @Test
    void try_to_get_search_object(){
        Search search = restTemplate.getForObject(searchUrl, Search.class);
        Search_ search_ = restTemplate.getForObject(searchUrl, Search_.class);

        System.out.println(search.getTotalResults());
        System.out.println(search_.getTitle());
        Assertions.assertEquals("Batman Begins", search.getSearch().get(1).getTitle());
    }


}

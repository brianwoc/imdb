package com.example.imdb;

import com.example.imdb.json.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class URL {
//    RestTemplate restTemplate = new RestTemplate();
    URLBuilder urlb;

    public URL() {
        urlb= new URLBuilder("www.omdbapi.com");
        urlb.setConnectionType("http");
        urlb.addParameter("apikey", "1d711ff6");
    }
    @Test
    public void get_basic_url() throws MalformedURLException, URISyntaxException {
        Assertions.assertEquals("http://www.omdbapi.com?apikey=1d711ff6", urlb.getURL());
    }
    @Test
    public void get_url_with_search_param() throws MalformedURLException, URISyntaxException {
        urlb.addParameter("s","Batman");
        Assertions.assertEquals("http://www.omdbapi.com?apikey=1d711ff6&s=Batman", urlb.getURL());
    }

    @Test
    public void get_url_with_search_param_and_year() throws MalformedURLException, URISyntaxException {
        urlb.addParameter("s","Batman");
        urlb.addParameter("y","2017");

        Assertions.assertEquals("http://www.omdbapi.com?apikey=1d711ff6&s=Batman&y=2017", urlb.getURL());
    }

}

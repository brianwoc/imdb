package com.example.imdb.controller;

import com.example.imdb.json.Search;
import com.example.imdb.json.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    public URLBuilder createrURL() {
        URLBuilder urlb = new URLBuilder("www.omdbapi.com");
        urlb.setConnectionType("http");
        urlb.addParameter("apikey", "1d711ff6&s");
        return urlb;

    }

    RestTemplate restTemplate;

    @Autowired
    public SearchController(RestTemplate restTemplate) throws MalformedURLException, URISyntaxException {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String getMenu() {
        return "home";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "title", required = true) String title, Model model) throws MalformedURLException, URISyntaxException {

        URLBuilder URL = createrURL();
        URL.addParameter("s", title);
        Search search = restTemplate.getForObject(URL.getURL(),Search.class);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < search.getSearch().size(); i++) {
            list.add(search.getSearch().get(i).getTitle());

        }
        model.addAttribute("list", list);

        model.addAttribute("search", search);
        return "search";
    }
}

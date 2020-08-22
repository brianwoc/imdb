package com.example.imdb.controller;

import com.example.imdb.json.Movie;
import com.example.imdb.json.Search;
import com.example.imdb.json.URLBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    public URLBuilder createrURL() {
        URLBuilder urlb = new URLBuilder("www.omdbapi.com");
        urlb.setConnectionType("http");
        urlb.addParameter("apikey", "1d711ff6");
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
    public String search(@RequestParam(name = "title", required = true) String title,
                         @RequestParam(name = "year", required = false) String year,
                         @RequestParam(name = "page", required = false) String page,
                         @RequestParam(name = "type", required = false) String type,
                         Model model) throws MalformedURLException, URISyntaxException {

        URLBuilder URL = createrURL();
        URL.addParameter("s", title);
        URL.addParameter("y", year);
        URL.addParameter("page", page);
        URL.addParameter("type", type);
        System.out.println(URL.getURL());
        Search search = restTemplate.getForObject(URL.getURL(), Search.class);

        model.addAttribute("movies", search.getSearch());

        search.getSearch().stream().forEach(x -> System.out.println(x));
        return "search";
    }

    @GetMapping("/{id}")
    public String searchWithId(@PathVariable(name = "id", required = true) String id,
                               Model model) throws MalformedURLException, URISyntaxException {

        URLBuilder URL = createrURL();
        URL.addParameter("i", id);
        URL.addParameter("plot", "full");

        System.out.println(URL.getURL());
        Movie movie = restTemplate.getForObject(URL.getURL(), Movie.class);

        model.addAttribute("movie", movie);

        System.out.println(movie.toString());
        return "movie-info";
    }
}

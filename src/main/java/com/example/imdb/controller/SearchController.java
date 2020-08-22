package com.example.imdb.controller;

import com.example.imdb.json.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    RestTemplate restTemplate = new RestTemplate();
    String URL =  "http://www.omdbapi.com/?apikey=1d711ff6&s=";




    @GetMapping("/")
    public String getMenu(MyModelObject myModelObject){
        return "home";
    }

    @GetMapping("/search")
    public String getSearch(){
        return "search-result";

    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "title") String title, Model model, MyModelObject myModelObject){
        Search search = restTemplate.getForObject(URL + title, Search.class);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < search.getSearch().size() ; i++) {
            list.add(search.getSearch().get(i).getTitle());

        }
        model.addAttribute("list", list);

        model.addAttribute("search", search);
        return "search";
    }
}

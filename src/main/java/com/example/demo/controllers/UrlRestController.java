package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class UrlRestController {
    private final Map<String, String> map = new HashMap<>();
    @GetMapping("/createUrl")
    public String createUrl(@RequestParam("in") String inUrl) {
        try {
            new URL(inUrl);
            Map.Entry<String, String> lastKey = null;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                lastKey = entry;
            }
            var outUrl = "";
            if (lastKey == null) {
                outUrl = "1";
            } else {
                outUrl = String.valueOf(Integer.parseInt(lastKey.getKey()) + 1);
            }
            map.put(outUrl, inUrl);
            return outUrl;
        } catch (MalformedURLException e) {
            return "{\"status\": \"not valid url\"}";
        }
    }

    @GetMapping(value = "/{url}")
    public String getUrl(@PathVariable final String url) {
        var str = map.get(url.split("/")[url.split("/").length - 1]);
        if (str != null) {
            return "<head>" +
                    "<meta http-equiv=\"refresh\" content=\"0;URL=" +
                    map.get(url.split("/")[url.split("/").length - 1]) + "\" />" +
                    "</head>";
        } else {
            return "404: Error";
        }
    }
}

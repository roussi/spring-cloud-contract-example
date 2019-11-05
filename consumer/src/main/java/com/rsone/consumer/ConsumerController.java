package com.rsone.consumer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ConsumerController {



    @GetMapping("/greeting")
    public ResponseEntity<String> callGreetingService(){
        String url=String.format("http://localhost:%s", Util.greetingPort);

        WebClient webClient = WebClient.create(url);
        String result = webClient.get()
                .uri("/v2/hello")
                .exchange()
                .flatMap(res-> res.bodyToMono(String.class))
                .map(s-> "Hello " + s.toLowerCase())
                .block();
        return ResponseEntity.ok(result);
    }
}

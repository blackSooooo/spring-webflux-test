package test.webflux.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

@RestController
public class TestController {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:7777")
            .build();

    @GetMapping("/api")
    public Mono<List> test() {

        return webClient.get().uri("/test")
                .retrieve()
                .bodyToMono(List.class);
    }

}

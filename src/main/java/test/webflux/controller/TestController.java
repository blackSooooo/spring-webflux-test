package test.webflux.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
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
import java.util.function.Function;

@Slf4j
@RestController
public class TestController {
    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient()))
            .baseUrl("http://localhost:7777")
            .build();

//    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @GetMapping("/api")
    public Mono<List> test() {
        return webClient.get().uri("/test")
                .retrieve()
                .bodyToMono(List.class);
    }

    @Bean
    public HttpClient httpClient() {
        ConnectionProvider provider = ConnectionProvider.builder("provider")
                .maxConnections(500)
                .pendingAcquireMaxCount(-1)
                .build();

        HttpClient httpClient = HttpClient.create(provider);
        return httpClient;
    }

}

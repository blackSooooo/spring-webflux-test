package test.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
//		System.setProperty("reactor.netty.ioWorkerCount", "4");
		SpringApplication.run(WebfluxApplication.class, args);
	}
}

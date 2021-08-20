package com.venkat.doamin;

import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainService {

    private KafkaTemplate <String,Domain> kafkaTemplate;
    private final String TOPIC_NAME="web-domains";

    public DomainService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void send(String name) {
       Mono<DomainList> domainListMono=WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);

domainListMono.subscribe(domainList -> {
   domainList.domains.forEach(domain -> {
       kafkaTemplate.send(TOPIC_NAME,domain);
       System.out.println("Domain message" + domain.getDomain());

   });
});

    }
}

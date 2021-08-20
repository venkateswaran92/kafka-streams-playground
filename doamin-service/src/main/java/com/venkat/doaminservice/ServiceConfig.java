package com.venkat.doaminservice;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ServiceConfig {

@Bean
    public Consumer<KStream<String,Domain>> consumer(){
    return stringDomainKStream -> stringDomainKStream.foreach((key,domamin)->{
        System.out.println(String.format("DomainConsumer [%$] Status [%$]",key,domamin));
    });
}

}

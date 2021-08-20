package com.venkat.doamin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class ProducerController {

private DomainService domainService;

    public ProducerController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("domain/{name}")
    public String lockUp(@PathVariable("name")final String name){
        domainService.send(name);
        return "finished";
    }

}

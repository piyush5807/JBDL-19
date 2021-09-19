package com.example.jbdl19.restapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    private static Logger log = LoggerFactory.getLogger(MyController.class);

//    @Autowired
    Person person;

    // Creating person object - com.example.jbdl19.restapis.Person@3c291aad

//    @Autowired
    MyConfiguration myConfiguration;


//    public MyController(Person person,
//                        MyConfiguration myConfiguration,
//                        ServerProperties serverProperties) {
//        this.person = person;
//        this.myConfiguration = myConfiguration;
//        log.info("price = {}, quantity = {}", serverProperties.getPrice(), serverProperties.getQuantity());
//
//    }


    public MyController(Person person,
                        MyConfiguration myConfiguration,
                        @Value("${myapp.price.currency}") String currency) {
        this.person = person;
        this.myConfiguration = myConfiguration;
        log.info("currency = {}", currency);

    }


    @GetMapping("/person2")
    public Person getPerson(){
        log.info("p = {}", person);

        person.setEmail("abc@gmail.com");
        return person;
    }

    @GetMapping("/person3")
    public Person getPerson3(){
        log.info("p = {}", person);

        person.setEmail("abc@gmail.com");
        return person;
    }

    @GetMapping(value = "/image2")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") int id){

        if(id == 0){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("ERROR_MSG", "Id cannot be less than 1");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        String url = "https://picsum.photos/id/" + id + "/200/300";
//        RestTemplate restTemplate = new RestTemplate();
        byte[] response = myConfiguration.getTemplate().getForObject(url, byte[].class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        ResponseEntity<byte[]> responseResponseEntity = new ResponseEntity(response, httpHeaders, HttpStatus.ACCEPTED);

        return responseResponseEntity;
    }


}

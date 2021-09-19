package com.example.jbdl19.restapis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class TestController extends Thread{
    @Override
    public void run() {
        super.run();
    }

    private static Logger log = LoggerFactory.getLogger(TestController.class);

//    @Autowired
//    Person person;      // Parameter injection

    Person person;

    @Autowired
    MyConfiguration myConfiguration;

    @Autowired
    TestController(Person per){
        log.info("person = {}", per);
        person = per;

    }

//    TestController(int num, String name){
//        log.info("num = {}, name = {}", num, name);
//    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public Person getPerson(){
//        Person p = new Person();
//
//        log.warn("Returning response");
//        System.out.println("Returning response");
//
//        log.info("p = {}", p);
        log.info("person = {}", person);

        // Creating person object - com.example.jbdl19.restapis.Person@6d1310f6

        return person;

//        return new Person("abc@gmail.com", "ABC", 1.3, "SDF5");
    }

    @GetMapping(value = "/image")
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

//    @RequestMapping(value = "/parse_csv", method = RequestMethod.POST)
//    public void parseCSV(HttpServletRequest httpServletRequest) throws IOException, ServletException {
//
//        log.info("Got a file in the request");
//
//        String header = httpServletRequest.getHeader("test-header");
//
//        Part part = httpServletRequest.getPart("my_file");
//        InputStream inputStream = part.getInputStream();
//
//        Part part2 = httpServletRequest.getPart("my_text");
//
//
//        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withDelimiter(';');
//        CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8), csvFormat);
//        List<CSVRecord> csvRecords = csvParser.getRecords();
//
//        for (CSVRecord csvRecord : csvRecords) {
//            if(csvRecord.getRecordNumber() == 1){
//                continue;
//            }
//            Person p = new Person(
//                    csvRecord.get(0),
//                    csvRecord.get(1),
//                    Double.parseDouble(csvRecord.get(2)),
//                    csvRecord.get(3)
//            );
//
//            // DB call to save the user info
//
////            log.info("email is {}, name is {}, amount is {}, DNI is {}", csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3));
//
//            log.info("person is {}", p);
//        }
//
//    }
}

// LOGGING LEVEL = warn

// 1 hour
// ERROR = 10
// WARN = 30
// INFO = 100
// DEBUG = 500
// TRACE = 5000



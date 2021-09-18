package com.example.jbdl19.restapis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
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
public class TestController {

    // ResponseBody
    // 1. IOC and IOC container
    // 2. DI
    // 3. Beans - objects created in spring
    // 4. Autowired
    // 5. Components and annotations

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public String getMsg(){
        log.warn("Returning response");
        System.out.println("Returning response");
        return "Hello!";
    }

    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") int id){

        if(id == 0){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("ERROR_MSG", "Id cannot be less than 1");
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        String url = "https://picsum.photos/id/" + id + "/200/300";
        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate.getForObject(url, byte[].class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE);

        ResponseEntity<byte[]> responseResponseEntity = new ResponseEntity(response, httpHeaders, HttpStatus.ACCEPTED);

        return responseResponseEntity;
    }

    @RequestMapping(value = "/parse_csv", method = RequestMethod.POST)
    public void parseCSV(HttpServletRequest httpServletRequest) throws IOException, ServletException {

        log.info("Got a file in the request");

        String header = httpServletRequest.getHeader("test-header");

        Part part = httpServletRequest.getPart("my_file");
        InputStream inputStream = part.getInputStream();

        Part part2 = httpServletRequest.getPart("my_text");


        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withDelimiter(';');
        CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream, StandardCharsets.UTF_8), csvFormat);
        List<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            if(csvRecord.getRecordNumber() == 1){
                continue;
            }
            Person p = new Person(
                    csvRecord.get(0),
                    csvRecord.get(1),
                    Double.parseDouble(csvRecord.get(2)),
                    csvRecord.get(3)
            );

            // DB call to save the user info

//            log.info("email is {}, name is {}, amount is {}, DNI is {}", csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3));

            log.info("person is {}", p);
        }

    }
}

// LOGGING LEVEL = warn

// 1 hour
// ERROR = 10
// WARN = 30
// INFO = 100
// DEBUG = 500
// TRACE = 5000



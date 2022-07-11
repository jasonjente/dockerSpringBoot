package project.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationInfoController {
    @GetMapping("/applicationStatus")
    public ResponseEntity<String> getInfo(){
        return ResponseEntity.ok("Application is up");
    }


}

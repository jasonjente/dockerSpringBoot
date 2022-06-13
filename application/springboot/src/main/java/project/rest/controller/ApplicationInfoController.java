package project.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import project.service.DataService;

@Controller
public class ApplicationInfoController {

    @Autowired
    private DataService dataService;

    @GetMapping("/applicationStatus")
    public ResponseEntity<String> getInfo(){
        return ResponseEntity.ok("Application is up");
    }


}

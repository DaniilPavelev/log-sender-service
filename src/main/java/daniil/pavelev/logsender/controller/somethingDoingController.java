package daniil.pavelev.logsender.controller;

import daniil.pavelev.logsender.service.SendLogInter;
import daniil.pavelev.logsender.service.SendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class somethingDoingController {

    @Autowired
    private SendLogInter service;

    @GetMapping("/doSomething")
    public String doSomething(){
        return service.doSomething();
    }
}

package com.awsexample.controller;

import com.awsexample.service.Ec2Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ec2")
public class Ec2Controller {

    private final Ec2Service ec2Service;

    public Ec2Controller(Ec2Service ec2Service) {
        this.ec2Service = ec2Service;
    }

    @PostMapping("/launch")
    public String launchInstance(@RequestParam String amiId, @RequestParam String instanceName) {
        System.out.println("ami-id="+amiId+", instanceName="+instanceName);
        String id = ec2Service.createInstance(amiId, instanceName);
        return "Successfully launched EC2 instance: " + id;
    }
}

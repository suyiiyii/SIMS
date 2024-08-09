package top.suyiiyii.sims.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthzController {
    @GetMapping("/healthz")
    public String healthz() {
        return "ok";
    }

    @PostMapping("/healthz")
    public HealthzResponse healthzPost() {
        return new HealthzResponse("health");
    }

    @AllArgsConstructor
    @Data
    public static class HealthzResponse {
        private String status;
    }

}

package top.suyiiyii.sims.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.common.AuthAccess;

@RestController
public class HealthzController {
    @AuthAccess(allowRoles = {"guest"})
    @GetMapping("/healthz")
    public String healthz() {
        return "ok";
    }

    @AuthAccess(allowRoles = {"guest"})
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

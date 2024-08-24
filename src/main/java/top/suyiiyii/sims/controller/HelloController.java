package top.suyiiyii.sims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;

import java.util.List;

@RestController
public class HelloController {

    @AuthAccess(allowRoles = {"guest"})
    @GetMapping("/hello")
    public String hello(String username) {
        return "Hello " + username;
    }

    @AuthAccess(allowRoles = {"guest"})
    @PostMapping("/hello")
    public List<String> helloPost(String username, Integer age) {
        List<String> list = List.of(username, age.toString());
        return list;
    }

    @AuthAccess(allowRoles = {"guest"})
    @GetMapping("/helloResult")
    public Result healthz() {
        return Result.success("Hello World");
    }

}

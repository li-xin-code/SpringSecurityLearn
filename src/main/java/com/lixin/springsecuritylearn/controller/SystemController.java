package com.lixin.springsecuritylearn.controller;

import com.lixin.springsecuritylearn.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @date 2021/6/1
 */
@RestController
public class SystemController {

    @GetMapping("/test")
    public String t() {
        return "ok";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PreAuthorize("hasAuthority('look')")
    @GetMapping("/look")
    public String look() {
        return "look";
    }

    @PreAuthorize("hasAuthority('del')")
    @GetMapping("/del")
    public String del() {
        return "del";
    }

    @PreAuthorize("hasAuthority('add')")
    @GetMapping("/add")
    public String add() {
        return "add";
    }

}

package com.woowahan.techcourse.resourceserver.ui;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/user")
    public ResponseEntity<Void> findUser(HttpServletRequest request) {
//        String accessToken = request.getHeader("Authorization");
//        System.out.println(accessToken);

        return ResponseEntity.ok(null);
    }
}

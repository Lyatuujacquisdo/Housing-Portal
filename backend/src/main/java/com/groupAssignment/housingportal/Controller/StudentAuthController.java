package com.groupAssignment.housingportal.Controller;

import com.groupAssignment.housingportal.Dto.LoginDTO;
import com.groupAssignment.housingportal.Dto.RegisterDTO;
import com.groupAssignment.housingportal.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class StudentAuthController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterDTO studentDTO){
        return ResponseEntity.ok(authService.registerStudent(studentDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginRequest(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(authService.loginStudent(loginDTO));
    }
}

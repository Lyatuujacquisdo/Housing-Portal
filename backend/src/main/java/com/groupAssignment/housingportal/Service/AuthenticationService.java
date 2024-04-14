package com.groupAssignment.housingportal.Service;

import com.groupAssignment.housingportal.Controller.AuthenticationResponse;
import com.groupAssignment.housingportal.Dto.RegisterDTO;
import com.groupAssignment.housingportal.Model.Role;
import com.groupAssignment.housingportal.Model.RoomStatus;
import com.groupAssignment.housingportal.Model.Student;
import com.groupAssignment.housingportal.Repo.StudentRepo;
import com.groupAssignment.housingportal.Config.JwtService;
import com.groupAssignment.housingportal.Dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //in charge of hashing the password, it's from the Spring Security dependency

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse registerStudent(RegisterDTO studentDTO) {
        Optional<Student> otherUser = studentRepo.findByRegistrationNo(studentDTO.getRegistrationNo());

        if(otherUser.isEmpty()){
            //below is equal to Student student = new Student() and setting all the object values
            Student student = Student.builder()
                    .email(studentDTO.getEmail())
                    .registrationNo(studentDTO.getRegistrationNo())
                    .firstName(studentDTO.getFirstName())
                    .lastName(studentDTO.getLastName())
                    .gender(studentDTO.getGender())
                    .password(passwordEncoder.encode(studentDTO.getPassword()))
                    .roomStatus(RoomStatus.NOT_ASSIGNED)
                    .role(Role.USER)
                    .build();
            studentRepo.save(student);

            var jwtToken = jwtService.generateToken(student);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .registrationNo(student.getRegistrationNo())
                    .message("successfully registered ")
                    .build();
        }

       else {
            return AuthenticationResponse.builder()
                    .message("USER ALREADY EXISTS!")
                    .build();
        }
    }

    public AuthenticationResponse loginStudent(@RequestBody LoginDTO loginDTO){
        Authentication authentication= authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        loginDTO.getRegistrationNo(),
                        loginDTO.getPassword()
                )
        );

        Optional<Student> student = studentRepo.findByRegistrationNo(loginDTO.getRegistrationNo());

        if(student.isEmpty()){
            return  AuthenticationResponse.builder()
                    .message("Incorrect username/password!")
                    .build();
        }

        var jwtToken = jwtService.generateToken(student.get());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .registrationNo(student.get().getRegistrationNo())
                .message("Successfully logged in!")
                .build();
    }
}

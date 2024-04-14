package com.groupAssignment.housingportal.Initializer;

import com.groupAssignment.housingportal.Model.Role;
import com.groupAssignment.housingportal.Model.Student;
import com.groupAssignment.housingportal.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!studentRepo.existsByRegistrationNo("admin")) {
            // Create admin user

            Student admin = Student.builder()
                            .email("admin@email.com")
                            .password(passwordEncoder.encode("admin"))
                            .role(Role.ADMIN)
                            .registrationNo("ADMIN")
                            .build();

            studentRepo.save(admin);
        }
    }
}

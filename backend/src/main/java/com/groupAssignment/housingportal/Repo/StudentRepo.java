package com.groupAssignment.housingportal.Repo;

import com.groupAssignment.housingportal.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//repo is an interface to the database

@EnableJpaRepositories
@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    Optional<Student>  findByRegistrationNo(String registrationNo);

    boolean existsByRegistrationNo(String registrationNo);
}

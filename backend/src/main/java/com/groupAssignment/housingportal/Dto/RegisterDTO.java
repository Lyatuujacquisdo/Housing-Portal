package com.groupAssignment.housingportal.Dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    private String registrationNo;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
}

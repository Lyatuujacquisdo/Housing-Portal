package com.groupAssignment.housingportal.Response;

import com.groupAssignment.housingportal.Model.Room;
import com.groupAssignment.housingportal.Model.RoomStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private String registrationNo;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
    private RoomStatus roomStatus;
    private Room room;
}

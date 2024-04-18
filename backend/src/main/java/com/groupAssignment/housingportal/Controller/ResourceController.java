package com.groupAssignment.housingportal.Controller;


import com.groupAssignment.housingportal.Controller.RequestDTO.BlockRequest;
import com.groupAssignment.housingportal.Controller.RequestDTO.BlocksRequest;
import com.groupAssignment.housingportal.Model.Room;
import com.groupAssignment.housingportal.Model.Student;
import com.groupAssignment.housingportal.Service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/acc")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/profile")
    public Student getStudentDetails(Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String registrationNo = userDetails.getUsername();
        return  resourceService.getProfile(registrationNo);
    }

    @PostMapping("/register/one")
    public List<Room> registerBlock(@RequestBody BlockRequest blockRequest, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String registrationNo = userDetails.getUsername();
        return  resourceService.registerBlock(blockRequest.getBlockNumber(),registrationNo);
    }

    @PostMapping("/register/several")
    public List<Room> registerBlock(@RequestBody BlocksRequest blocksRequest,Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String registrationNo = userDetails.getUsername();
        return  resourceService.registerBlock(blocksRequest.getBlockNumbers(),registrationNo);
    }

    @GetMapping("/vacant")
    public List<Room> getVacantRooms(){
        return resourceService.getVacantRooms();
    }

    @PostMapping("/select")
    public String selectRoom(@RequestParam String roomLabel, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String registrationNo = userDetails.getUsername();
        return resourceService.selectRoom(roomLabel, registrationNo);
    }

    @GetMapping("/roomies")
    public List<Student> getRoomies(@RequestParam String roomLabel, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String registrationNo = userDetails.getUsername();
        return resourceService.getRoomies(roomLabel);
    }
}

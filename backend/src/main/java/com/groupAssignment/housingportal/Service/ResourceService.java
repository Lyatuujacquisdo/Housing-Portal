package com.groupAssignment.housingportal.Service;

import com.groupAssignment.housingportal.Model.Role;
import com.groupAssignment.housingportal.Model.Room;
import com.groupAssignment.housingportal.Model.RoomStatus;
import com.groupAssignment.housingportal.Model.Student;
import com.groupAssignment.housingportal.Repo.RoomRepo;
import com.groupAssignment.housingportal.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private RoomRepo roomRepo;

    public Student getProfile(String registrationNo) throws Exception {
        Optional<Student> student = studentRepo.findByRegistrationNo(registrationNo);
        return student.orElse(null);
    }

    public List<Room> registerBlock(int blockNumber, String registrationNo){
        Optional<Student> student = studentRepo.findByRegistrationNo(registrationNo);

        if(student.isEmpty() || student.get().getRole() != Role.ADMIN){
            System.out.println("not admin");
            return null;
        }
        List<Room> rooms = new ArrayList<>();
        //SIDE-A
        for (int i =1 ; i <= 5 ; i++){
            Room room =  Room.builder()
                    .block(blockNumber)
                    .side('A')
                    .roomNumber(i)
                    .roomLabel(blockNumber + "A" + "-" + i)
                    .vacancy(4)
                    .build();
            rooms.add(room);
            roomRepo.save(room);
        }
        //SIDE-B
        for (int i =1 ; i <= 5 ; i++){
            Room room =  Room.builder()
                    .block(blockNumber)
                    .side('B')
                    .roomNumber(i)
                    .roomLabel(blockNumber + "B" + "-" + i)
                    .vacancy(4)
                    .build();
            rooms.add(room);
            roomRepo.save(room);

        }
        return rooms;
    }
    public List<Room> registerBlock(int[] blockNumbers, String registrationNo){
        Optional<Student> student = studentRepo.findByRegistrationNo(registrationNo);

        if(student.isEmpty() || student.get().getRole() != Role.ADMIN){
           System.out.println("not admin");
           return null;
        }

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i<blockNumbers.length ;i++ ){
            //SIDE-A
            for (int j = 1 ; j <= 5 ; j++){
                Room room =  Room.builder()
                        .block(blockNumbers[i])
                        .side('A')
                        .roomNumber(i)
                        .roomLabel(String.valueOf(blockNumbers[i] + 'A' + '-' + i))
                        .vacancy(4)
                        .build();
                rooms.add(room);
                roomRepo.save(room);
            }
            //SIDE-B
            for (int k = 1 ; k <= 5 ; k++){
                Room room =  Room.builder()
                        .block(blockNumbers[i])
                        .side('B')
                        .roomNumber(i)
                        .roomLabel(String.valueOf(blockNumbers[i] + 'A' + '-' + i))
                        .vacancy(4)
                        .build();
                rooms.add(room);
                roomRepo.save(room);

            }
        }
        return rooms;
    }

    public Room removeRoom(){return null;}

    public Room addRoom(){return null;}

    public List<Room> getVacantRooms(){
        List<Room> allRooms = roomRepo.findAll();
        //vacant rooms
       return allRooms.stream().filter(room -> room.getVacancy() > 0).collect(Collectors.toList());
    }

    public String selectRoom(String roomLabel, String registrationNo){
        Optional<Room> room = roomRepo.findByRoomLabel(roomLabel);
        if(room.isEmpty()){
            System.out.println("No such room");
            return null;
        }
        Optional<Student> student = studentRepo.findByRegistrationNo(registrationNo);
        if(student.isEmpty()){
            System.out.println("No such student");
            return null;
        }
        Room selectedRoom = room.get();
        selectedRoom.setVacancy(selectedRoom.getVacancy() - 1);
        selectedRoom.getStudents().add(student.get());
        student.get().setRoomID(selectedRoom.getRoomLabel());
        student.get().setRoomStatus(RoomStatus.ASSIGNED);
        studentRepo.save(student.get());
        roomRepo.save(selectedRoom);

        return student.get().getFirstName() + student.get().getLastName() +" has selected Room: " + roomLabel;
    }

    public List<Student> selectPeople(){return null;}
}

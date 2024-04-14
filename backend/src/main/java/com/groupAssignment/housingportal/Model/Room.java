package com.groupAssignment.housingportal.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @Column(name="roomId", length=45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    @Column(name="roomLabel", unique = true, length=50)
    private String roomLabel;

    @Column(name="block", length=5)
    private int block;

    @Column(name="side")
    private char side;

    @Column(name="roomNumber")
    private int roomNumber;

    @Column(name="vacancy")
    private int vacancy;

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name="sr_fk", referencedColumnName = "roomId")
    private List<Student> students;

}

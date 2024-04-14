package com.groupAssignment.housingportal.Repo;

import com.groupAssignment.housingportal.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface RoomRepo extends JpaRepository<Room,Integer> {
    Optional<Room> findByRoomLabel(String roomLabel);
}

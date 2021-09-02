package com.example.fptacademysystem.repository;

import org.springframework.stereotype.Repository;
import com.example.fptacademysystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}

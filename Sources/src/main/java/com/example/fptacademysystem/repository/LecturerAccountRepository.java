package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.Lecturer;
import com.example.fptacademysystem.model.LecturerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerAccountRepository extends JpaRepository<LecturerAccount, Integer> {

    @Query(value = "SELECT * FROM lecturer_account where lecturid = :lecturid", nativeQuery = true)
    LecturerAccount findAccount(@Param("lecturid")int lecturid);

    @Query(value = "SELECT * FROM lecturer_account WHERE rollnum = :rollnum AND pass = :pass AND removeat = 'No'", nativeQuery = true)
    LecturerAccount checkLogin(@Param("rollnum") String rollnum, @Param("pass") String pass);

    @Query("SELECT l FROM LecturerAccount l WHERE l.lecturid = :lecturer")
    LecturerAccount findPLecturerAccountByLecturerId(Lecturer lecturer);

}
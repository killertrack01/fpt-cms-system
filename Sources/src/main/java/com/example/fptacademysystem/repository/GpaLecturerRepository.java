package com.example.fptacademysystem.repository;

import java.util.List;

import com.example.fptacademysystem.model.GpaLecturer;
import com.example.fptacademysystem.model.RenderGpalecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Sort;

public interface GpaLecturerRepository extends JpaRepository<GpaLecturer, Integer> {
    @Query(value = "select * from gpa_lecturer where removeat = 'No'", nativeQuery = true)
    @Override
    public List<GpaLecturer> findAll();
    
    @Query(value = "select * from gpa_lecturer where MONTH(gpamonth) = ?1 and YEAR(gpamonth) = ?2 and lecturid = ?3", nativeQuery = true)
    public List<GpaLecturer> findGpaForOneLectruer(int month, int year, int lecturid);
    
    @Query(value = "SELECT r FROM RenderGpalecturer r WHERE r.gpayear = :gpayear AND r.lecturid = :lecturid")
    public List<RenderGpalecturer> findGpaYearForOneLectruer(@Param("gpayear")int gpayear, @Param("lecturid")int lecturid, Sort sort);

    @Query(value = "SELECT r FROM RenderGpalecturer r WHERE r.gpayear = :gpayear")
    public List<RenderGpalecturer> findAllGpaYearLecturer(@Param("gpayear")int gpayear, Sort sort);
}
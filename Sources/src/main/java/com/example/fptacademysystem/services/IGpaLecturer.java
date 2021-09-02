package com.example.fptacademysystem.services;

import com.example.fptacademysystem.dto.GpaLecturerDTO;
import com.example.fptacademysystem.model.GpaLecturer;
import com.example.fptacademysystem.model.RenderGpalecturer;
import java.util.List;

public interface IGpaLecturer {
    void create(GpaLecturer gpaLecturer);
    List<GpaLecturer> findAll();
    List<GpaLecturerDTO> findGpaForOneLecturer(GpaLecturerDTO dto);
    List<RenderGpalecturer> findGpaYearForOneLecturer(int gpayear, int lecturid);

    List<RenderGpalecturer> findAllGpaYearLecturer(int gpayear);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.services;

import java.util.List;

import com.example.fptacademysystem.model.BranchCampus;

/**
 *
 * @author kille
 */
public interface IBranchCampus {
    List<BranchCampus> Findall();

    void create(BranchCampus branchCampus);

    BranchCampus findOne(int id);

    void update(BranchCampus branchCampus);

    void delete(int id);
}

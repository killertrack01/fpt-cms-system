/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.fptacademysystem.services;

import java.util.List;

import com.example.fptacademysystem.model.BranchCampus;
import com.example.fptacademysystem.repository.BranchCampusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kille
 */
@Service
public class BranchCampusService implements IBranchCampus{
@Autowired 
BranchCampusRepository bcRepo;
    @Override
    public List<BranchCampus> Findall() {
        return bcRepo.findall();
    }

    @Override
    public void create(BranchCampus branchCampus) {
        bcRepo.save(branchCampus);
        
    }

    @Override
    public BranchCampus findOne(int id) {
        return bcRepo.findById(id).get();
    }

    @Override
    public void update(BranchCampus branchCampus) {
        bcRepo.save(branchCampus);
        
    }

    @Override
    public void delete(int id) {
        bcRepo.deleteid(id);
        
    }
    
}


package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.Branch;
import com.example.fptacademysystem.repository.BranchRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService implements IBranch {

    @Autowired
    BranchRepository branchRepository;

    @Override
    public List<Branch> Findall() {
        return branchRepository.findall();
    }

    @Override
    public void create(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public Branch findOne(int id) {
        return branchRepository.findById(id).get();
    }

    @Override
    public void update(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public void delete(int id) {
        branchRepository.deleteid(id);
    }

//    @Override
//    public List<Branch> getAllBranch() {
//        return branchRepository.getAllBranch();
//    }

}

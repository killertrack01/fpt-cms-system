
package com.example.fptacademysystem.services;

import com.example.fptacademysystem.model.Branch;
import java.util.List;

public interface IBranch {
    List<Branch> Findall();
    void create(Branch branch);
    Branch findOne(int id);
    void update(Branch branch);
    void delete(int id);
//    List<Branch> getAllBranch();
}


package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    @Query("SELECT b FROM Branch b WHERE b.removeat='No' AND b.branchid!=1")
    List<Branch> findall();

    @Query("SELECT b FROM Branch b WHERE b.removeat='No' AND b.branchid!=1 AND b.branchnm = :branchnm")
    Branch findBranchByName(@Param("branchnm")String branchnm);

    @Query("SELECT b FROM Branch b WHERE b.removeat='No' AND b.branchid!=1 AND b.branchid = :branchid")
    Branch findBranchById(@Param("branchid")int branchid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Branch b SET b.removeat='Yes' WHERE b.branchid= :id")
    void deleteid(@Param("id") int id);

//    public static final String GET_ALL_BRANCH = "SELECT br FROM Branch br WHERE br.removeat='No'";
//
//    @Query(value = GET_ALL_BRANCH)
//    List<Branch> getAllBranch();


}

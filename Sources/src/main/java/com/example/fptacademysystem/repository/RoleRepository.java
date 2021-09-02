package com.example.fptacademysystem.repository;

import com.example.fptacademysystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from role where removeat = 'No'", nativeQuery = true)
    List<Role> findAllRole();

    @Query(value = "select * from role where roleid = :roleid and removeat = 'No'", nativeQuery = true)
    Role findRole(@Param("roleid")int roleid);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update role set removeat = 'Yes' where roleid = :roleid", nativeQuery = true)
    void deleteRole(@Param("roleid")int roleid);
}

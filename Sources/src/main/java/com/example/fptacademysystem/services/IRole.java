package com.example.fptacademysystem.services;import com.example.fptacademysystem.model.Role;import java.util.List;public interface IRole {    List<Role> findAll();    Role findRole(int roleid);    void deleteRole(int roleid);    void createRole(Role role);    void updateRole(Role role);}
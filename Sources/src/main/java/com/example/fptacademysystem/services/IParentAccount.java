
package com.example.fptacademysystem.services;
import com.example.fptacademysystem.model.*;

public interface IParentAccount {
    ParentAccount findByParentId(Parent parent);
    void SaveParentAccount(ParentAccount account);
    void deleteByParentId(Parent pa);

    // check login parent account
    ParentAccount checkpaLogin(String rollnum,String pass);
}

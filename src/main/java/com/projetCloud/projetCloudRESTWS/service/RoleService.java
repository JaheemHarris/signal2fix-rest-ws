package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.model.Role;
import com.projetCloud.projetCloudRESTWS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List<Role> getRoles(){
        return roleRepo.findAll();
    }
}

package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.repository.StatusRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusService {

	@Autowired
	private StatusRepository statusRepo;

	public Status saveStatus(Status status){
		return statusRepo.save(status);
	}

	public Status getStatus(Long id){
		Optional<Status> optStatus = statusRepo.findById(id);
		Status status = null;
		if(optStatus.isPresent())
			status = optStatus.get();
		return status;
	}

	public List<Status> getAllStatus(){
		return statusRepo.findAll();
	}
}

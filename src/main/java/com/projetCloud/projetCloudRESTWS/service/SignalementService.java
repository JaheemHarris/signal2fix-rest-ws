package com.projetCloud.projetCloudRESTWS.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.Signalement;
import com.projetCloud.projetCloudRESTWS.repository.SignalementRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class SignalementService {

	@Autowired
	private SignalementRepository signalRepo;

	public List<Signalement> getAllSignalements(){
		return  signalRepo.findAll();
	}

	public Signalement saveSignalement(Signalement signalement){
		return signalRepo.save(signalement);
	}

	public Signalement getSignalement(Long id){
		Optional<Signalement> optSignal = signalRepo.findById(id);
		Signalement signalement = null;
		if(optSignal.isPresent())
			signalement = optSignal.get();
		return signalement;
	}

	public Signalement updateStatusSignalement(Long id){
		Signalement signalement = this.getSignalement(id);
		Signalement temp = signalement;
		if(signalement.getIdStatus()<3){
			signalement.setIdStatus(signalement.getIdStatus()+1);
		}
		Signalement updatedSignalement = signalRepo.save(signalement);
		if(updatedSignalement==null)
			return temp;
		return updatedSignalement;
	}
}

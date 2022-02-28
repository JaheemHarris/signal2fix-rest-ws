package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import com.projetCloud.projetCloudRESTWS.model.Signalement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.SignalementDetails;
import com.projetCloud.projetCloudRESTWS.repository.SignalementDetailsRepo;
import sun.misc.Signal;

@Service
public class SignalementDetailsService {

	@Autowired
	private SignalementDetailsRepo signalDetailsRepo;

	public List<SignalementDetails> getAllSignalDetails(Long idSignal,Long idUser,Long idRegion,Long idType,Long idStatus){
		return signalDetailsRepo.getSignalements(idSignal,idUser,idRegion,idType,idStatus);
	}

	public SignalementDetails getSignalementDetails(Long id){
		Optional<SignalementDetails>optSignalDetails =  signalDetailsRepo.findById(id);
		SignalementDetails signalDetails = optSignalDetails.get();
		return signalDetails;
	}
}
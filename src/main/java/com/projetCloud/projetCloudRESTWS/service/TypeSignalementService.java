package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.TypeSignalement;
import com.projetCloud.projetCloudRESTWS.repository.TypeSignalementRepo;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TypeSignalementService {

	@Autowired
	private TypeSignalementRepo typeRepo;

	public TypeSignalement saveTypeSignalement(TypeSignalement typeSignalement){
		return typeRepo.save(typeSignalement);
	}

	public TypeSignalement getTypeSignalement(Long id){
		Optional<TypeSignalement> optType = typeRepo.findById(id);
		TypeSignalement typeSignalement = null;
		if(optType.isPresent())
			typeSignalement = optType.get();
		return  typeSignalement;
	}

	public List<TypeSignalement> getAllTypeSignalements(){
		return typeRepo.findAll();
	}
}

package com.projetCloud.projetCloudRESTWS.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.projetCloud.projetCloudRESTWS.model.*;
import com.projetCloud.projetCloudRESTWS.service.ImageSignalementService;
import com.projetCloud.projetCloudRESTWS.service.UserService;
import com.projetCloud.projetCloudRESTWS.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.projetCloud.projetCloudRESTWS.service.SignalementService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1")
public class SignalementController {

	@Autowired
	private SignalementService signalService;

	@Autowired
	private ImageSignalementService imageService;

	@Autowired
	private UserService userService;

	@Autowired
	private FileStorageService fileService;

	@GetMapping
	public ResponseEntity<?> getAllSignalements(){
		return ResponseEntity.ok(signalService.getAllSignalements());
	}

	@GetMapping("/signalements/{id}")
	public ResponseEntity<?> getSignalement(@PathVariable("id") Long id){
		return ResponseEntity.ok(signalService.getSignalement(id));
	}

	@GetMapping("/signalements/image")
	public ResponseEntity<?> getSignalementImage(@RequestParam(name="fileName") String fileName){
		return ResponseEntity.ok(fileService.load(fileName));
	}

	@PostMapping("/signalements")
	public ResponseEntity<?> saveSignalement(@ModelAttribute Signalement signalement, @RequestParam(name="files",required = true) MultipartFile[] files, Authentication authentication){
		Optional<User> optUser = userService.getUserByMail(authentication.getName());
		if(optUser.isPresent()) {
			User currentUser = optUser.get();
			Long idUser = currentUser.getId();
			try{
				for(MultipartFile file : files){
					ImageSignalement imageSignalement = fileService.save(file);
					signalement.setIdUser(idUser);
					Signalement savedSignalement = signalService.saveSignalement(signalement);
					imageSignalement.setIdSignalement(savedSignalement.getId());
					imageService.saveImage(imageSignalement);
				}
			}catch (Exception e){
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
			}
		}else{
			return ResponseEntity.badRequest().body("Not user!");
		}
		return  ResponseEntity.ok().body(signalService.saveSignalement(signalement));
	}

	@PutMapping("/signalements/{id}")
	public ResponseEntity<?> traiterSignalement(@PathVariable("id") Long id){
		return ResponseEntity.ok(signalService.updateStatusSignalement(id));
	}
}

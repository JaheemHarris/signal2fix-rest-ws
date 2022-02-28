package com.projetCloud.projetCloudRESTWS.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.projetCloud.projetCloudRESTWS.model.Region;
import com.projetCloud.projetCloudRESTWS.model.Role;
import com.projetCloud.projetCloudRESTWS.model.User;
import com.projetCloud.projetCloudRESTWS.service.SignalementDetailsService;
import com.projetCloud.projetCloudRESTWS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SignalementDetailsController {

	@Autowired
	private SignalementDetailsService signalDetailsService;

	@Autowired
	private UserService userService;

	@GetMapping("/signalementdetails/{id}")
	public ResponseEntity<?> getAllSignalementsDetails(@PathVariable("id") Long id){
		return ResponseEntity.ok(signalDetailsService.getSignalementDetails(id));
	}

	@GetMapping("/signalementdetails")
	public ResponseEntity<?> getAllSignalementsDetails(@RequestParam(name="user",required = false) Long idUser, @RequestParam(name="region",required = false) Long idRegion, @RequestParam(name="type",required = false) Long idType, @RequestParam(name="status",required = false) Long idStatus, Principal principal){
		String userEmail = principal.getName();
		Optional<User> optUser = userService.getUserByMail(userEmail);
		if(optUser.isPresent()){
			User user = optUser.get();
			Role userRole = user.getRole();
			Region chiefRegion = user.getRegion();
			if(userRole.getNom().compareToIgnoreCase("USER")==0) {
				idUser = user.getId();
			}if(chiefRegion!=null){
				idRegion = chiefRegion.getId();
			}
		}
		return ResponseEntity.ok(signalDetailsService.getAllSignalDetails(null,idUser,idRegion,idType,idStatus));
	}
}

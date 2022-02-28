package com.projetCloud.projetCloudRESTWS.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetCloud.projetCloudRESTWS.model.TypeSignalement;
import com.projetCloud.projetCloudRESTWS.service.TypeSignalementService;

@RestController
@RequestMapping("/api/v1")
public class TypeSignalementController {

	@Autowired
	private TypeSignalementService typeService;

	@GetMapping("/typesignalements")
	public ResponseEntity<?> getAllTypes(){
		return ResponseEntity.ok().body(typeService.getAllTypeSignalements());
	}

	@GetMapping("/typesignalements/{id}")
	public ResponseEntity<?> getType(@PathVariable("id") Long id){
		return ResponseEntity.ok(typeService.getTypeSignalement(id));
	}

	@PostMapping("/typesignalements")
	public ResponseEntity<?> saveTypeSignalement(@RequestBody TypeSignalement typeSignalement){
		return ResponseEntity.ok(typeService.saveTypeSignalement(typeSignalement));
	}
}

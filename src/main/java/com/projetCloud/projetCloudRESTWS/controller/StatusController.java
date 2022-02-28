package com.projetCloud.projetCloudRESTWS.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.service.StatusService;

@RestController
@RequestMapping("/api/v1")
public class StatusController {

	@Autowired
	private StatusService statusService;

	@GetMapping("/status")
	public ResponseEntity<?> getAllStatus(){
		return ResponseEntity.ok(statusService.getAllStatus());
	}

	@GetMapping("/status/{id}")
	public ResponseEntity<?> getStatus(@PathVariable("id") Long id){
		return ResponseEntity.ok(statusService.getStatus(id));
	}

	@PostMapping("/status")
	public ResponseEntity<?> saveStatus(@RequestBody Status status){
		return ResponseEntity.ok(status);
	}
}

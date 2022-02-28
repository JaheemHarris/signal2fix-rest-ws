package com.projetCloud.projetCloudRESTWS.controller;

import java.util.List;

import com.projetCloud.projetCloudRESTWS.service.SignalementDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.projetCloud.projetCloudRESTWS.model.Region;
import com.projetCloud.projetCloudRESTWS.service.RegionService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@Autowired
	private SignalementDetailsService signalDetailsService;

	@GetMapping("/regions")
	public ResponseEntity<?> getAllRegions(){
		List<Region> allRegions = regionService.getRegions();
		return ResponseEntity.ok(allRegions);
	}

	@GetMapping("/regions/{id}")
	public ResponseEntity<?> getRegion(@PathVariable("id") Long id){
		return ResponseEntity.ok(regionService.getRegion(id));
	}

	@PostMapping("/regions")
	public ResponseEntity<?> saveRegion(@RequestBody Region region){
		return ResponseEntity.ok().body(region);
	}

	@GetMapping("/regions/{id}/signalementdetails")
	public ResponseEntity<?> getRegionAllSignalement(@PathVariable("id") Long id,@RequestParam(name="type",required = false) Long idType,@RequestParam(name="status",required = false) Long idStatus){
		return ResponseEntity.ok().body(signalDetailsService.getAllSignalDetails(null,null,id,idType,idStatus));
	}

	@GetMapping("/regions/{id}/signalementdetails/{idsignal}")
	public ResponseEntity<?> getRegionAllSignalement(@PathVariable("id") Long id,@PathVariable("idsignal") Long idSignal){
		return ResponseEntity.ok().body(signalDetailsService.getAllSignalDetails(idSignal,null,id,null,null));
	}
}

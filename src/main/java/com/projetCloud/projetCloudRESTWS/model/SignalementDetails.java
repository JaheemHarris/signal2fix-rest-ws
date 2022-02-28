package com.projetCloud.projetCloudRESTWS.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Immutable
@Entity
@Table(name="view_signalementdetails")
public class SignalementDetails {
	
	@Id
	@Column(name="idsignalement")
	private Long idSignalement;
	
	@Column(name="idtype")
	private Long idType;

	private String type;

	private String couleur;
	
	@Column(name="idregion")
	private Long idRegion;

	private String region;
	
	@Column(name="iduser")
	private Long idUser;
	
	private String nom;
	
	private String prenom;
	
	@Column(name="idstatus")
	private Long idStatus;
	
	private String status;

	private String description;

	@Temporal(TemporalType.DATE
	)
	@Column(name="datesignalement")
	private Date dateSignalement;
	
	private Double latitude;

	private Double longitude;
}

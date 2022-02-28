package com.projetCloud.projetCloudRESTWS.model;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="signalement")
public class Signalement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="idtype")
	private Long idType;
	
	@Column(name="idregion")
	private Long idRegion;
	
	@Column(name="iduser")
	private Long idUser;
	
	@Column(name="idstatus")
	private Long idStatus;

	@NotNull
	@NotEmpty
	private String description;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="datesignalement")
	private Date dateSignalement;

	@Column(name="heuresignalement")
	private LocalTime heureSignalement;
	
	private Double latitude;

	private Double longitude;

	@PrePersist
	public void onCreate() {
		heureSignalement = LocalTime.now();
	}
}
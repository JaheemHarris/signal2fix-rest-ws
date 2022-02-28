package com.projetCloud.projetCloudRESTWS.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="imagesignalement")
public class ImageSignalement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="idsignalement")
    private Long idSignalement;

    @Column(name="imageurl")
    private String imageUrl;

    @Column(name="imagename")
    private String imageName;

    @Column(name="imagetype")
    private String imageType;
}

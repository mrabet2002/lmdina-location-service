package com.mdina.location.dao.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends GlobalEntity {
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    @ManyToMany(mappedBy = "categories")
    private List<TouristSite> touristSites;
}

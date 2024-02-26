package com.mdina.location.dao.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class TouristSite extends GlobalEntity {
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne
    private Location location;
    @ManyToMany
    private List<Calendar> calendars;
    @OneToMany
    private List<File> attachments;
    @ManyToMany
    private List<Category> categories;
}

package com.mdina.location.dao.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Culture extends GlobalEntity {
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany
    private List<File> attachments;
}

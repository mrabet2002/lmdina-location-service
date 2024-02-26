package com.mdina.location.dao.entities;


import com.mdina.location.enumerations.CalendarSeason;
import com.mdina.location.enumerations.Day;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Calendar extends GlobalEntity {
    @Enumerated(EnumType.STRING)
    private Day day;
    private LocalTime startAt;
    private LocalTime endAt;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private CalendarSeason season;
    @ManyToMany(mappedBy = "calendars")
    private List<TouristSite> touristSites;
}

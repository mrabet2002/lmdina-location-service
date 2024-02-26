package com.mdina.location.service.dto;

import com.mdina.location.dao.entities.Calendar;
import com.mdina.location.dao.entities.Category;
import com.mdina.location.dao.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTouristSite {
    private String name;
    private String description;
    private Location location;
    private List<Calendar> calendars;
    private List<MultipartFile> attachments;
    private List<Category> categories;
}

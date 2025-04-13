package app.controller;

import app.model.Location;
import app.repository.LocationRepository;
import app.repository.entity.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepo;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepo.findAll().stream().map(LocationController::locationFromEntity).toList();
    }

    public static Location locationFromEntity(LocationEntity locationEntity) {
        Location location = new Location();
        location.setId(locationEntity.getId());
        location.setName(locationEntity.getName());
        return location;
    }

}

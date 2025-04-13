package app.service;

import app.repository.LocationRepository;
import app.repository.entity.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LocationCache {

    @Autowired
    private LocationRepository locationRepo;

    @Cacheable("locations")
    public LocationEntity getLocation(Long id) {
        return locationRepo.findById(id).orElse(null);
    }

}

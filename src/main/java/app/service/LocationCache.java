package app.service;

import app.repository.LocationRepository;
import app.repository.entity.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationCache {

    private final LocationRepository locationRepo;

    @Autowired
    public LocationCache(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Cacheable("locationsByName")
    public Optional<LocationEntity> getLocationByName(String location) {
        return locationRepo.findByName(location);
    }

    @Cacheable("locationsById")
    public Optional<LocationEntity> getLocationById(Long id) {
        return locationRepo.findById(id);
    }

}

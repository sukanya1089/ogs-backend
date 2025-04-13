package app.controller;

import app.model.Location;
import app.model.Sample;
import app.repository.SampleRepository;
import app.repository.entity.LocationEntity;
import app.repository.entity.SampleEntity;
import app.service.LocationCache;
import app.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/samples")
public class SampleController {

    @Autowired
    private SampleRepository sampleRepo;

    @Autowired
    private LocationCache locationCache;

    @Autowired
    private SampleService sampleService;

    @GetMapping
    public List<Sample> getAllSamples() {
        return sampleRepo.findAll().stream().map(this::sampleFromSampleEntity).toList();
    }

    @PostMapping
    public Sample addSample(@RequestBody Sample sample) {
        LocationEntity location = Optional.ofNullable(sample.getLocation())
                .map(Location::getId)
                .map(locationCache::getLocation)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found"));
        if (sample.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id should not be provided");
        }
        SampleEntity sampleEntity = sampleRepo.save(sampleEntityFromSample(sample));
        return sampleFromSampleEntity(sampleEntity);
    }

    @PutMapping("/{id}")
    public Sample updateSample(@PathVariable Long id, @RequestBody Sample updated) {
        updated.setId(id);
        SampleEntity sampleEntity = sampleRepo.findById(id).orElse(null);
        if (sampleEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found");
        }
        sampleEntity.setDateCollected(updated.getDateCollected());
        sampleEntity.setDepth(updated.getDepth());
        sampleEntity.setWaterContent(updated.getWaterContent());
        sampleEntity.setShearStrength(updated.getShearStrength());
        sampleEntity.setUnitWeight(updated.getUnitWeight());
        return sampleFromSampleEntity(sampleRepo.save(sampleEntity));
    }

    @DeleteMapping("/{id}")
    public void deleteSample(@PathVariable Long id) {
        sampleRepo.deleteById(id);
    }

    @GetMapping("/statistics")
    public Map<String, Object> getStatistics() {
        List<SampleEntity> allSamples = sampleRepo.findAll();
        double avgWater = sampleService.calculateAverageWaterContent(allSamples);
        List<SampleEntity> exceedingEntities = sampleService.getExceedingSamples(allSamples);
        List<Sample> exceeding = exceedingEntities.stream().map(this::sampleFromSampleEntity).toList();
        return Map.of(
                "averageWaterContent", avgWater,
                "samplesExceedingThreshold", exceeding.size()
        );
    }

    public Sample sampleFromSampleEntity(SampleEntity sampleEntity) {
        Sample sample = new Sample();
        sample.setLocation(LocationController.locationFromEntity(sampleEntity.getLocation()));
        sample.setId(sampleEntity.getId());
        sample.setDateCollected(sampleEntity.getDateCollected());
        sample.setDepth(sampleEntity.getDepth());
        sample.setWaterContent(sampleEntity.getWaterContent());
        sample.setShearStrength(sampleEntity.getShearStrength());
        sample.setUnitWeight(sampleEntity.getUnitWeight());
        return sample;
    }

    public SampleEntity sampleEntityFromSample(Sample sample) {
        LocationEntity locationEntity = locationCache.getLocation(sample.getLocation().getId());
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setLocation(locationEntity);
        sampleEntity.setId(sample.getId());
        sampleEntity.setDateCollected(sample.getDateCollected());
        sampleEntity.setDepth(sample.getDepth());
        sampleEntity.setWaterContent(sample.getWaterContent());
        sampleEntity.setShearStrength(sample.getShearStrength());
        sampleEntity.setUnitWeight(sample.getUnitWeight());
        return sampleEntity;
    }
}

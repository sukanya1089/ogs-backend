package app.service;

import app.repository.entity.SampleEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    @Value("${threshold.unitWeight}")
    private double thresholdUnitWeight;

    @Value("${threshold.waterContent}")
    private double thresholdWaterContent;

    @Value("${threshold.shearStrength}")
    private double thresholdShearStrength;

    public double calculateAverageWaterContent(List<SampleEntity> samples) {
        return samples.stream()
                .mapToDouble(SampleEntity::getWaterContent)
                .average()
                .orElse(0);
    }

    public List<SampleEntity> getExceedingSamples(List<SampleEntity> samples) {
        return samples.stream().filter(s ->
                s.getUnitWeight() > thresholdUnitWeight ||
                        s.getWaterContent() > thresholdWaterContent ||
                        s.getShearStrength() > thresholdShearStrength
        ).toList();
    }
}

package app.service;

import app.model.Statistics;
import app.repository.entity.SampleEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    @Value("${threshold.unitWeight}")
    double thresholdUnitWeight;

    @Value("${threshold.waterContent}")
    double thresholdWaterContent;

    @Value("${threshold.shearStrength}")
    double thresholdShearStrength;

    public double calculateAverageWaterContent(List<SampleEntity> samples) {
        return samples.stream()
                .mapToDouble(SampleEntity::getWaterContent)
                .average()
                .orElse(0);
    }

    public long calculateSamplesAboveUnitWeightThreshold(List<SampleEntity> samples) {
        return samples.stream().filter(s -> s.getUnitWeight() > thresholdUnitWeight).count();
    }

    public long calculateSamplesAboveWaterContentThreshold(List<SampleEntity> samples) {
        return samples.stream().filter(s -> s.getWaterContent() > thresholdWaterContent).count();
    }

    public long calculateSamplesAboveShearStrengthThreshold(List<SampleEntity> samples) {
        return samples.stream().filter(s -> s.getShearStrength() > thresholdShearStrength).count();
    }

    public Statistics getStatistics(List<SampleEntity> samples) {
        Statistics statistics = new Statistics();
        statistics.setAverageWaterContent(calculateAverageWaterContent(samples));
        statistics.setSamplesAboveUnitWeightThreshold(calculateSamplesAboveUnitWeightThreshold(samples));
        statistics.setSamplesAboveShearStrengthThreshold(calculateSamplesAboveShearStrengthThreshold(samples));
        statistics.setSamplesAboveWaterContentThreshold(calculateSamplesAboveWaterContentThreshold(samples));
        return statistics;
    }
}

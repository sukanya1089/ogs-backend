package app.service;

import app.model.Statistics;
import app.repository.entity.SampleEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SampleServiceTest {


    @Test
    void calculateAverageWaterContent() {
        SampleService sampleService = new SampleService();
        assertThat(sampleService.calculateAverageWaterContent(List.of())).isEqualTo(0);
        SampleEntity sampleEntity1 = new SampleEntity();
        sampleEntity1.setWaterContent(100);
        assertThat(sampleService.calculateAverageWaterContent(List.of(sampleEntity1))).isEqualTo(100);
        SampleEntity sampleEntity2 = new SampleEntity();
        sampleEntity2.setWaterContent(200);
        assertThat(sampleService.calculateAverageWaterContent(List.of(sampleEntity1, sampleEntity2))).isEqualTo(150);
    }

    @Test
    void calculateSamplesAboveUnitWeightThreshold() {
        SampleService sampleService = new SampleService();
        sampleService.thresholdUnitWeight = 25;
        assertThat(sampleService.calculateSamplesAboveUnitWeightThreshold(List.of())).isEqualTo(0);
        SampleEntity sampleEntity1 = new SampleEntity();
        sampleEntity1.setUnitWeight(20);
        assertThat(sampleService.calculateSamplesAboveUnitWeightThreshold(List.of(sampleEntity1))).isEqualTo(0);
        SampleEntity sampleEntity2 = new SampleEntity();
        sampleEntity2.setUnitWeight(30);
        assertThat(sampleService.calculateSamplesAboveUnitWeightThreshold(List.of(sampleEntity1, sampleEntity2))).isEqualTo(1);
    }

    @Test
    void calculateSamplesAboveWaterContentThreshold() {
        SampleService sampleService = new SampleService();
        sampleService.thresholdWaterContent = 100;
        assertThat(sampleService.calculateSamplesAboveWaterContentThreshold(List.of())).isEqualTo(0);
        SampleEntity sampleEntity1 = new SampleEntity();
        sampleEntity1.setWaterContent(50);
        assertThat(sampleService.calculateSamplesAboveWaterContentThreshold(List.of(sampleEntity1))).isEqualTo(0);
        SampleEntity sampleEntity2 = new SampleEntity();
        sampleEntity2.setWaterContent(200);
        assertThat(sampleService.calculateSamplesAboveWaterContentThreshold(List.of(sampleEntity1, sampleEntity2))).isEqualTo(1);
    }

    @Test
    void calculateSamplesAboveShearStrengthThreshold() {
        SampleService sampleService = new SampleService();
        sampleService.thresholdShearStrength = 800;
        assertThat(sampleService.calculateSamplesAboveShearStrengthThreshold(List.of())).isEqualTo(0);
        SampleEntity sampleEntity1 = new SampleEntity();
        sampleEntity1.setShearStrength(500);
        assertThat(sampleService.calculateSamplesAboveShearStrengthThreshold(List.of(sampleEntity1))).isEqualTo(0);
        SampleEntity sampleEntity2 = new SampleEntity();
        sampleEntity2.setShearStrength(1000);
        assertThat(sampleService.calculateSamplesAboveShearStrengthThreshold(List.of(sampleEntity1, sampleEntity2))).isEqualTo(1);
    }

    @Test
    void getStatistics() {
        SampleService sampleService = new SampleService();
        sampleService.thresholdUnitWeight = 25;
        sampleService.thresholdWaterContent = 100;
        sampleService.thresholdShearStrength = 800;
        SampleEntity sampleEntity1 = new SampleEntity();
        sampleEntity1.setUnitWeight(20);
        sampleEntity1.setWaterContent(50);
        sampleEntity1.setShearStrength(500);
        SampleEntity sampleEntity2 = new SampleEntity();
        sampleEntity2.setUnitWeight(30);
        sampleEntity2.setWaterContent(200);
        sampleEntity2.setShearStrength(1000);
        Statistics statistics = sampleService.getStatistics(List.of(sampleEntity1, sampleEntity2));
        assertThat(statistics.getAverageWaterContent()).isEqualTo(125);
        assertThat(statistics.getSamplesAboveUnitWeightThreshold()).isEqualTo(1);
        assertThat(statistics.getSamplesAboveWaterContentThreshold()).isEqualTo(1);
        assertThat(statistics.getSamplesAboveShearStrengthThreshold()).isEqualTo(1);
    }
}
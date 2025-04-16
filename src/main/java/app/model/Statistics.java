package app.model;



import java.util.StringJoiner;

public class Statistics {

    private double averageWaterContent;
    private long samplesAboveUnitWeightThreshold;
    private long samplesAboveWaterContentThreshold;
    private long samplesAboveShearStrengthThreshold;

    public Statistics() {}

    public double getAverageWaterContent() {
        return averageWaterContent;
    }

    public void setAverageWaterContent(double averageWaterContent) {
        this.averageWaterContent = averageWaterContent;
    }

    public long getSamplesAboveUnitWeightThreshold() {
        return samplesAboveUnitWeightThreshold;
    }

    public void setSamplesAboveUnitWeightThreshold(long samplesAboveUnitWeightThreshold) {
        this.samplesAboveUnitWeightThreshold = samplesAboveUnitWeightThreshold;
    }

    public long getSamplesAboveWaterContentThreshold() {
        return samplesAboveWaterContentThreshold;
    }

    public void setSamplesAboveWaterContentThreshold(long samplesAboveWaterContentThreshold) {
        this.samplesAboveWaterContentThreshold = samplesAboveWaterContentThreshold;
    }

    public long getSamplesAboveShearStrengthThreshold() {
        return samplesAboveShearStrengthThreshold;
    }

    public void setSamplesAboveShearStrengthThreshold(long samplesAboveShearStrengthThreshold) {
        this.samplesAboveShearStrengthThreshold = samplesAboveShearStrengthThreshold;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Statistics.class.getSimpleName() + "[", "]")
                .add("averageWaterContent=" + averageWaterContent)
                .add("samplesAboveUnitWeightThreshold=" + samplesAboveUnitWeightThreshold)
                .add("samplesAboveWaterContentThreshold=" + samplesAboveWaterContentThreshold)
                .add("samplesAboveShearStrengthThreshold=" + samplesAboveShearStrengthThreshold)
                .toString();
    }
}

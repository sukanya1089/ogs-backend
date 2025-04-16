package app.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "sample")
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long locationId;

    private double depth;
    private LocalDate dateCollected;
    private double unitWeight;     // in kN/m3
    private double waterContent;   // in %
    private double shearStrength;  // in kPa

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public LocalDate getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(LocalDate dateCollected) {
        this.dateCollected = dateCollected;
    }

    public double getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(double unitWeight) {
        this.unitWeight = unitWeight;
    }

    public double getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(double waterContent) {
        this.waterContent = waterContent;
    }

    public double getShearStrength() {
        return shearStrength;
    }

    public void setShearStrength(double shearStrength) {
        this.shearStrength = shearStrength;
    }
}

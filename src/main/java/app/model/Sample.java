package app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(nullable=false, updatable=false)
    private Location location;

    private LocalDate dateCollected;
    private double depth;
    private double unitWeight;     // in kN/m3
    private double waterContent;   // in %
    private double shearStrength;  // in kPa

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

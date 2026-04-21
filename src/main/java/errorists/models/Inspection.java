package errorists.models;

import java.time.LocalDate;

public class Inspection {
    private String inspectorName;
    private LocalDate inspectionDate;
    private Shipment shipment;
    private InspectionResult inspectionResult;
    private Warehouse warehouse;

    //Constructors
    public Inspection(String inspectorName, LocalDate inspectionDate, InspectionResult inspectionResult) {
        this.inspectorName = inspectorName;
        this.inspectionDate = inspectionDate;
        this.inspectionResult = inspectionResult;
    }

    //getters and setters
    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    @Deprecated
    public void setInpsectorName(String inspectorName) {
        setInspectorName(inspectorName);
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public InspectionResult getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public Warehouse getInspectionLocation() {
        // Prefer explicitly stored inspection location; fallback to shipment location for legacy data.
        return warehouse != null ? warehouse : (shipment != null ? shipment.getCurrentWarehouse() : null);
    }


    public void setInspectionLocation(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    
}

package errorists.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;



public class MainViewController {
    private static final Logger LOG = Logger.getLogger(MainViewController.class.getName());
    private AppController appController;

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    public void handleButtonDashboardAction(ActionEvent event){
        LOG.fine("Dashboard button clicked");
        try {
            appController.loadView("Dashboard");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed to load Dashboard view", e);
        }
    }

    @FXML
    public void handleButtonWarehousesViewAction(ActionEvent event){
        LOG.fine("Warehouse button clicked");
        try {
            appController.loadView("WarehousesView");
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed to load Warehouses view", e);
        }
    }

    @FXML
    public void handleButtonShipmentAction(ActionEvent event){
            LOG.fine("Shipment button clicked");
            try {
                appController.loadView("ShipmentsView");
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Failed to load Shipments view", e);
            }
        }

    @FXML
    public void handleButtonInspectionAction(ActionEvent event){
            LOG.fine("Inspection button clicked");
            try {
                appController.loadView("InspectionsView");
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Failed to load Inspections view", e);
            }
        }

    @FXML
    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    
}

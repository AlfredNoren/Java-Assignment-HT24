package errorists.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import errorists.models.AppModel;
import errorists.models.Shipment;
import errorists.models.Warehouse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppController {
    private static final Logger LOG = Logger.getLogger(AppController.class.getName());
    private final Stage primaryStage;
    private MainViewController mainViewController;
    private final AppModel appModel;


    //Constructor
    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.appModel = new AppModel();
    }

    
    public void showMainView() throws IOException{
        LOG.info("Main view shown");
        URL url = getClass().getResource("/fxml/MainView.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());

        mainViewController = loader.getController();
        mainViewController.setAppController(this);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Viking Express");
        primaryStage.show();
        loadView("Dashboard");

    }

    public void loadView(String view) throws IOException{
        URL url = getClass().getResource("/fxml/"+ view +".fxml");
        LOG.fine("Loading FXML: " + url);

        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        // Get the controller from the FXML file
        Object controller = loader.getController();
    
        if (controller instanceof WarehousesViewController) {
            LOG.fine("Controller is WarehousesViewController");
            WarehousesViewController warehousesViewController = (WarehousesViewController) controller;
            warehousesViewController.setAppController(this);  
            warehousesViewController.setAppModel(appModel);   
            
        } else if (controller instanceof ShipmentsViewController) {
            LOG.fine("Controller is ShipmentsViewController");
            ShipmentsViewController shipmentsViewController = (ShipmentsViewController) controller;
            shipmentsViewController.setAppController(this);  // Set AppController if needed
            shipmentsViewController.setAppModel(appModel);   // Set AppModel for data access
        } else if (controller instanceof InspectionsViewController) {
            LOG.fine("Controller is InspectionsViewController");
            InspectionsViewController inspectionsViewController = (InspectionsViewController) controller;
            inspectionsViewController.setAppController(this);  // Set AppController if needed
            inspectionsViewController.setAppModel(appModel);   // Set AppModel for data access
            
        } else if (controller instanceof DashboardController) {
            LOG.fine("Controller is DashboardController");
            DashboardController dashboardController = (DashboardController) controller;
            dashboardController.setAppController(this);  // Set AppController if needed
            dashboardController.setAppModel(appModel);   // Set AppModel for data access
        } else if (controller instanceof ShipmentAddViewController) {
            LOG.fine("Controller is ShipmentAddViewController");
            ShipmentAddViewController shipmentAddViewController = (ShipmentAddViewController) controller;
            shipmentAddViewController.setAppController(this);  // Set AppController if needed
            shipmentAddViewController.setAppModel(appModel);   // Set AppModel for data access
        } else if (controller instanceof WarehouseAddViewController) {
            WarehouseAddViewController warehouseAddViewController = (WarehouseAddViewController) controller;
            warehouseAddViewController.setAppController(this);  // Set AppController if needed
            warehouseAddViewController.setAppModel(appModel);   // Set AppModel for data access
        } else if (controller instanceof InspectionAddViewController) {
            InspectionAddViewController inspectionAddViewController = (InspectionAddViewController) controller;
            inspectionAddViewController.setAppController(this);  // Set AppController if needed
            inspectionAddViewController.setAppModel(appModel);   // Set AppModel for data access

        }


        mainViewController.getMainScrollPane().setContent(root);
    }

    
    public void loadWarehouseInfoView(Warehouse selectedWarehouse) throws IOException {
    URL url = getClass().getResource("/fxml/WarehouseInfoView.fxml");
    LOG.fine("Loading WarehouseInfoView FXML: " + url);

    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    // Get the controller for the WarehouseInfoView
    WarehouseInfoViewController controller = loader.getController();
    
    // Pass the selected warehouse data to the controller
    controller.setAppController(this);  // Pass AppController if needed
    controller.setWarehouseInfo(selectedWarehouse);

    // Set the content of the Main Scroll Pane to the new view
    mainViewController.getMainScrollPane().setContent(root);
}

    public void showMoveShipmentDialog(Shipment selectedShipment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShipmentMoveDialog.fxml"));
        Parent root = loader.load();

        ShipmentMoveDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setShipment(selectedShipment);
        controller.setLabelShipmentName(selectedShipment);

        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(root));
        modalStage.setTitle("Move Shipment");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }

    public void showShipmentLogAddDialog(Shipment selectedShipment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShipmentLogAddDialog.fxml"));
        Parent root = loader.load();

        ShipmentLogAddDialogController controller = loader.getController();
        controller.setAppModel(appModel);
        controller.setShipment(selectedShipment);

        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(root));
        modalStage.setTitle("Add Shipment Log");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(primaryStage);
        modalStage.showAndWait();
    }

    public AppModel getAppModel() {
        return this.appModel;
    }
    


}

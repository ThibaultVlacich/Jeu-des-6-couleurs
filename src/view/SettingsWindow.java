/**
 * Jeu des 6 couleurs
 *
 * @package view
 * @class   SettingsWindow
 * @desc    Vue de l'écran des options
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package view;

import settings.Settings;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsWindow implements Initializable {
  @FXML
  private ChoiceBox<Object> gridTypeChoiceBox;
  
  @FXML
  private Slider gridSizeSlider;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    gridTypeChoiceBox.setItems(
        FXCollections.observableArrayList(
            "square",
            "diamond",
            "rectangle"
        )
    );
    
    gridTypeChoiceBox.setValue(Settings.get("gridType"));
    
    gridSizeSlider.setValue((long) Settings.get("gridSize"));
  }
  
  @FXML
  protected void saveSettings(ActionEvent event) {
    // Type de la grille
    String gridType = (String) gridTypeChoiceBox.getValue();
    
    Settings.set("gridType", gridType);
    
    // Taille de la grille
    long gridSize = (long) gridSizeSlider.getValue();
    
    Settings.set("gridSize", gridSize);
    
    // Sauvegarde dans le fichier
    Settings.save();
    
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Jeu des 6 couleurs");
    alert.setHeaderText(null);
    alert.setContentText("Les options ont bien été sauvegardées !");

    alert.showAndWait();
    
    Stage stage = (Stage) gridSizeSlider.getScene().getWindow();
    stage.close();
  }
  
  @FXML
  protected void closeSettings(ActionEvent event) {
    Stage stage = (Stage) gridSizeSlider.getScene().getWindow();
    stage.close();
  }

}

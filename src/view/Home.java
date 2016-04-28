package view;

import java.io.File;

import java.util.Optional;

import game.Game;

import javafx.application.*;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Home extends Application {
  static Stage mainStage;
  
  @Override
  public void start(Stage stage) throws Exception {
    try {
      GridPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
      
      root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      
      // Création de la scène
      Scene scene = new Scene(root, 320, 380);
      
      stage.setTitle("Jeu des 6 couleurs");
      stage.setScene(scene);
      stage.show();
      
      mainStage = stage;
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  @FXML
  protected void launchGame(ActionEvent event) {
    // On masque l'écran d'accueil
    mainStage.hide();
    
    try {
      GameWindow gameWindow = new GameWindow(new Game());
      
      Scene scene = gameWindow.getScene();
      
      Stage stage = new Stage();
      
      stage.setTitle("Jeu des 6 couleurs");
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @FXML
  protected void loadGame(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    
    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("Fichier texte", "*.txt")
    );
    
    File file = fileChooser.showOpenDialog(mainStage);
    
    if (file != null) {
      
    }
  }
  
  @FXML
  protected void quitApp(ActionEvent event) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    
    alert.setTitle("Jeu des 6 couleurs");
    alert.setHeaderText("Confirmation");
    alert.setContentText("Voulez-vous vraiment quitter le jeu des 6 couleurs ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        System.exit(0);
    }
  }
  
  @FXML
  protected void openGitHub(ActionEvent event) {
    getHostServices().showDocument("https://github.com/ThibaultVlacich/Jeu-des-6-couleurs");
  }
}

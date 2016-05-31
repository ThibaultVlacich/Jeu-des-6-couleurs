package edu.isep.jeudes6couleurs.view;

import java.io.File;

import java.util.Optional;

import edu.isep.jeudes6couleurs.file.Load;

import edu.isep.jeudes6couleurs.game.Game;

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

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.view
 * @class   Home
 * @desc    Défini la fenêtre de l'écran d'accueil
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class Home extends Application {
  static Stage mainStage;
  
  @Override
  public void start(Stage stage) throws Exception {
    try {
      // Chargement de l'interface FXML
      GridPane root = FXMLLoader.load(getClass().getResource("Home.fxml"));
      
      root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      
      // Création de la scène
      Scene scene = new Scene(root);
      
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
    
    new NewGameWindow();
  }
  
  @FXML
  protected void loadGame(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    
    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("Fichier texte", "*.txt")
    );
    
    File file = fileChooser.showOpenDialog(mainStage);
    
    if (file != null) {
      Game game = Load.loadSave(file);
      
      if (game == null) {
        Alert alert = new Alert(AlertType.ERROR);
        
        alert.setTitle("Jeu des 6 couleurs");
        alert.setHeaderText("Erreur");
        alert.setContentText("Le fichier choisi n'est pas un fichier de sauvegarde valide.");

        alert.showAndWait();
        
        return;
      }
      
      mainStage.hide();
      
      new GameWindow(game);
    }
  }
  
  @FXML
  protected void showAbout(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
      
      GridPane aboutRoot = loader.load();
      
      About aboutController = loader.getController();
      
      aboutController.setHostServices(getHostServices());
      
      aboutRoot.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
      
      Stage dialog = new Stage();
      dialog.setTitle("Jeu des 6 couleurs - A propos");
      dialog.setScene(new Scene(aboutRoot));
      dialog.show();
    } catch (Exception e) {
      e.printStackTrace();
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

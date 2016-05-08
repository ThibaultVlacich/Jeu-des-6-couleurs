/**
 * Jeu des 6 couleurs
 *
 * @package view
 * @class   NewGameWindow
 * @desc    Défini la fenêtre permettant de lancer une nouvelle partie
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewGameWindow {
  // Fenêtre
  Stage stage = new Stage();
  
  // Vue racine de la fenêtre
  private GridPane root = new GridPane();

  // Scène javaFX racine de la fenêtre
  private Scene scene;
  
  // Index de ligne actuel dans la grille root
  int currentIndex = 0;
  
  private GridPane playersList = new GridPane();
  
  NewGameWindow() { 
    root.setPadding(new Insets(10));
    
    drawHeading();
    
    drawGameSettings();
    
    drawPlayersSettings();
    
    drawButtons();
    
    // Ajout de la grille à la scène
    scene = new Scene(root);
    
    // Chargement des fichiers CSS
    scene.getStylesheets().add(getClass().getResource("css/game.css").toExternalForm());
    
    // Affichage de la fenêtre
    stage.setTitle("Jeu des 6 couleurs");
    stage.setScene(scene);
    stage.show();
  }
  
  private void drawHeading() {
    Label newGameLabel = new Label("Nouvelle partie");
    root.add(newGameLabel, 0, currentIndex++);
    
    Separator newGameSeparator = new Separator();
    root.add(newGameSeparator, 0, currentIndex++);
    
    GridPane.setMargin(newGameSeparator, new Insets(5, 0, 5, 0));
  }
  
  private void drawGameSettings() {
    GridPane gameSettingsGrid = new GridPane();
    
    // Contraintes de colonnes
    ColumnConstraints column1 = new ColumnConstraints(150);
    ColumnConstraints column2 = new ColumnConstraints(200);

    gameSettingsGrid.getColumnConstraints().addAll(column1, column2);
    
    // Type de la grille
    Label gridTypeLabel = new Label("Type de grille :");
    gameSettingsGrid.add(gridTypeLabel, 0, 0);
    
    ChoiceBox<String> gridTypeChoiceBox = new ChoiceBox<String>();
    gridTypeChoiceBox.setPrefWidth(200);
    
    gridTypeChoiceBox.setItems(
        FXCollections.observableArrayList(
            "Carré"
        )
    );
    
    GridPane.setMargin(gridTypeChoiceBox, new Insets(5, 0, 5, 0));
    
    gameSettingsGrid.add(gridTypeChoiceBox, 1, 0);
    
    // Taille de la grille
    Label gridSizeLabel = new Label("Taille de la grille :");
    gameSettingsGrid.add(gridSizeLabel, 0, 1);
    
    Slider gridSizeSlider = new Slider();
    
    gridSizeSlider.setMin(13.0);
    gridSizeSlider.setMax(25.0);
    
    gridSizeSlider.setBlockIncrement(2.0);
    gridSizeSlider.setMinorTickCount(0);
    gridSizeSlider.setMajorTickUnit(2.0);
    
    gridSizeSlider.setShowTickLabels(true);
    gridSizeSlider.setShowTickMarks(true);
    
    gridSizeSlider.setSnapToTicks(true);
    
    GridPane.setMargin(gridSizeSlider, new Insets(5, 0, 5, 0));
    
    gameSettingsGrid.add(gridSizeSlider, 1, 1);
    
    root.add(gameSettingsGrid, 0, currentIndex++);
    
    Separator gameSettingsSeparator = new Separator();
    root.add(gameSettingsSeparator, 0, currentIndex++);
    
    GridPane.setMargin(gameSettingsSeparator, new Insets(5, 0, 5, 0));
  }
  
  private void drawPlayersSettings() {
    GridPane playersSettingsGrid = new GridPane();
    
    ColumnConstraints column1 = new ColumnConstraints(150);
    ColumnConstraints column2 = new ColumnConstraints(200);
    
    playersSettingsGrid.getColumnConstraints().setAll(column1, column2);
    
    // Nombre de joueurs
    Label nbOfPlayersLabel = new Label("Nombre de joueurs :");
    playersSettingsGrid.add(nbOfPlayersLabel, 0, 0);
    
    ChoiceBox<Integer> nbOfPlayersChoiceBox = new ChoiceBox<Integer>();
    nbOfPlayersChoiceBox.setPrefWidth(200);
    
    nbOfPlayersChoiceBox.setItems(
        FXCollections.observableArrayList(
            2,
            3,
            4
        )
    );
    
    nbOfPlayersChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        int nbOfPlayers = nbOfPlayersChoiceBox.getItems().get((Integer) newValue);
        drawPlayersList(nbOfPlayers);
      }
    });
    
    GridPane.setMargin(nbOfPlayersChoiceBox, new Insets(5, 0, 5, 0));
    
    playersSettingsGrid.add(nbOfPlayersChoiceBox, 1, 0);
    
    playersSettingsGrid.add(playersList, 0, 1, 2, 1);
    
    root.add(playersSettingsGrid, 0, currentIndex++);
    
    Separator playersSettingsSeparator = new Separator();
    root.add(playersSettingsSeparator, 0, currentIndex++);
    
    GridPane.setMargin(playersSettingsSeparator, new Insets(5, 0, 5, 0));
  }
  
  private void drawPlayersList(int nbOfPlayers) {
    playersList.getChildren().clear();
    
    ColumnConstraints col1 = new ColumnConstraints(150);
    ColumnConstraints col2 = new ColumnConstraints(100);
    ColumnConstraints col3 = new ColumnConstraints(100);
    
    playersList.getColumnConstraints().addAll(col1, col2, col3);
    
    for (int i = 0; i < nbOfPlayers; i++) {
      Label playerName = new Label("Joueur " + (i + 1));
      playersList.add(playerName, 0, i);
      
      ChoiceBox<String> playerTypeChoiceBox = new ChoiceBox<String>();
      
      playerTypeChoiceBox.setItems(
        FXCollections.observableArrayList(
          "Joueur local",
          "Joueur IA"
        )
      );
      
      playerTypeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
          String playerType = playerTypeChoiceBox.getItems().get((Integer) newValue);
          
          if (playerType.equals("Joueur IA")) {
            
          } else {
            
          }
        }
      });
      
      playersList.add(playerTypeChoiceBox, 1, i);
      
      GridPane.setMargin(playerTypeChoiceBox, new Insets(5, 0, 5, 0));
    }
    
    // On resize la fenêtre à son contenu
    stage.sizeToScene();
  }
  
  /**
   * Dessine les boutons en bas de fenêtre
   */
  private void drawButtons() {
    GridPane buttonsGrid = new GridPane();
    
    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(50);
    ColumnConstraints col2 = new ColumnConstraints();
    col2.setPercentWidth(50);
    
    buttonsGrid.getColumnConstraints().setAll(col1, col2);
    
    Button startButton = new Button();
    startButton.setText("Lancer la partie");
    
    startButton.setOnAction((event) -> {
      //startGame();
    });
    
    buttonsGrid.add(startButton, 0, 0);
    
    Button cancelButton = new Button();
    cancelButton.setText("Annuler");
    
    cancelButton.setOnAction((event) -> {
      stage.close();
    });
   
    GridPane.setHalignment(cancelButton, HPos.RIGHT);
    
    buttonsGrid.add(cancelButton, 1, 0);
    
    root.add(buttonsGrid, 0, currentIndex++);
  }
}
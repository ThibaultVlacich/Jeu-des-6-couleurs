/**
 * Jeu des 6 couleurs
 *
 * @package view
 * @class   GameWindow
 * @desc    Défini la fenêtre de jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package view;

import java.io.File;

import file.Save;

import game.Game;
import game.Player;
import models.TileColor;

import observer.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;

public class GameWindow implements Observer {
  // Stock l'instance de jeu
  private Game game;
  
  // Vue racine de la fenêtre
  private GridPane root = new GridPane();
  
  // Vue représentant la liste des joueurs
  private GridPane playersGrid;
  
  // Vue représentant la grille de jeu
  private GridPane gameGrid;
  
  // Scène javaFX racine de la fenêtre
  private Scene scene;
  
  GameWindow(Game g) {
    game = g;
    
    // On ajoute la présente vue à la liste des observateurs du jeu
    // et on lance le jeu
    game.addObserver(this);
    game.start();
    
    // Création de la barre des menus
    addMenuBar();
    
    // Visualisation des joueurs
    drawPlayers();
    
    // Rendu de la grille du jeu
    drawGameGrid();
    
    // Ajout de la grille à la scène
    scene = new Scene(root);
    
    // Chargement des fichiers CSS
    scene.getStylesheets().add(getClass().getResource("css/game.css").toExternalForm());
  }
  
  /**
   * Permet de créer la barre des menus
   */
  private void addMenuBar() {
    MenuBar menuBar = new MenuBar();
    
    /**
     * Menu Fichier
     */
    Menu menuFile = new Menu("Fichier");
    
    MenuItem newGameItem = new MenuItem("Nouvelle partie");
    newGameItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        System.out.println("lel");
      }
    });
    
    menuFile.getItems().add(newGameItem);
    
    MenuItem saveGameItem = new MenuItem("Sauvegarder la partie");
    saveGameItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        String json = Save.save(game);
        
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Fichier texte", "*.txt")
        );
        
        File file = fileChooser.showSaveDialog(Home.mainStage);
        
        System.out.println(file);
        
        if (file != null) {
          Save.saveToFile(json, file);
        }
      }
    });
    
    menuFile.getItems().add(saveGameItem);
    
    MenuItem quitItem = new MenuItem("Quitter");
    quitItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        System.exit(0);
      }
    });
    
    menuFile.getItems().add(quitItem);
    
    menuBar.getMenus().add(menuFile);
    
    root.add(menuBar, 0, 0);
  }
  
  /**
   * Créé la vue montrant la liste des joueurs
   */
  private void drawPlayers() {
    playersGrid = new GridPane();
    
    for (int i = 1; i <= game.getNbOfPlayers(); i++) {
      GridPane playerGrid = new GridPane();
      
      playerGrid.getStyleClass().add("playerBox");
      
      if (i == game.getCurrentPlayer().ID) {
        playerGrid.getStyleClass().add("current");
      }
      
      Player player = game.getPlayerWithID(i);
      
      Label playerName = new Label("Joueur "+i);
      
      playerGrid.add(playerName, 0, 0);
      
      Label playerTiles = new Label("Cases possédées : "+game.getGrid().countTilesOwnedBy(i));
      
      playerGrid.add(playerTiles, 0, 1);
      
      Button button = new Button();
      
      button.getStyleClass().add("tile");
      button.getStyleClass().add(TileColor.getColorClassName(player.getColor()));
      
      playerGrid.add(button, 1, 0, 1, 2);
      
      playersGrid.add(playerGrid, i - 1, 0);
    }
    
    root.add(playersGrid, 0, 1);
  }
  
  /**
   * Permet de dessiner la grille de jeu dans la vue
   */
  private void drawGameGrid() {
    gameGrid = game.getGrid().show2D(game);
    
    root.add(gameGrid, 0, 2);
  }
  
  /**
   * Permet de mettre à jour la vue
   */
  public void updateView() {
    root.getChildren().remove(playersGrid);
    root.getChildren().remove(gameGrid);
    
    drawPlayers();
    drawGameGrid();
  }
  
  /**
   * Affiche au joueur qu'il ne peut pas choisir cette couleur
   * 
   * @param c La couleur en question
   */
  public void cantChooseColor(TileColor c) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Jeu des 6 couleurs");
    alert.setHeaderText(null);
    alert.setContentText("Vous ne pouvez pas choisir cette couleur, elle est déjà prise !");

    alert.showAndWait();
  }
  
  /**
   * Affiche aux joueurs que la partie est terminée
   * 
   * @param winnerID
   */
  public void gameOver(int winnerID) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Jeu des 6 couleurs");
    alert.setHeaderText(null);
    alert.setContentText("La partie est terminée ! Le gagnant est le joueur " + winnerID + " !");

    alert.showAndWait();
  }
  
  /**
   * Permet d'obtenir la scène JavaFX
   * 
   * @return
   */
  public Scene getScene() {
    return scene;
  }

}

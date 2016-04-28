package view;

import game.Game;

import models.TileColor;

import observer.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class GameWindow implements Observer {
  // Stock une instance de l'objet Game
  private Game game;
  
  //
  private GridPane root = new GridPane();
  
  private GridPane gameGrid;
  
  //
  private Scene scene;
  
  GameWindow(Game g) {
    game = g;
    
    // Création de la barre des menus
    addMenuBar();
    
    drawGameGrid();
    
    // Ajout de la grille à la scène
    scene = new Scene(root);
    
    scene.getStylesheets().add(getClass().getResource("css/game.css").toExternalForm());

    // On ajoute la présente vue à la liste des observers du jeu
    // et on lance le jeu
    game.addObserver(this);
    game.start();
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
  
  private void drawGameGrid() {
    gameGrid = game.getGrid().show2D(game);
    
    root.add(gameGrid, 0, 1);
  }
  
  /**
   * Permet de mettre à jour la vue
   */
  public void updateView() {
    root.getChildren().remove(gameGrid);
    
    gameGrid = game.getGrid().show2D(game);
    
    root.add(gameGrid, 0, 1);
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
  
  public Scene getScene() {
    return scene;
  }

}

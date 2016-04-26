package view;

import game.Game;
import game.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import models.TileColor;

public class GameWindow {
  // Stock une instance de l'objet Game
  private Game game;
  
  //
  private GridPane root = new GridPane();
  
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
    GridPane gameGrid = game.getGrid().show2D();
    
    root.add(gameGrid, 0, 1);
  }
  
  public Scene getScene() {
    return scene;
  }

}

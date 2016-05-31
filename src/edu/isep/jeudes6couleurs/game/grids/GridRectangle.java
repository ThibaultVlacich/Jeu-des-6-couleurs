package edu.isep.jeudes6couleurs.game.grids;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import edu.isep.jeudes6couleurs.game.Game;
import edu.isep.jeudes6couleurs.game.Tile;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   GridSquare
 * @desc    Classe définissant une grille de type carrée
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class GridRectangle implements Grid {
  // Taille de la grille
  private int sizex;
  private int sizey;

  // Grille du jeu
  private Tile[][] grid;

  // Stock les coordonnées des coins
  public String[][] cornersCoordinates = {
      {"min", "min"}, // En haut à gauche - Joueur 1
      {"max", "max"}, // En bas à droite  - Joueur 2
      {"min", "max"}, // En haut à droite - Joueur 3
      {"max", "min"}  // En bas à gauche  - Joueur 4
  };

  /**
   * Initialise la grille avec sa taille par défaut
   */
  public GridRectangle() {
    this(20,13);
  }

  /**
   * Initialise la grille avec une taille donnée
   *
   * @param s Taille de la grille
   */
  public GridRectangle(int x, int y) {
    sizex = x;
    sizey = y;

    grid = new Tile[x][y];
  }
  
  /**
   * Permet de construire une copie d'une autre instance de GridRectangle
   * 
   * @param copyInstance  L'instance à dupliquer
   */
  public GridRectangle(GridRectangle copyInstance) {
    sizex = copyInstance.getSizeX();
    sizey = copyInstance.getSizeY();
    
    grid = new Tile[sizex][sizey];
    
    for (int i = 0; i < sizex; i++) {
      for (int j = 0; j < sizey; j++) {
        Tile tile = copyInstance.getTile(i, j);
        
        grid[i][j] = new Tile(tile.getColor());
        grid[i][j].setPlayerID(tile.getPlayerID());
      }
    }
  }

  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    for(int i = 0; i < sizex; i++) {
      for(int j = 0; j < sizey; j++) {
        TileColor randomColor = TileColor.getRandomColor();

        grid[i][j] = new Tile(randomColor);
      }
    }
  }

  /**
   * Remplit la grille à partir d'une sauvegarde
   */
  public void initWithSave(JSONArray colorGrid, JSONArray playerGrid) {
    for(int i = 0; i < sizex; i++) {
      JSONArray colorLine  = (JSONArray) colorGrid.get(i);
      JSONArray playerLine = (JSONArray) playerGrid.get(i);

      for(int j = 0; j < sizey; j++) {
        String    colorCode   = (String) colorLine.get(j);
        TileColor randomColor = TileColor.getColorFromCode(colorCode);
        int       pID         = ((Long) playerLine.get(j)).intValue();

        grid[i][j] = new Tile(randomColor);
        grid[i][j].setPlayerID(pID);
      }
    }
  }

  /**
   * Permet d'obtenir la case de la grille aux coordonnées demandées
   *
   * @param x, y  Coordonnées de la case demandée
   *
   * @return  La case située aux coordonnés (x, y)
   */
  public Tile getTile(int x, int y) {
    return grid[x][y];
  }

  /**
   * Permet d'assigner à un joueur toutes les cases de la couleur qu'il demande
   * et qui touchent une case qu'il possède déjà
   *
   * @param pID Le joueur à qui assigner les cases
   * @param c   La couleur choisie par le joueur
   */
  public void assignTiles(int pID, TileColor c) {
    int newAssignedTiles = -1;

    while (newAssignedTiles != 0) {
      // Tant que la grille a été modifiée à l'éxécution précédente de la boucle,
      // on continue à vérifier si des cases ne peuvent pas être contrôlées
      newAssignedTiles = 0;

      // On assigne les cases de la couleur demandée au joueur
      for (int i = 0; i < sizex; i++) {
        for (int j = 0; j < sizey; j++) {
          Tile tile = grid[i][j];

          if (tile.getPlayerID() == pID) {
            // La case appartient au joueur
            // On la met de la nouvelle couleur choisie par le joueur
            tile.setColor(c);

            if (i > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[i - 1][j].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i - 1][j].getPlayerID() != pID
                  ) {
                // Case située en haut
                grid[i - 1][j].setPlayerID(pID);

                newAssignedTiles++;
              }
            }

            if (j > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[i][j - 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i][j - 1].getPlayerID() != pID
                  ) {
                // Case située à gauche
                grid[i][j - 1].setPlayerID(pID);

                newAssignedTiles++;
              }
            }

            if (i < sizex - 1){
              if (
                  // La case est de la couleur voulue
                  grid[i + 1][j].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i + 1][j].getPlayerID() != pID
                  ) {
                // Case située en dessous
                grid[i + 1][j].setPlayerID(pID);

                newAssignedTiles++;
              }
            }

            if (j < sizey - 1){
              if (
                  // La case est de la couleur voulue
                  grid[i][j + 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i][j + 1].getPlayerID() != pID
                  ) {
                // Case située à droite
                grid[i][j + 1].setPlayerID(pID);

                newAssignedTiles++;
              }
            }
          }
        }
      }
    }
  }

  /**
   * Permet d'assigner un joueur à une case
   *
   * @param x, y  Coordonnées de la case demandée
   * @param pID   Le joueur à qui assigner la case
   */
  public void assignTile(int x, int y, int pID) {
    Tile tile = grid[x][y];

    tile.setPlayerID(pID);
  }
  
  public int getSize() {
    return 0;
  }

  /**
   * Permet d'obtenir la largeur de la grille
   *
   * @return  La largeur de la grille
   */
  public int getSizeX() {
    return sizex;
  }
  
  /**
   * Permet d'obtenir la hauteur de la grille
   *
   * @return  La hauteur de la grille
   */
  public int getSizeY() {
    return sizey;
  }
  
  /**
   * Compte le nombre de cases qu'un joueur pourrait obtenir avec la couleur spécifiée
   * 
   * @param pID   L'ID du joueur
   * @param color La couleur choisie
   * 
   * @return  Le nombre de cases qu'il obtiendrait
   */
  public int countTilesTakeable(int pID, TileColor c) {
    // On créé une copie de la grille actuelle
    GridRectangle newGameGrid = new GridRectangle(this);
    
    // On assigne "virtuellement" au joueur la couleur
    newGameGrid.assignTiles(pID, c);
    
    // On compte le nombre de nouvelles cases obtenues
    int tilesTooked = (newGameGrid.countTilesOwnedBy(pID) - this.countTilesOwnedBy(pID));
    
    return tilesTooked;
  }

  /**
   * Permet d'obtenir le nombre de cases dans la grille
   * 
   * @return
   */
  public int totalNumberOfTiles() {
    return (int) (sizex*sizey);
  }
  
  /**
   * Permet d'assigner un coin au joueur
   *
   * @param   playerID  L'ID du joueur
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID) {
    String[] cornerCoordinate = cornersCoordinates[playerID - 1];
    
    int x = (cornerCoordinate[0].equals("min")) ? 0 : sizex - 1;
    int y = (cornerCoordinate[1].equals("min")) ? 0 : sizey - 1;
    
    assignTile(x, y, playerID);
    
    return getTile(x, y).getColor();
  }
  
  /**
   * Permet d'assigner un coin au joueur avec une couleur donnée
   *
   * @param   playerID  L'ID du joueur
   * @param   color     La couleur à associer
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID, TileColor color) {
    String[] cornerCoordinate = cornersCoordinates[playerID - 1];
    
    int x = (cornerCoordinate[0].equals("min")) ? 0 : sizex - 1;
    int y = (cornerCoordinate[1].equals("min")) ? 0 : sizey - 1;
    
    assignTile(x, y, playerID);
    getTile(x, y).setColor(color);
    
    return getTile(x, y).getColor();
  }

  /**
   * Permet de compter le nombre de cases possédées par un joueur
   *
   * @param   pID L'ID du joueur (0 pour avoir les cases libres)
   *
   * @return  Le nombre de cases libres
   */
  public int countTilesOwnedBy(int pID) {
    int count = 0;

    for (int i = 0; i < sizex; i++) {
      for (int j = 0; j < sizey; j++) {
        Tile tile = grid[i][j];

        if (tile.getPlayerID() == pID) {
          // La case est libre
          count++;
        }
      }
    }

    return count;
  }

  /**
   * Permet d'afficher la grille en mode 2D
   */
  public GridPane show2D(Game game) {
    GridPane gameGrid = new GridPane();

    for (int i = 0; i < sizex; i++) {
      for (int j = 0; j < sizey; j++) {
        Tile tile = getTile(i, j);
        int  pID  = tile.getPlayerID();

        Button button = new Button();

        button.getStyleClass().add("tile");
        button.getStyleClass().add(TileColor.getColorClassName(tile.getColor()));
        
        if (sizey > 19) {
          button.getStyleClass().add("small");
        }

        if (pID != 0) {
          button.setText(Integer.toString(pID));
        }

        button.setOnAction(event -> {
          game.chooseColor(tile.getColor());
        });

        gameGrid.add(button, j, i);
      }
    }

    return gameGrid;
  }

  /**
   * Permet d'exporter la grille au format JSON
   *
   * @return  L'objet JSON représentant la grille
   */
  public JSONObject exportToJSON() {
    JSONObject jsonObject = new JSONObject();

    JSONArray colorGrid = new JSONArray();

    for (int i = 0; i < sizex; i++) {
      JSONArray lineArray = new JSONArray();

      for (int j = 0; j < sizey; j++) {
        TileColor color = grid[i][j].getColor();
        lineArray.add(TileColor.getColorCode(color));
      }

      colorGrid.add(lineArray);
    }

    jsonObject.put("colorGrid", colorGrid);

    JSONArray playerGrid = new JSONArray();

    for (int i = 0; i < sizex; i++) {
      JSONArray lineArray = new JSONArray();

      for (int j = 0; j < sizey; j++) {
        int pID = grid[i][j].getPlayerID();

        lineArray.add(pID);
      }

      playerGrid.add(lineArray);
    }

    jsonObject.put("playerGrid", playerGrid);

    return jsonObject;
  }
}

package edu.isep.jeudes6couleurs.game.grids;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.layout.GridPane;

import edu.isep.jeudes6couleurs.game.Game;
import edu.isep.jeudes6couleurs.game.Tile;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package   edu.isep.jeudes6couleurs.game
 * @interface Grid
 * @desc      Interface définissant le modèle à suivre pour créer une grille;
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */
public interface Grid {
  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom();

  /**
   * Remplit la grille à partir d'une sauvegarde
   */
  public void initWithSave(JSONArray colorGrid, JSONArray playerGrid);

  /**
   * Permet d'obtenir la case de la grille aux coordonnées demandées
   *
   * @param x, y  Coordonnées de la case demandée
   *
   * @return  La case située aux coordonnés (x, y)
   */
  public Tile getTile(int x, int y);

  /**
   * Permet d'assigner à un joueur toutes les cases de la couleur qu'il demande
   * et qui touchent une case qu'il possède déjà
   *
   * @param pID Le joueur à qui assigner les cases
   * @param c   La couleur choisie par le joueur
   */
  public void assignTiles(int pID, TileColor c);

  /**
   * Permet d'assigner un joueur à une case
   *
   * @param x, y  Coordonnées de la case demandée
   * @param pID   Le joueur à qui assigner la case
   */
  public void assignTile(int x, int y, int pID);

  /**
   * Permet d'obtenir la taille de la grille
   *
   * @return  La taille de la grille
   */
  public int getSize();

  /**
   * Permet d'obtenir le nombre de cases dans la grille
   * 
   * @return
   */
  public int totalNumberOfTiles();
  
  /**
   * Permet d'assigner un coin au joueur
   *
   * @param   playerID  L'ID du joueur
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID);
  
  /**
   * Permet d'assigner un coin au joueur avec une couleur donnée
   *
   * @param   playerID  L'ID du joueur
   * @param   color     La couleur à associer
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID, TileColor color);

  /**
   * Compte le nombre de cases qu'un joueur pourrait obtenir avec la couleur spécifiée
   * 
   * @param pID   L'ID du joueur
   * @param color La couleur choisie
   * 
   * @return  Le nombre de cases qu'il obtiendrait
   */
  public int countTilesTakeable(int pID, TileColor color);
  
  /**
   * Permet de compter le nombre de cases possédées par un joueur
   *
   * @param   pID L'ID du joueur (0 pour avoir les cases libres)
   *
   * @return  Le nombre de cases libres
   */
  public int countTilesOwnedBy(int pID);

  /**
   * Permet d'afficher la grille en mode 2D
   * @return  La grille créée
   */
  public GridPane show2D(Game game);

  /**
   * Permet d'exporter la grille au format JSON
   *
   * @return  L'objet JSON représentant la grille
   */
  public JSONObject exportToJSON();
}

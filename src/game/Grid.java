/**
 * Jeu des 6 couleurs
 *
 * @package   game
 * @interface Grid
 * @desc      Interface définissant le modèle à suivre pour créer une grille;
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.layout.GridPane;

import models.TileColor;

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
   * Permet d'obtenir les coordonnées du coin à associer au joueur
   *
   * @param   player  Le numéro du joueur
   *
   * @return  Les Coordonnées du coin
   */
  public String[] getCornerCoordinate(int player);

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

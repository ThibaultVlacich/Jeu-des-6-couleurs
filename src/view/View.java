/**
 * Jeu des 6 couleurs
 *
 * @package view
 * @class   View
 * @desc    Gère l'affichage graphique du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package view;

// Importe le package StdDraw
import edu.princeton.cs.introcs.StdDraw;

// Importe l'objet Java Color
import java.awt.Color;

// Importe les modèles utiles
import models.TileColor;

// Importe l'objet grille
import game.Grid;
import game.Tile;

public class View {
  public static void showGrid(Grid g) {
    int size = g.getSize();
    
    StdDraw.setXscale(-1, size);
    StdDraw.setYscale(-size, 1);

    StdDraw.clear(StdDraw.WHITE);
    
    StdDraw.setPenColor(StdDraw.BLACK);
    
    // Dessine le quadrillage de la grille
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        StdDraw.square(i, -j, 0.5);
      }
    }
    
    // Place les pions sur la grille
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        Tile tile = g.getTile(i, j);
        
        Color drawColor = TileColor.getDrawColor(tile.getColor());
        
        StdDraw.setPenColor(drawColor);
        
        // On dessine le pion
        StdDraw.filledCircle(j, -i, 0.4);
        
        if (tile.getPlayerID() != 0) {
            // On affiche l'ID du joueur possédant la case, le cas échéant
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(j, -i, Integer.toString(tile.getPlayerID()));
        }
      }
    }
  }
}

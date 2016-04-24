/**
 * Jeu des 6 couleurs
 *
 * @package view
 * @class   View
 * @desc    Gère l'typegrille graphique du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package view;

// Importe le package StdDraw
import edu.princeton.cs.introcs.StdDraw;

// Importe l'objet Java Color
import java.awt.Color;
import java.awt.Font;

// Importe les modèles utiles
import models.TileColor;

// Importe l'objet grille
import game.Grid;
import game.Tile;

public class View {
  public static void showGrid(Grid g, int typegrille) {
    int size = g.getSize();
    
    if(typegrille == 0){
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
    
    //typegrille du losange
    if(typegrille == 1){
    	
        StdDraw.setXscale(-1, size*3);
        StdDraw.setYscale(-size*2, 1);

        StdDraw.clear(StdDraw.WHITE);
        
        StdDraw.setPenColor(StdDraw.BLACK);
        
    	for(int i = 0; i < size*2; i++) {
    		for(int j = 0; j < size; j++) {
    			for(int k = 0; k < size; k++){
    				if(k+j == i){ //On consid�re alors l'�l�ment [k][j]
    					
    				
    			    	
    			    	// Place les pions sur la grille
    					Tile tile = g.getTile(k, j);
        
    					Color drawColor = TileColor.getDrawColor(tile.getColor());
        
    					StdDraw.setPenColor(drawColor);
        
    					// On dessine le pion
    					StdDraw.filledCircle(15+j-k, -i, 0.4);
    					
    					StdDraw.setPenColor();
    			    	// Dessine le quadrillage de la grille autour du pion
		    			StdDraw.line(15+j-k+1, -i, 15+j-k, -i+1);
		    			StdDraw.line(15+j-k-1, -i, 15+j-k, -i+1);
		    			StdDraw.line(15+j-k+1, -i, 15+j-k, -i-1);
		    			StdDraw.line(15+j-k-1, -i, 15+j-k, -i-1);

        
    					if (tile.getPlayerID() != 0) {
    						// On affiche l'ID du joueur possédant la case, le cas échéant
    						Font font = new Font("Sans Serif",Font.BOLD,12);
    						StdDraw.setFont(font);
    						StdDraw.setPenColor(Color.BLACK);
    						StdDraw.text(15+j-k, -i, Integer.toString(tile.getPlayerID()));
    					}
    				}
    			}
    		}
    	}
   }    
 }
}

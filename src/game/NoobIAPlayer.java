/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   NoobIAPlayer
 * @desc    Classe définissant le joueur IA de niveau "noob"
 * @desc    Cette IA choisit la couleur qui lui ferait gagner le plus de cases
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import models.TileColor;

public class NoobIAPlayer extends Player {
  NoobIAPlayer(int playerID) {
    super(playerID);
    
    playerType = PlayerType.IA;
  }
  
  public void play(Game game) {
    int optimal = 0;
    TileColor colorChoosed = getColor();
    
    for (TileColor testcolor: TileColor.values()) {
      if(!game.isColorOwned(testcolor)){
        int valeur = game.getGrid().getTilesTakeable(ID, testcolor);
                
        if(valeur > optimal){
          colorChoosed = testcolor;
          optimal = valeur;
        }
      }
    }
    
    game.chooseColor(colorChoosed);
  }
}
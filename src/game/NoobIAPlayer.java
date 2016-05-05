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
    int       maxTilesTooked = 0;
    TileColor colorChoosed   = getColor();
    Grid      gameGrid       = game.getGrid();
    
    for (TileColor colorTested: TileColor.values()) {
      if(!game.isColorOwned(colorTested)){
        int tilesTooked = gameGrid.countTilesTakeable(ID, colorTested);
                
        if(tilesTooked > maxTilesTooked){
          colorChoosed   = colorTested;
          maxTilesTooked = tilesTooked;
        }
      }
    }
    
    game.chooseColor(colorChoosed);
  }
}
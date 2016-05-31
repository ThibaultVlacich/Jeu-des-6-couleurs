package edu.isep.jeudes6couleurs.game.players;

import edu.isep.jeudes6couleurs.game.Game;
import edu.isep.jeudes6couleurs.game.grids.Grid;
import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   NoobIAPlayer
 * @desc    Classe définissant le joueur IA de niveau "noob"
 * @desc    Cette IA choisit la couleur qui lui ferait gagner le plus de cases
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class NoobIAPlayer extends Player {
  public NoobIAPlayer(int playerID) {
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
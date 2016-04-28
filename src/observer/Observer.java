package observer;

/**
 * Jeu des 6 couleurs
 *
 * @package   observer
 * @interface Observer
 * @desc      Interface définissant un observateur
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */

import models.TileColor;

public interface Observer {
  /**
   * Permet de mettre à jour la vue
   */
  public void updateView();
  
  /**
   * Affiche au joueur qu'il ne peut pas choisir cette couleur
   * 
   * @param c La couleur en question
   */
  public void cantChooseColor(TileColor c);
  
  /**
   * Affiche aux joueurs que la partie est terminée
   * 
   * @param winnerID
   */
  public void gameOver(int winnerID);
}

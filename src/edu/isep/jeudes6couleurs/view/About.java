package edu.isep.jeudes6couleurs.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.view
 * @class   About
 * @desc    Défini la fenêtre "A propos" du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class About implements Initializable {
  private HostServices hostServices;
  
  public void setHostServices(HostServices _hostServices) {
    hostServices = _hostServices;
  }
  
  public HostServices getHostServices() {
    return hostServices;
  }
  
  @FXML
  private ImageView isepLogo;
  
  @FXML
  private ImageView githubLogo;
  
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    isepLogo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        getHostServices().showDocument("http://www.isep.fr/");
      }
    });
    
    githubLogo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        getHostServices().showDocument("https://github.com/ThibaultVlacich/Jeu-des-6-couleurs");
      }
    });
  }
  
  @FXML
  protected void close(ActionEvent event) {
    Stage stage = (Stage) isepLogo.getScene().getWindow();
    stage.close();
  }
}

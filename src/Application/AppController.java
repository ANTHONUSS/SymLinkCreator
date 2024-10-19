package Application;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    public MFXTextField sourceLabel;
    @FXML
    public MFXTextField linkLabel;
    @FXML
    public MFXTextField infoLabel;
    @FXML
    public MFXButton symlinkButton;
    @FXML
    public MFXButton linkButton;
    @FXML
    public MFXButton sourceButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add radius to buttons ripple animation
        setClipButton(symlinkButton, 20);
        setClipButton(linkButton, 5);
        setClipButton(sourceButton, 5);
    }

    private void setClipButton (MFXButton button, int radius) {
        Rectangle clip = new Rectangle();
        clip.setArcWidth(radius*2);
        clip.setArcHeight(radius*2);
        button.setClip(clip);
        button.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            clip.setWidth(newValue.getWidth());
            clip.setHeight(newValue.getHeight());
        });
    }

    @FXML
    public void sourcePath(ActionEvent actionEvent) {
    }

    @FXML
    public void linkPath(ActionEvent actionEvent) {
    }

    @FXML
    public void createSymLink(ActionEvent actionEvent) {
    }
}

package Application;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    public MFXTextField sourceLabel;
    @FXML
    public MFXTextField linkLabel;
    @FXML
    public MFXTextField infoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

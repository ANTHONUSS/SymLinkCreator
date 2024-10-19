package Application;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    public MFXTextField sourceLabel;
    @FXML
    public MFXTextField linkLabel;
    @FXML
    public MFXTextField fileName;
    @FXML
    public MFXTextField infoLabel;
    @FXML
    public MFXButton symlinkButton;
    @FXML
    public MFXButton linkButton;
    @FXML
    public MFXButton sourceButton;
    @FXML
    public MFXToggleButton sourceTypeToggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add radius to buttons ripple animation
        setClipButton(symlinkButton, 20);
        setClipButton(linkButton, 5);
        setClipButton(sourceButton, 5);

        //Verify sourceTypeToggle
        sourceTypeToggleListener();
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
        if (!sourceTypeToggle.isSelected()) {
            FileChooser filechooser = new FileChooser();
            java.io.File file = filechooser.showOpenDialog(null);
            if (file != null) {
                sourceLabel.setText(file.getAbsolutePath());
            }
        } else {
            DirectoryChooser directorychooser = new DirectoryChooser();
            java.io.File file = directorychooser.showDialog(null);
            if (file != null) {
                sourceLabel.setText(file.getAbsolutePath());
            }
        }
    }

    @FXML
    public void linkPath(ActionEvent actionEvent) {
        DirectoryChooser directorychooser = new DirectoryChooser();
        java.io.File file = directorychooser.showDialog(null);
        if (file != null) {
            linkLabel.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void createSymLink(ActionEvent actionEvent) {
        Path source = Paths.get(sourceLabel.getText());
        String linkName = linkLabel.getText()+"\\"+fileName.getText();
        Path link = Paths.get(linkName);
        infoLabel.setVisible(true);

        try {
            Files.createSymbolicLink(link, source);
            infoLabel.setText("SymLink created");
        } catch (FileAlreadyExistsException e) {
            infoLabel.setText("File already exists");
        } catch (IOException e) {
            infoLabel.setText("Error while creating Symbolic link : " + e.getMessage());
        }
    }

    private void sourceTypeToggleListener(){
        sourceTypeToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                fileName.setFloatingText("New directory name");
            } else {
                fileName.setFloatingText("New file name");
            }
        });

    }
}

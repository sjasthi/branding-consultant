import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;

import java.nio.file.Path;

public class BrandApp extends Application {

    @Override
    public void start(Stage stage) {
        TextField folderField = new TextField();
        Button browse = new Button("Browse");
        Button scan = new Button("Scan Repository");
        TextArea output = new TextArea();

        browse.setOnAction(_ -> {
            DirectoryChooser chooser = new DirectoryChooser();
            var dir = chooser.showDialog(stage);
            if (dir != null) {
                folderField.setText(dir.getAbsolutePath());
            }
        });

        scan.setOnAction(_ -> {
            try {
                String report = RepositoryScanner.scan(Path.of(folderField.getText()));
                output.setText(report);
            } catch (Exception ex) {
                output.setText("Error: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10,
                new Label("Repository Folder:"),
                new HBox(10, folderField, browse),
                scan,
                output
        );

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("BrandApp Repository Scanner");
        stage.show();
    }

    @SuppressWarnings("unused")
    static void main(String[] args) {
        launch(args);
    }

}

package aydin.firebasedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent; // <-- The missing import
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

public class DemoApp extends Application {
    public static Scene scene;

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

        // Change "primary" to "welcome" to load the new screen first
        scene = new Scene(loadFXML("welcome"), 640, 480);

        // Add the CSS stylesheet (for Part 2c)
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DemoApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
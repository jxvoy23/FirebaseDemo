package aydin.firebasedemo;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button; // <-- You'll need this too
import java.io.IOException;


public class WelcomeController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button signInButton;

    @FXML
    private Label statusLabel;

    @FXML
    void registerButtonClicked() {
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            statusLabel.setText("Email and password cannot be empty.");
            return;
        }

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailField.getText())
                .setEmailVerified(false)
                .setPassword(passwordField.getText())
                .setDisabled(false);

        try {
            UserRecord userRecord = DemoApp.fauth.createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            statusLabel.setText("Registration successful! Please sign in.");
        } catch (FirebaseAuthException ex) {
            System.out.println("Error creating new user: " + ex.getMessage());
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    void signInButtonClicked() throws IOException {
        // NOTE: The Firebase Admin SDK (firebase-admin) used in this project
        // CANNOT verify user passwords. It's meant for server-side management.
        // A real client app would use a client-side SDK (like Firebase Auth for Web,
        // Android, or iOS) to handle sign-in.

        // For this academic assignment, we will *simulate* a successful sign-in
        // by just navigating to the next screen.

        if (emailField.getText().isEmpty()) {
            statusLabel.setText("Please enter an email to sign in.");
            return;
        }

        System.out.println("Simulating sign-in for user: " + emailField.getText());
        // Navigate to the primary data access view
        DemoApp.setRoot("primary");
    }
}
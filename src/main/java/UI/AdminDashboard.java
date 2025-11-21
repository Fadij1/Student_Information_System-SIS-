package UI;

import DAO.AdminDAO;
import Model.Admin;
import Model.Course;
import Model.Teacher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.InputStream;

public class AdminDashboard {

    public void show(Stage stage, Admin admin) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4f6f9;");

        root.setTop(createTopBar());
        root.setLeft(createSidebar(admin, root));
        root.setCenter(createTeachersView(root)); // Default View

        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Admin Dashboard - " + admin.getUsername());
        stage.setScene(scene);
        stage.show();
    }

    // =========================================
    // 1. Top Bar
    // =========================================
    private HBox createTopBar() {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setStyle("-fx-background-color: #2c3e50;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = loadImageView("/images/sis_logo.png", 30, 30);
        Label brandLabel = new Label("SIS Admin Panel");
        brandLabel.setTextFill(Color.WHITE);
        brandLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        topBar.getChildren().addAll(logo, brandLabel);
        return topBar;
    }

    // =========================================
    // 2. Sidebar
    // =========================================
    private VBox createSidebar(Admin admin, BorderPane root) {

        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(250);
        sidebar.setStyle("-fx-background-color: #34495e;");
        sidebar.setPadding(new Insets(30, 10, 20, 10));
        sidebar.setAlignment(Pos.TOP_CENTER);

        // --- Profile Pic ---
        ImageView profilePic = new ImageView();
        profilePic.setFitWidth(80); profilePic.setFitHeight(80);
        try {
            if (admin.getFullName() != null && !admin.getFullName().isEmpty())
                profilePic.setImage(new Image("file:///" + admin.getFullName().replace("\\", "/")));
            else
                profilePic.setImage(new Image(getClass().getResourceAsStream("/images/student_placeholder.png")));
        } catch (Exception e) {
            try { profilePic.setImage(new Image(getClass().getResourceAsStream("/images/student_placeholder.png"))); } catch(Exception ignored){}
        }
        Circle clip = new Circle(40, 40, 40);
        profilePic.setClip(clip);

        // --- Name ---
        String displayName = (admin.getPassword() != null && !admin.getPassword().isEmpty()) ? admin.getPassword() : admin.getUsername();
        Label nameLabel = new Label(displayName);
        nameLabel.setTextFill(Color.WHITE);
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        // --- Menu Buttons ---
        VBox navMenu = new VBox(5);
        Button teachBtn = createNavButton("Manage Teachers", true);
        Button courseBtn = createNavButton("Manage Courses", false);
        Button assignBtn = createNavButton("Assign Teachers", false);
        Button profileBtn = createNavButton("My Profile", false); // NEW
        Button logoutBtn = createNavButton("Logout", false);

        // Actions
        teachBtn.setOnAction(e -> {
            root.setCenter(createTeachersView(root));
            setActive(teachBtn, courseBtn, assignBtn, profileBtn, logoutBtn);
        });

        courseBtn.setOnAction(e -> {
            root.setCenter(createCoursesView(root));
            setActive(courseBtn, teachBtn, assignBtn, profileBtn, logoutBtn);
        });

        assignBtn.setOnAction(e -> {
            root.setCenter(createAssignmentView(root));
            setActive(assignBtn, teachBtn, courseBtn, profileBtn, logoutBtn);
        });

        profileBtn.setOnAction(e -> {
            root.setCenter(createProfileView(admin, root)); // NEW
            setActive(profileBtn, teachBtn, courseBtn, assignBtn, logoutBtn);
        });

        logoutBtn.setOnAction(e -> {
            Stage current = (Stage) logoutBtn.getScene().getWindow();
            current.close();
            try { new LoginScreen().show(new Stage()); } catch (Exception ex) {}
        });

        navMenu.getChildren().addAll(teachBtn, courseBtn, assignBtn, profileBtn, logoutBtn);
        sidebar.getChildren().addAll(profilePic, nameLabel, new Region(), navMenu);
        return sidebar;
    }

   

    // =========================================
    // VIEW D: Admin Profile (NEW)
    // =========================================
    private VBox createProfileView(Admin admin, BorderPane root) {
        VBox content = new VBox(15);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Edit Admin Profile");
        title.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 28));
        title.setTextFill(Color.web("#2c3e50"));

        // --- Pic Section ---
        HBox picSection = new HBox(20);
        picSection.setAlignment(Pos.CENTER_LEFT);

        ImageView currentPic = new ImageView();
        currentPic.setFitWidth(100); currentPic.setFitHeight(100);
        Circle clip = new Circle(50, 50, 50);
        currentPic.setClip(clip);

        // Load Image
        try {
            if (admin.getFullName() != null && !admin.getFullName().isEmpty())
                currentPic.setImage(new Image("file:///" + admin.getFullName().replace("\\", "/")));
            else
                currentPic.setImage(new Image(getClass().getResourceAsStream("/images/student_placeholder.png")));
        } catch (Exception e) {
            try { currentPic.setImage(new Image(getClass().getResourceAsStream("/images/student_placeholder.png"))); } catch (Exception ignored) {}
        }

        Button uploadBtn = new Button("Change Picture");
        final StringBuilder newImagePath = new StringBuilder(admin.getProfilePicPath() == null ? "" : admin.getProfilePicPath());

        uploadBtn.setOnAction(e -> {
            javafx.stage.FileChooser fc = new javafx.stage.FileChooser();
            fc.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
            java.io.File f = fc.showOpenDialog(null);
            if (f != null) {
                newImagePath.setLength(0);
                newImagePath.append(f.getAbsolutePath());
                currentPic.setImage(new Image(f.toURI().toString()));
            }
        });
        picSection.getChildren().addAll(currentPic, uploadBtn);

        // --- Fields ---
        Label lblU = new Label("Username:");
        TextField userField = new TextField(admin.getUsername());
        userField.setMaxWidth(400);

        Label lblF = new Label("Full Name:");
        // Ensure we handle potential nulls for FullName
        TextField nameField = new TextField(admin.getPassword() != null ? admin.getPassword() : "");
        nameField.setMaxWidth(400);

        Label lblP = new Label("Password:");
        PasswordField passField = new PasswordField();
        // FIX: Previously this was setting ProfilePicPath to the password field
        passField.setText(admin.getProfilePicPath());
        passField.setMaxWidth(400);

        Button saveBtn = new Button("Save Changes");
        saveBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        Label statusLabel = new Label();

        // --- SAVE LOGIC ---
        saveBtn.setOnAction(e -> {
            AdminDAO dao = new AdminDAO();
            boolean success = dao.updateProfile(
                    admin.getAdminId(),
                    userField.getText(),
                    nameField.getText(),
                    passField.getText(),
                    newImagePath.toString()
            );

            if (success) {
                // 1. RE-FETCH DATA
                // We assume the username/password might have changed, so we use the NEW values to login/fetch
                Admin updatedAdmin = dao.login(userField.getText(), passField.getText());

                if (updatedAdmin != null) {
                    // 2. REFRESH SIDEBAR (To show new Pic/Name)
                    root.setLeft(createSidebar(updatedAdmin, root));

                    // 3. REFRESH CENTER (To show updated fields)
                    root.setCenter(createProfileView(updatedAdmin, root));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile Updated Successfully!");
                    alert.show();
                } else {
                    statusLabel.setText("Saved, but could not refresh data.");
                    statusLabel.setTextFill(Color.ORANGE);
                }
            } else {
                statusLabel.setText("Update Failed. Username might be taken.");
                statusLabel.setTextFill(Color.RED);
            }
        });

        content.getChildren().addAll(title, picSection, lblU, userField, lblF, nameField, lblP, passField, new Region(), saveBtn, statusLabel);
        return content;
    }

    // Helpers
    private Button createNavButton(String text, boolean isActive) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPadding(new Insets(10));
        btn.setFont(Font.font("Segoe UI", 14));
        btn.setCursor(javafx.scene.Cursor.HAND);

        if (isActive) {
            btn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-border-color: transparent transparent transparent #3498db; -fx-border-width: 0 0 0 4;");
        } else {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #bdc3c7; -fx-border-width: 0;");
        }

        btn.setOnMouseEntered(e -> {
            if (!btn.getStyle().contains("#2c3e50")) btn.setStyle("-fx-background-color: #3e5871; -fx-text-fill: white;");
        });
        btn.setOnMouseExited(e -> {
            if (!btn.getStyle().contains("#2c3e50")) btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #bdc3c7;");
        });

        return btn;
    }

    private void setActive(Button active, Button... others) {
        active.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-border-color: transparent transparent transparent #3498db; -fx-border-width: 0 0 0 4;");
        for (Button b : others) {
            b.setStyle("-fx-background-color: transparent; -fx-text-fill: #bdc3c7; -fx-border-width: 0;");
        }
    }

    private ImageView loadImageView(String path, double w, double h) {
        ImageView iv = new ImageView();
        try { InputStream is = getClass().getResourceAsStream(path); if(is!=null) iv.setImage(new Image(is)); } catch(Exception e){}
        iv.setFitWidth(w); iv.setFitHeight(h);
        return iv;
    }
}
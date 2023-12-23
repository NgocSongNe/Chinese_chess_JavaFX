
import Piece.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javax.swing.JFrame;

import controllers.main.*;


public class StartScreen extends Application {
	final double RATIO = 0.6;
	Scene game_screen = null, start_screen = null, about_screen = null;
	Board board = null;
	Cannon cannon;

	public void start(Stage primaryStage) {
		// Stage Setup
		primaryStage.setTitle("Chinese Chess");
		primaryStage.setWidth(1920 * RATIO);
		primaryStage.setHeight(1115 * RATIO);
		primaryStage.setResizable(false);
		setupStartMenu(primaryStage);

		primaryStage.show();
	}

	public void setupStartMenu(Stage primaryStage) {
		ImageView background = createImageView("/Images/main_menu.png", true);

		Button start_button = createButton("/Images/start.png");
		start_button.setOnMouseClicked(e -> {
			setupGameScreen(primaryStage);
			primaryStage.setScene(game_screen);
		});
		Button pve_button = createButton("/Images/pve.png");
		pve_button.setOnMouseClicked(e -> {
			
			MainUI mainUIApp = new MainUI();
			mainUIApp.main(null);
			primaryStage.close();
			
		});
		Button pvp_button = createButton("/Images/pvp.png");
		pvp_button.setOnMouseClicked(e -> {
			
			ClientRun mainUIApp = new ClientRun();
			mainUIApp.main(null);
			primaryStage.close();
			
		});
		Button load_button = createButton("/Images/load.png");
		load_button.setOnMouseClicked(e -> {
			if (game_screen != null && board != null && board.getWin() == false)
				primaryStage.setScene(game_screen);
		});
		Button quit_button = createButton("/Images/quit.png");
		quit_button.setOnMouseClicked(e -> {
			System.exit(0);
		});

		Pane pane = new Pane();
		pane.getChildren().addAll(background, start_button, load_button, quit_button, pve_button,pvp_button);
		start_button.relocate(300 * RATIO, 720 * RATIO);
		pve_button.relocate(800 * RATIO, 890 * RATIO);
		pvp_button.relocate(1200 * RATIO, 890 * RATIO);
		load_button.relocate(800 * RATIO, 720 * RATIO);
		quit_button.relocate(1300 * RATIO, 720 * RATIO);
		start_screen = new Scene(pane);
		start_screen.getStylesheets().add("/css/start_menu.css");
		primaryStage.setScene(start_screen);
	}

	public void setupGameScreen(Stage primaryStage) {
		ImageView background = createImageView("/Images/board.png", true);

		ImageView captured_pieces = createImageView("/Images/captured_pieces.png", false);

		Button save_button = createButton("/Images/save.png");
		save_button.setOnMouseClicked(e -> {
			primaryStage.setScene(start_screen);
		});
		Button quit_button = createButton("/Images/quit.png");
		quit_button.setOnMouseClicked(e -> {
			System.exit(0);
		});

		Pane pane = new Pane();
		pane.getChildren().addAll(background, save_button, quit_button, captured_pieces);
		save_button.relocate(1030 * RATIO, 850 * RATIO);
		quit_button.relocate(1410 * RATIO, 850 * RATIO);
		captured_pieces.relocate(990 * RATIO, 240 * RATIO);
		board = new Board(pane, primaryStage, start_screen);
		game_screen = new Scene(pane);
		game_screen.getStylesheets().add("/css/start_menu.css");
	}

	// ===============================Helper
	// Functions=======================================
	public Button createButton(String image_location) {
		return new Button("", createImageView(image_location, false));
	}

	public ImageView createImageView(String image_location) {
		Image image = new Image(image_location);
		ImageView image_view = new ImageView(image);
		image_view.setFitWidth(image.getWidth() * RATIO);
		image_view.setPreserveRatio(true);
		image_view.setSmooth(true);
		image_view.setCache(true);
		return image_view;
	}

	public ImageView createImageView(String image_location, boolean background) {
		Image image = new Image(image_location);
		ImageView image_view = new ImageView(image);
		if (background) {
			image_view.setFitWidth(1920 * RATIO);
		} else {
			image_view.setFitWidth(image.getWidth() * RATIO);
		}
		image_view.setPreserveRatio(true);
		image_view.setSmooth(true);
		image_view.setCache(true);
		return image_view;
	}
	// ======================================================================================

	public static void main(String[] args) {
		launch("StartScreen");
	}
}
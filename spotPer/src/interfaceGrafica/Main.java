package interfaceGrafica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
	public void start(Stage stage) throws Exception {
		
		System.out.println(
			    getClass().getResource("/interfaceGrafica/base/janelaBase.fxml")
			);

		
		Parent root = FXMLLoader.load(getClass().getResource("/interfaceGrafica/base/janelaBase.fxml"));
		Scene scene = new Scene(root, 1200, 800);
		
		stage.setMinHeight(600);
		stage.setMinWidth(900);
		
		String css = this.getClass().getResource("styles.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		
        launch(args);
    }

}

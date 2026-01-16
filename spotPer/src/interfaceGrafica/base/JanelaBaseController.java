package interfaceGrafica.base;

import interfaceGrafica.base.header.HeaderController;
import interfaceGrafica.base.sideBar.SideBarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class JanelaBaseController {
	@FXML
	BorderPane root;
	
	@FXML
	HeaderController headerController;
	
	@FXML
	SideBarController sidebarController;
	
	
	public void initialize() {
		navegar("/interfaceGrafica/modelos/home.fxml");
	}
	
	private void navegar(String path) {
		try {
			Node pagina = FXMLLoader.load(getClass().getResource(path));
			root.setCenter(pagina);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

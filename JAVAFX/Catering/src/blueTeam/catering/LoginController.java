package blueTeam.catering;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	public void login_btn(ActionEvent event) throws IOException {
		Parent layout_parent = FXMLLoader.load(getClass().getResource(
				"view/EmployeeTableLayout.fxml"));
		Scene layout_scene = new Scene(layout_parent);
		Stage layout_stage = (Stage) ((Node) event.getSource()).getScene()
				.getWindow();
		layout_stage.setScene(layout_scene);
		layout_stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}

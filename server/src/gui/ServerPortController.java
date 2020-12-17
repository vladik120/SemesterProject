package gui;

import Server.ServerUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerPortController {

	@FXML
	private Label LabelPort;

	@FXML
	private TextField TxtPort;

	@FXML
	private Button BtnDone;

	@FXML
	private Button BtnExit;

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerPort.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void Done(ActionEvent event) throws Exception {
		String p;
		p = getport();
		if (p.trim().isEmpty()) {
			System.out.println("You must enter a port number");
		} else {
			FXMLLoader loader = new FXMLLoader();
			ServerUI.runServer(p);

			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = loader.load(getClass().getResource("/gui/ServerConnection.fxml").openStream());
			ServerConnectionController.Sc = loader.getController();

			Scene scene = new Scene(root);
			primaryStage.setTitle("Server Connection");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	public void Exit(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		System.exit(0);
	}

	private String getport() {
		return TxtPort.getText();
	}
}

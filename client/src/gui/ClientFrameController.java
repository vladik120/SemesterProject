package gui;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientFrameController {

	@FXML
	private Button BtnShow;

	@FXML
	private Button BtnExit;

	@FXML
	void Exit(ActionEvent event) {
		System.out.println("--> Exit client frame ");
		ClientUI.chat.accept("exit");
		System.exit(0);
	}

	@FXML
	void Show(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		ChatClient.list.clear();
		ClientUI.chat.accept("show");
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = loader.load(getClass().getResource("/gui/DATA.fxml").openStream());
		DataController dataController = loader.getController();
		dataController.SetTable(ChatClient.list);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Data Table");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Client.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

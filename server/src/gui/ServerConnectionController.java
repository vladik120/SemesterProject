package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Logic.ClientConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServerConnectionController implements Initializable {

	public static ServerConnectionController Sc;

	@FXML // fx:id="BtnExit"
	private Button BtnExit; // Value injected by FXMLLoader

	@FXML // fx:id="TblClient"
	private TableView<ClientConnection> TblClient; // Value injected by FXMLLoader

	@FXML // fx:id="ColIP"
	private TableColumn<ClientConnection, String> ColIP; // Value injected by FXMLLoader

	@FXML // fx:id="ColHost"
	private TableColumn<ClientConnection, String> ColHost; // Value injected by FXMLLoader

	@FXML // fx:id="ColStatus"
	private TableColumn<ClientConnection, String> ColStatus; // Value injected by FXMLLoader

	@FXML
	void Exit(ActionEvent event) {
		System.out.println("--> Exit Server");
		System.exit(0);
	}

	public void Update(ArrayList<ClientConnection> client) {
		System.out.println("--> Update server connection GUI by client --> " + client);
		ObservableList<ClientConnection> data = FXCollections.observableArrayList(client);
		TblClient.setItems(data);
		TblClient.refresh();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ColIP.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("ipAddress"));
		ColHost.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("hostName"));
		ColStatus.setCellValueFactory(new PropertyValueFactory<ClientConnection, String>("status"));
	}

}

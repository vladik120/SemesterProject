package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.Customer;

public class DataController implements Initializable {
	@FXML
	private TableView<Customer> TblData;

	@FXML
	private TableColumn<Customer, String> ColID;

	@FXML
	private TableColumn<Customer, String> ColFname;

	@FXML
	private TableColumn<Customer, String> ColLname;

	@FXML
	private TableColumn<Customer, String> ColEmail;

	@FXML
	private TableColumn<Customer, String> ColTel;

	@FXML
	private Button BtnBack;

	@FXML
	private Button BtnUpdate;

	@FXML
	void Back(ActionEvent event) throws Exception {
		TblData.getItems().clear();
		((Node) event.getSource()).getScene().getWindow().hide();
		System.out.println("--> Return to main screen");
		Stage stage = new Stage();
		ClientUI clientUI = new ClientUI();
		clientUI.start(stage);
	}

	@FXML
	void UpdateEmail(ActionEvent event) {
		List<Customer> temp = TblData.getItems();
		String dataString = "update---";
		System.out.println(temp);
		ChatClient.list.clear();
		for (int i = 0; i < temp.size(); i++) {
			dataString += temp.get(i).toString();
			if (i < temp.size() - 1)
				dataString += ",";
		}
		ClientUI.chat.accept(dataString);
	}

	public void SetTable(ArrayList<Customer> list) {
		ObservableList<Customer> data = FXCollections.observableArrayList(list);
		TblData.setItems(data);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Callback<TableColumn<Customer, String>, TableCell<Customer, String>> cellFactory = new Callback<TableColumn<Customer, String>, TableCell<Customer, String>>() {
			public TableCell<Customer, String> call(TableColumn<Customer, String> p) {
				return new EditingCell();
			}
		};

		ColID.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		ColFname.setCellValueFactory(new PropertyValueFactory<Customer, String>("fname"));
		ColLname.setCellValueFactory(new PropertyValueFactory<Customer, String>("lname"));
		ColEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		ColTel.setCellValueFactory(new PropertyValueFactory<Customer, String>("teln"));

		TblData.setEditable(true);
		ColEmail.setEditable(true);
		ColEmail.setCellFactory(cellFactory);
		ColEmail.setOnEditCommit(new EventHandler<CellEditEvent<Customer, String>>() {
			@Override
			public void handle(CellEditEvent<Customer, String> t) {
				((Customer) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			}
		});
	}

//
	class EditingCell extends TableCell<Customer, String> {

		private TextField textField;

		public EditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText((String) getItem());
			setGraphic(null);
		}

		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					if (!arg2) {
						commitEdit(textField.getText());
					}
				}
			});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

}

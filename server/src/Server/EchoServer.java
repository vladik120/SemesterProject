package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gui.ServerConnectionController;
import Logic.ClientConnection;
import Logic.Customer;
import ocsf.server.*;

public class EchoServer extends AbstractServer {

	/**
	 * list of customers
	 */
	private ArrayList<Customer> list;
	/**
	 * list of client that connect to server
	 */
	private ArrayList<ClientConnection> client = null;

	public EchoServer(int port) {
		super(port);
	}

	/**
	 * receive a message from client and parsing it
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ClientConnection newClient = new ClientConnection(client);

		if (this.client == null) {
			this.client = new ArrayList<ClientConnection>();
			this.client.add(newClient);
		} else {
			for (int i = 0; i < this.client.size(); i++) {
				if (!this.client.get(i).getHostName().equals(newClient.getHostName()))
					this.client.add(newClient);
				else
					this.client.get(i).setStatus("Connected");
			}
		}
		if (msg.equals("exit"))
			for (int i = 0; i < this.client.size(); i++)
				if (this.client.get(i).getHostName().equals(newClient.getHostName()))
					this.client.get(i).setStatus("Disconnected");

		ServerConnectionController.Sc.Update(this.client);

		if (msg.equals("show") && SetTableData())
			sendTable();

		String[] strings = msg.toString().split("---");
		if (strings[0].equals("update"))
			UpdateTable(strings[1]);
	}

	/**
	 * convert to string and sends the customer list to client
	 */
	private void sendTable() {
		String dataStrings = "";
		for (int i = 0; i < list.size(); i++) {
			dataStrings += list.get(i).toString();
			if (i < list.size() - 1)
				dataStrings += ",";
		}
		System.out.println("--> sending to clientUI --> " + dataStrings);
		this.sendToAllClients(dataStrings);
	}

	/**
	 * convert to arrayList<customer> the string from client and update the DataBase
	 * 
	 * @param msg - the string from client
	 * @functionIn -UpdateDB() update the DataBase
	 */
	private void UpdateTable(String msg) {
		System.out.println("--> UpdateTable");
		String[] firstSplit = msg.split(",");
		String[] SecondSplit = null;
		for (int i = 0; i < firstSplit.length; i++) {
			SecondSplit = firstSplit[i].split("\\s");
			list.add(new Customer(SecondSplit[0], SecondSplit[1], SecondSplit[2], SecondSplit[3], SecondSplit[4]));
		}
		this.sendToAllClients("END");
		if (UpdateDB()) {
			System.out.println("--> UpdateTable --> Successfully update the table");
			this.sendToAllClients("END");
		} else
			System.out.println("--> UpdateTable --> Unsuccessfully update the table");
	}

	
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	/**connect to DataBase in MYSQL and update it
	 * @return true - if successful , false - if unsuccessful
	 */
	private boolean UpdateDB() {
		System.out.println("--> UpdateDB ");
		Connection connection = ConnectToDB();
		Statement StatementOfQuery;
		try {
			StatementOfQuery = connection.createStatement();
			for (int i = 0; i < list.size(); i++) {
				StatementOfQuery.executeUpdate("UPDATE Visitor SET Email =\"" + list.get(i).getEmail()
						+ "\"WHERE ID = \"" + list.get(i).getId() + "\";");
			}
			System.out.println("the DataBase was secssesfully update\n");
			return true;
		} catch (SQLException e) {
			System.out.println("the DataBase wasn`t update\n");
			e.printStackTrace();
		}
		return false;
	}

	/**set the ArrayList<Customer> list with information from DataBase
	 *  @return true - if successful , false - if unsuccessful
	 */
	private boolean SetTableData() {
		Connection connection = ConnectToDB();
		Customer tempCustomer;
		Statement StatementOfResultSet;
		list = new ArrayList<Customer>();
		ResultSet Resultset;
		try {
			StatementOfResultSet = connection.createStatement();
			Resultset = StatementOfResultSet.executeQuery("SELECT * FROM visitor;");
			while (Resultset.next()) {
				tempCustomer = new Customer(Resultset.getString(1), Resultset.getString(2), Resultset.getString(3),
						Resultset.getString(4), Resultset.getString(5));
				list.add(tempCustomer);
			}
			System.out.println("--> Set table in Data Base");
			Resultset.close();
			return true;
		} catch (SQLException e) {
			System.out.println("-->ERROR--> Set table in Data Base");
			e.printStackTrace();
		}
		return false;
	}

	/**connects to MYSQL
	 * @return if successful - Connection , if unsuccessful - null
	 */
	private Connection ConnectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("--> Driver definition succeed");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("--> Driver definition failed");
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gonature?serverTimezone=IST", "root",
					"Vladik120");
			System.out.println("--> SQL connection succeed");
			return con;
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("--> SQLException: " + ex.getMessage());
			System.out.println("--> SQLState: " + ex.getSQLState());
			System.out.println("--> VendorError: " + ex.getErrorCode());
		}
		return null;
	}
}

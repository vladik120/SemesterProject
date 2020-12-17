
package client;

import ocsf.client.*;
import common.ChatIF;
import logic.Customer;

import java.io.*;
import java.util.ArrayList;

public class ChatClient extends AbstractClient {

	public static ArrayList<Customer> list = new ArrayList<>();

	ChatIF clientUI;
	public static boolean awaitResponse = false;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;

	}

	public void handleMessageFromServer(Object msg) {
		if (msg.equals("END"))
			awaitResponse = false;
		else {
			System.out.println(msg.toString());
			awaitResponse = false;
			String Originalmsg = msg.toString();
			String[] firstSplit;
			String[] secondSplit;
			Customer customer;
			if (Originalmsg.isEmpty()) {
				System.out.println("--> handleMessageFromServer --> the msg is empty");
				return;
			}
			firstSplit = Originalmsg.split(",");
			for (int i = 0; i < firstSplit.length; i++) {
				secondSplit = firstSplit[i].split("\\s");
				customer = new Customer(secondSplit[0], secondSplit[1], secondSplit[2], secondSplit[3], secondSplit[4]);
				list.add(customer);
			}
		}
	}

	public void handleMessageFromClientUI(String message) {
		try {
			if (message.equals("exit")) {
				System.out.println("--> Update the connection");
				try {
					openConnection();
					sendToServer(message);
				} catch (Exception e) {
					System.out.println("--> The server is shotdown");
				}
				quit();
			}
			System.out.println("--> handleMessageFromClientUI --> message: " + message);
			openConnection();
			awaitResponse = true;
			sendToServer(message);
			while (awaitResponse) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("--> Could not send message to server: Terminating client." + e);
			quit();
		}

	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}

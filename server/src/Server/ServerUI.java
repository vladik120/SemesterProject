package Server;

import gui.ServerPortController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {

	final public static int DEFAULT_PORT = 5555;

	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerPortController aFrame = new ServerPortController();
		aFrame.start(primaryStage);
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	/**
	 * start the server with the port "userport"
	 * @param userport
	 * @throws ERROR - Could not connect --> if couldn't connect
	 * @throws ERROR - Could not listen for clients --> if couldn't listen
	 */
	public static void runServer(String userport) {
		int port = 0;
		try {
			port = Integer.parseInt(userport);
			
		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
		}
		EchoServer sv = new EchoServer(port);
		try {
			sv.listen();
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}

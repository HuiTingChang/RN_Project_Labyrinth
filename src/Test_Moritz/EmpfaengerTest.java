package Test_Moritz;

import generated.ErrorType;
import generated.MazeCom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Board;

import networking.Connection;
import networking.MazeComMessageFactory;

public class EmpfaengerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MazeComMessageFactory mcmf = new MazeComMessageFactory();
			ServerSocket s = new ServerSocket(5001);
			Socket c = s.accept();
			Connection con = new Connection(c);
			
			System.out.println("========================================");
			System.out.println("Empfange Login und sende Reply");
			con.login(1);
			System.out.println("========================================");
			System.out.println("Sende Win");
			con.sendMessage(mcmf.createWinMessage(1, 99, "TestWinner", new Board()));
			System.out.println("========================================");
			System.out.println("Sende Error");
			con.sendMessage(mcmf.createErrorMessage(1, ErrorType.ERROR));
			System.out.println("========================================");
			System.out.println("Sende Disconnect");
			con.sendMessage(mcmf.createDisconnectMessage(1, "Test"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

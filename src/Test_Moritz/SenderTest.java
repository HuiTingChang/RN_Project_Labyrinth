package Test_Moritz;

import generated.ErrorType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Board;

import networking.Connection;
import networking.MazeComMessageFactory;

public class SenderTest {
	public static void main(String[] args) {
		try {
			MazeComMessageFactory mcmf = new MazeComMessageFactory();
			Socket c = new Socket("localhost", 5001);
			Connection con = new Connection(c);
			System.out.println("========================================");
			System.out.println("Sende Login");
			con.sendMessage(mcmf.createLoginMessage("Test"));
			System.out.println("========================================");
			System.out.println("Empfange Reply");
			con.receiveMessage();
			System.out.println("========================================");
			System.out.println("Empfange Win");
			con.receiveMessage();
			System.out.println("========================================");
			System.out.println("Empfange Error");
			con.receiveMessage();
			System.out.println("========================================");
			System.out.println("Empfange Disconnect");
			con.receiveMessage();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

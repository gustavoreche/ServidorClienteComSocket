package br.com.threads.socket.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import br.com.threads.socket.servidor.ServidorSocket;

public class ClienteSocket {
	
	private PrintStream respostaDoCliente;
	
	public void inicia() {
		Socket clienteSocket = null;
		try {
			clienteSocket = new Socket(ServidorSocket.IP_SOCKET, ServidorSocket.PORTA_SOCKET);
			System.out.println("Cliente conectado! Porta: " + clienteSocket.getLocalPort());
			executaRespostaDoCliente(clienteSocket);
		} catch (Exception e) {
			System.err.println("ClienteSocket - inicia - Exception: " + e);
		} finally {
			fechaConexao(clienteSocket);
		}
	}

	private void executaRespostaDoCliente(Socket clienteSocket) throws IOException {
		try {
			this.respostaDoCliente = new PrintStream(clienteSocket.getOutputStream());
			this.respostaDoCliente.println("Resposta do cliente");
			Thread.sleep(5000);			
		} catch (InterruptedException e) {
			System.err.println("ClienteSocket - executaRespostaDoCliente - Exception: " + e);
		}
	}

	private void fechaConexao(Socket clienteSocket) {
		if(clienteSocket != null) {
			try {
				clienteSocket.close();
				this.respostaDoCliente.close();
				System.out.println("Cliente desconectado! Porta: " + clienteSocket.getLocalPort());
			} catch (IOException e) {
				System.err.println("ClienteSocket - fechaConexao - Exception: " + e);
			}			
		}
	}

}

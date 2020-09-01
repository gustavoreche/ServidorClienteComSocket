package br.com.threads.socket.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import br.com.threads.socket.servidor.ServidorSocket;

public class ClienteSocket {
	
	private Scanner digitacaoDoCliente;
	private PrintStream respostaDoCliente;
	
	public void inicia() {
		Socket clienteSocket = null;
		try {
			clienteSocket = new Socket(ServidorSocket.IP_SOCKET, ServidorSocket.PORTA_SOCKET);
			System.out.println("Cliente conectado! Porta: " + clienteSocket.getLocalPort());
			executaRespostaDoCliente(clienteSocket);
		} catch (Exception e) {
			System.err.println("O servidor NÃO está ONLINE");
		} finally {
			fechaConexao(clienteSocket);
		}
	}

	private void executaRespostaDoCliente(Socket clienteSocket) {
		try {
			digitacaoDoUsuario(clienteSocket);
		} catch (Exception e) {
			System.err.println("ClienteSocket - executaRespostaDoCliente - Exception: " + e);
		}
	}

	private void digitacaoDoUsuario(Socket clienteSocket) throws IOException {
		this.digitacaoDoCliente = new Scanner(System.in);
		while(this.digitacaoDoCliente.hasNextLine()) {
			this.respostaDoCliente = new PrintStream(clienteSocket.getOutputStream());
			String usuarioInteragindo = this.digitacaoDoCliente.nextLine();
			if(usuarioInteragindo.equals("")) {
				break;
			}
			this.respostaDoCliente.println(usuarioInteragindo);
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

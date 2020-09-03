package br.com.threads.socket.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ThreadEnviaDadosAoServidor implements Runnable {

	private Socket conectadoNoServidor;
	
	public ThreadEnviaDadosAoServidor(Socket conectadoNoServidor) {
		this.conectadoNoServidor = conectadoNoServidor;
	}
	
	@Override
	public void run() {
		try(Scanner digitacaoDoCliente = new Scanner(System.in)){
			while(digitacaoDoCliente.hasNextLine()) {
				PrintStream respostaDoCliente = new PrintStream(this.conectadoNoServidor.getOutputStream());
				String usuarioInteragindo = digitacaoDoCliente.nextLine();
				if(desejaSairDoSistema(usuarioInteragindo))
					break;
				respostaDoCliente.println(usuarioInteragindo);
			}
		} catch (IOException e) {
			System.err.println("ThreadEnviaDadosAoServidor - run - Exception: " + e);
		}
	}

	private boolean desejaSairDoSistema(String usuarioInteragindo) {
		return usuarioInteragindo.equals("");
	}
	
}

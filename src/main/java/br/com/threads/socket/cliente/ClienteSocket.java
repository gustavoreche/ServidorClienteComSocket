package br.com.threads.socket.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.threads.socket.servidor.ServidorSocket;

public class ClienteSocket {
	
	public void inicia() {
		try(Socket conectadoNoServidor = new Socket(ServidorSocket.IP_SOCKET, ServidorSocket.PORTA_SOCKET)){
			System.out.println("Cliente conectado! Porta: " + conectadoNoServidor.getLocalPort());
			executaTarefaDoCliente(conectadoNoServidor);
		}
		catch (UnknownHostException e) {
			System.err.println("ClienteSocket - inicia - Exception: " + e);
		} catch (IOException e) {
			System.err.println("ClienteSocket - inicia - Exception: " + e);
		}
	}

	private void executaTarefaDoCliente(Socket conectadoNoServidor) {
		try {
			Thread threadEnviaDados = new Thread(new ThreadEnviaDadosAoServidor(conectadoNoServidor));
			new Thread(new ThreadRecebeDadosDoServidor(conectadoNoServidor)).start();
			threadEnviaDados.start();
			threadEnviaDados.join();
		} catch (Exception e) {
			System.err.println("ClienteSocket - executaTarefaDoCliente - Exception: " + e);
		} finally {
			System.out.println("Cliente desconectado! Porta: " + conectadoNoServidor.getLocalPort());
		}
	}
	
}

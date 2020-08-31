package br.com.threads.socket.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.threads.service.DistribuidorDeTarefa;

public class ServidorSocket {
	
	public static final int PORTA_SOCKET = 12345;
	public static final String IP_SOCKET = "localhost";
	
	private ExecutorService configuraThreads;
	
	public void inicia() {	
		try {
			System.out.println("======= Iniciou servidor socket =======");
			ServerSocket servidorSocket = new ServerSocket(PORTA_SOCKET);
			this.configuraThreads = Executors.newCachedThreadPool();
			mantemServidorAtivo(servidorSocket);
		} catch (Exception e) {
			System.err.println("ServidorSocket - inicia - Exception: " + e);
		}		
	}

	private void mantemServidorAtivo(ServerSocket servidorSocket) throws IOException, InterruptedException {
		while(true) {
			aceitaNovoCliente(servidorSocket);
		}
	}

	private void aceitaNovoCliente(ServerSocket servidorSocket) throws IOException {
		Socket clienteConectado = servidorSocket.accept();
		System.out.println("Novo cliente conectado no servidor! Porta: " + clienteConectado.getPort());
		DistribuidorDeTarefa distribuidorDeTarefa = new DistribuidorDeTarefa(clienteConectado);
		this.configuraThreads.execute(distribuidorDeTarefa);
	}

}

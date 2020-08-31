package br.com.threads.main;

import br.com.threads.socket.servidor.ServidorSocket;

public class MainServidor {
	
	public static void main(String[] args) {
		new ServidorSocket().inicia(); 
	}

}

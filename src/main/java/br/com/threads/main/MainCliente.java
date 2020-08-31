package br.com.threads.main;

import br.com.threads.socket.cliente.ClienteSocket;

public class MainCliente {
	
	public static void main(String[] args) {
		new ClienteSocket().inicia();		
	}

}

package br.com.threads.socket.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadRecebeDadosDoServidor implements Runnable {
	
	private Socket conectadoNoServidor;
	
	public ThreadRecebeDadosDoServidor(Socket conectadoNoServidor) {
		this.conectadoNoServidor = conectadoNoServidor;
	}

	@Override
	public void run() {
		try(Scanner respostaDoServidor = new Scanner(this.conectadoNoServidor.getInputStream())){
			while(respostaDoServidor.hasNextLine()) {
				System.out.println(respostaDoServidor.nextLine());
			}
		} catch (IOException e) {
			System.err.println("ThreadRecebeDadosDoServidor - run - Exception: " + e);
		}
	}

}

package br.com.threads.socket.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuidorDeTarefaDoServidor implements Runnable {
	
	private Socket clienteConectado;
	private PrintStream servidorRespondendo;
	
	private static final String RESPOSTA_DO_SERVIDOR_OK = "Comando recebido no servidor!";

	public DistribuidorDeTarefaDoServidor(Socket clienteConectado) {
		this.clienteConectado = clienteConectado;
	}
	
	@Override
	public void run() {
		simulaTarefaQueOClienteFazNoServidor();
	}
	
	private void simulaTarefaQueOClienteFazNoServidor() {
		try {
			leRespostaDoCliente();
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			System.err.println("DistribuidorDeTarefa - simulaTarefaQueOClienteFazNoServidor - Exception: " + e);
		}
	}

	private void leRespostaDoCliente() {
		try(Scanner respostaDoCliente = new Scanner(this.clienteConectado.getInputStream())){
			while(respostaDoCliente.hasNextLine()) {
				System.out.println("Cliente da porta " + this.clienteConectado.getPort() + 
						": " + respostaDoCliente.nextLine());
				this.servidorRespondendo = new PrintStream(this.clienteConectado.getOutputStream());
				this.servidorRespondendo.println(RESPOSTA_DO_SERVIDOR_OK);
			}
		} catch (IOException e) {
			System.err.println("DistribuidorDeTarefa - leRespostaDoCliente - Exception: " + e);
		} 
	}

}

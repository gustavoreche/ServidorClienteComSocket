package br.com.threads.service;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuidorDeTarefaDoServidor implements Runnable {
	
	private Socket clienteConectado;

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
		Scanner respostaDoCliente = null;
		try {
			respostaDoCliente = new Scanner(this.clienteConectado.getInputStream());
			while(respostaDoCliente.hasNextLine()) {
				System.out.println("Usuário da porta " + this.clienteConectado.getPort() + 
						": " + respostaDoCliente.nextLine());
			}
		} catch (IOException e) {
			System.err.println("DistribuidorDeTarefa - leRespostaDoCliente - Exception: " + e);
		} finally {
			fechaConexao(respostaDoCliente);
		}
	}

	private void fechaConexao(Scanner respostaDoCliente) {
		try {
			if(respostaDoCliente != null) {
				respostaDoCliente.close();
				this.clienteConectado.close();
			}			
		} catch (IOException e) {
			System.err.println("DistribuidorDeTarefa - fechaConexao - Exception: " + e);
		}
	}

}

package br.com.threads.service;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuidorDeTarefa implements Runnable {
	
	private Socket clienteConectado;

	public DistribuidorDeTarefa(Socket clienteConectado) {
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
				System.out.println(respostaDoCliente.nextLine());
			}
			System.out.println("----------------------------------");
		} catch (IOException e) {
			System.err.println("DistribuidorDeTarefa - leRespostaDoCliente - Exception: " + e);
		} finally {
			fechaConexao(respostaDoCliente);
		}
	}

	private void fechaConexao(Scanner respostaDoCliente) {
		if(respostaDoCliente != null)
			respostaDoCliente.close();
	}

}

package com.wilbur.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

	public static void main(String[] args) throws Exception {

		// PARTE UM DEFININDO PARAMETROS INICIAIS
		// recebe cada linha lida do arquivo texto
		String curLine;

		// mapa: Palavra -> Frequencia
		// usado para contabilizar as
		// frequencias das palavras
		Map<String, Integer> mapPalavras = new HashMap<String, Integer>();

		/********************************************************/
		/********************************************************/

		// PARTE DOIS ABRINDO ARQUIVO DE TEXTO
		// abre o arquivo
		FileReader txtFile = new FileReader("texts.txt");
		BufferedReader txtBuffer = new BufferedReader(txtFile);
		
		/********************************************************/
		/********************************************************/

		// PARTE TRES PERCORRENDO ARQUIVO
		// loop que processa cada linha do arquivo texto
		// pega a primeira linha do arquivo
		curLine = txtBuffer.readLine();

		while (curLine != null) {
			// quebra a linha em tokens (palavras) utilizando
			// expressão regular
			// primeiro converte tudo para minúsculo
			String minusculo = curLine.toLowerCase();

			//buscar por tipo REGEX palavras dentro do pipe "|"
			Pattern p  = Pattern.compile("([^|]+)");
			Matcher m = p.matcher(minusculo);

			// neste loop pegamos cada palavra
			// e atualizamos o mapa de frequências
			while (m.find()) {
				
				//FAZER LOGICA DE SE TIVER ESPAÇO PEGAR PALAVRAS E ESPAÇO
				//E ARMAZENAR NO TOKEN
				
				// pega um token
				String token = m.group();
				// verifica se esse
				// token já está no mapa
				Integer freq = mapPalavras.get(token);
		
				if (freq != null) {
					// se palavra existe, atualiza a frequencia
					mapPalavras.put(token, freq + 1);
				} else {
					// se palavra não existe, insiro com um novo id e freq=1.
					mapPalavras.put(token, 1);
				}
				
			}

			// pega a próxima linha do arquivo
			curLine = txtBuffer.readLine();
		}

		txtBuffer.close();

		/********************************************************/
		/********************************************************/

		// PARTE FINAL IMPRIMINDO O MAPA DE FREQUENCIAS
		for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
			System.out.println("Wilbur says - \n\tPalavra: " + entry.getKey() + " \n\tfreqencia = " + entry.getValue() + "\n");
		}

	}

}

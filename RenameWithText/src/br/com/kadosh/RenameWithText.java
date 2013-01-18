package br.com.kadosh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class RenameWithText {
	
	public void execute(String pathArqText, String pathArqu) {
		File textFile = new File(pathArqText);
		File directoryFile = new File(pathArqu);
		ArrayList<String> lineArray = new ArrayList<String>(); 
		
		if(directoryFile.isDirectory()){
			listAllFiles(directoryFile);
			lineArray = readTextFile(textFile);
			doRename(lineArray, directoryFile);
		}else{
			System.out.println("Favor informar um diretório para busca dos arquivos");
		}
	}
	
	private void listAllFiles(File directoryFile) {
		try {
			if (directoryFile.isDirectory() && directoryFile.canRead()) {
				File[] renameFile = directoryFile.listFiles();
				for (int i = 0; i < renameFile.length; i++) {
					System.out.println(renameFile[i].getName());
				}
			} else {
				System.out.println("Diretório inválido ou sem permissão");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private ArrayList<String> readTextFile(File textFile) {
		ArrayList<String> lineArray = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(textFile);
			BufferedReader buffer = new BufferedReader(reader);
			
			while (buffer.ready()) {
				lineArray.add(buffer.readLine());
			}
			buffer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return lineArray;
	}
	
	private boolean doRename(ArrayList<String> lineArray, File directoryFile) {
		try {
			File[] files = directoryFile.listFiles();
			if(lineArray.size() != files.length){
				System.out.println("Diferença de tamanho entre o arquivo texto e os arquivos no diretório");
			}else{
				for(int i=0;i<lineArray.size();i++){
					if(!files[i].isDirectory()){
						File newName = new File(directoryFile.getPath()+'\\'+lineArray.get(i));
						boolean ok = false;
						while (!ok) {
							ok = files[i].renameTo(newName);
						}
						
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}

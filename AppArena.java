import java.util.Random;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class AppArena {
	static Boolean flagfinal = true;
	static int qntdrounds;
	Personagem[] personagens;
	Random sort = new Random();
	Integer x;
	Grafico graph = new Grafico();
	static String csvFilePath2;
	public static void main(String[] args)throws IOException, InterruptedException  {
		String filePath1;
		String filePath2;
		// filePath = "arquivoArena.csv";
		JFileChooser fc1 = new JFileChooser(System.getProperty("user.dir"));
		fc1.showOpenDialog(null);
		File file1 = fc1.getSelectedFile();
		filePath1 = file1.getAbsolutePath();
		JFileChooser fc2 = new JFileChooser(System.getProperty("user.dir"));
		fc2.showOpenDialog(null);
		File file2 = fc2.getSelectedFile();
		filePath2 = file2.getAbsolutePath();
		Personagem.flag = false;//false para não mostrar os ataques e defesas e true para mostrar os ataques e defesas
		Personagem.flag2 = false; //false para desativar os desenhos, true para permitir
		csvFilePath2 = filePath2;
		for(int i=0; i<10; i++){
			AppArena arena = new AppArena(filePath1);
			arena.iniciarCombates();
		}
		AppArena arena = new AppArena(filePath2);
		flagfinal = false;
		arena.iniciarCombates();
		System.out.println("O campeão está acima...");
		int k = 0;
		if(k == 1){
			String line = "";
			String all = "";
			try{
				BufferedReader br = new BufferedReader(new FileReader(csvFilePath2));
				Boolean flagfirst = true;
				while ((line = br.readLine()) != null){
					if(flagfirst)
						all = all+line;
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public AppArena(int qtdPersonagens){
		personagens = new Personagem[qtdPersonagens];
		for (int i = 0; i < personagens.length; i++) {
			x = sort.nextInt(29);
			if(x >= 0 && x<=5){
				personagens[i] = new Lutador();
			}
			else if(x >= 6 && x<=8){
				personagens[i] = new Lutador("Lutador Especial");
			}
			else if(x>=9 && x<=16){
				personagens[i] = new Fera();
			}
			else if(x==17){
				personagens[i] = new Fera("Fera Especial");
			}
			else if(x>=18 && x<20){
				personagens[i] = new Gladiador();
			}
			else if(x >= 20 && x<=21){
				personagens[i] = new Gladiador("Guarda Real","Espada de Berhyl", "Ataque Direto", 35.0, 40.0);
			}
			else if(x==22){
				personagens[i] = new Gladiador("Espadachin Lendário", "Espada Sagrada de Fogo", "Ataque Infernal", 48.0, 90.0);
			}
			else if(x==23){
				personagens[i] = new Judoca();
			}
			else if(x==25){
				personagens[i] = new JiuJitero();
			}
			else if(x==26){
				personagens[i] = new Karateca();
			}
			else if(x==27){
				personagens[i] = new UfcFighter();
			}
			else{
				CrossbowCBayoneta arma1 = new CrossbowCBayoneta();
				arma1.set_description("Crossbow");
				Armadura protection = new Armadura();
				protection.setEstado_de_conservação(35.0);
				protection.setPoder_de_defesa(55.0);
				personagens[i] = new Gladiador("Arqueiro", arma1, protection);
			}
		}
	}
	
	public AppArena(Personagem[] personagens) {
		super();
		this.personagens = personagens;
	}

	public AppArena(String csvFilePath) {
		qntdrounds ++;
		ArrayList<Personagem> personagens = new ArrayList<Personagem>(); 
		String line = "";
		String splitBy = ";";
		try {
			//parsing a CSV file into BufferedReader class constructor  
			//BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dell\\Desktop\\csvDemo.csv"));
			BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
			String nameant = null;
			String armaant = null;
			int indexant = 0;
			while ((line = br.readLine()) != null){
				String[] data = line.split(splitBy);
				if(data[1].equalsIgnoreCase(nameant) && data[0].equalsIgnoreCase("Gladiador")){
					Arma arma1 = new Arma();
					Armadura protection = new Armadura();
					Golpes iniGolpe = new Golpes();
					int i = personagens.size() - 1;
					Gladiador aux =(Gladiador)(personagens.get(i));
				if(data[3] != ""){
						if(data[3].equalsIgnoreCase(armaant)){
							iniGolpe.set_nome_do_golpe(data[4]);
							iniGolpe.set_poder_ofensivo(Double.valueOf(data[5]).doubleValue());
							aux.get_armas(indexant).set_ataques(iniGolpe);
						}
						else{
							iniGolpe.set_nome_do_golpe(data[4]);
							iniGolpe.set_poder_ofensivo(Double.valueOf(data[5]).doubleValue());
							arma1.set_description(data[3]);
							arma1.set_ataques(iniGolpe);
							aux.set_armas(arma1);
							armaant = data[3];
							indexant = aux.sizearma()-1;
						}
					}
				if(data.length > 6){
					if(data[6] != ""){
						protection.set_descricaoarmadura(data[6]);
						protection.setPoder_de_defesa(Double.valueOf(data[7]).doubleValue());
						protection.setEstado_de_conservação(Double.valueOf(data[8]).doubleValue());
						aux.set_armaduras(protection);
					}
				}
				}
				else if(data[0].equalsIgnoreCase("Fera")) {
					Personagem fera = new Fera(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(fera);
				}
				else if(data[0].equalsIgnoreCase("Lutador")){
					Personagem lutador = new Lutador(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(lutador);
				}
				else if(data[0].equalsIgnoreCase("Jiujitero")){
					Personagem jiujitero = new JiuJitero(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(jiujitero);
				}
				else if(data[0].equalsIgnoreCase("Judoca")){
					Personagem judoca = new Judoca(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(judoca);	
				}
				else if(data[0].equalsIgnoreCase("Karateca")){
					Personagem karateca = new Karateca(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(karateca);
				}
				else if(data[0].equalsIgnoreCase("UfcFighter")){
					Personagem ufcfighter = new UfcFighter(data[1], Double.valueOf(data[2]).doubleValue());
					personagens.add(ufcfighter);
				}
				else if(data[0].equalsIgnoreCase("Gladiador")){
					Gladiador gladiador = new Gladiador(data[1], Double.valueOf(data[2]).doubleValue());
					Arma arma1 = new Arma();
					Armadura protection = new Armadura();
					Golpes iniGolpe = new Golpes();
					if(data[3] != ""){
						iniGolpe.set_nome_do_golpe(data[4]);
						iniGolpe.set_poder_ofensivo(Double.valueOf(data[5]).doubleValue());
						arma1.set_description(data[3]);
						arma1.set_ataques(iniGolpe);
						gladiador.set_armas(arma1);
						indexant = 0;
						armaant = data[3];
					}
					if(data.length > 6){
						if(data[6] != ""){
							protection.set_descricaoarmadura(data[6]);
							protection.setPoder_de_defesa(Double.valueOf(data[7]).doubleValue());
							protection.setEstado_de_conservação(Double.valueOf(data[8]).doubleValue());
							gladiador.set_armaduras(protection);
						}
				}
					personagens.add(gladiador);
				}
				nameant = data[1];
			}
			this.personagens = new Personagem[personagens.size()];
			personagens.toArray(this.personagens); //converte arraylist para vetor.
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void adicionarcampeoes(Personagem campeao, String CsvFilePath2){
		String line = "";
		String splitBy = ";";
		String all = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(csvFilePath2));
			Boolean flagfirst = true;
			while ((line = br.readLine()) != null){
				if(flagfirst)
					all = all+line;
				else
					all = all+"\n"+line;
				flagfirst = false;
			}
			int loop = 1;
			int j = 0;
			int i = 0;
			if(campeao instanceof Gladiador){
				campeao.nivel_de_energia = 200.0;
				i = ((Gladiador)campeao).sizearma();
				j = ((Gladiador)campeao).sizearmadura();
				loop = (i > j)?i:j;
				if(j == 0  && i == 0)
					loop = 1;
			}
			for(int x = 0; x<loop; x++){
				if(campeao instanceof Gladiador){
					if(x<i){
						if(x<j){
							for(int y = 0; y<((Gladiador)campeao).get_armas(x).sizeataques(); y++)
								all = all+"\n"+"Gladiador"+";"+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+((Gladiador)campeao).get_armas(x).get_description()+";"+((Gladiador)campeao).get_armas(x).get_ataques(y).get_nome_do_golpe()+";"+((Gladiador)campeao).get_armas(x).get_ataques(y).get_poder_ofensivo()+";"+";"+";";
							all = all+"\n"+"Gladiador"+";"+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+";"+";"+";"+((Gladiador)campeao).get_armaduras(x).get_descricaoarmadura()+";"+((Gladiador)campeao).get_armaduras(x).getPoder_de_defesa()+";"+((Gladiador)campeao).get_armaduras(x).getEstado_de_conservação();
						}
						else{
							for(int y = 0; y<((Gladiador)campeao).get_armas(x).sizeataques(); y++)
								all = all+"\n"+"Gladiador"+";"+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+((Gladiador)campeao).get_armas(x).get_description()+";"+((Gladiador)campeao).get_armas(x).get_ataques(y).get_nome_do_golpe()+";"+((Gladiador)campeao).get_armas(x).get_ataques(y).get_poder_ofensivo()+";"+";"+";";
						}
					}
					else{
						if(x<j){
							all = all+"\n"+"Gladiador"+";"+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+";"+";"+";"+((Gladiador)campeao).get_armaduras(x).get_descricaoarmadura()+";"+((Gladiador)campeao).get_armaduras(x).getPoder_de_defesa()+";"+((Gladiador)campeao).get_armaduras(x).getEstado_de_conservação();
						}
						else{
							all = all+"\n"+"Gladiador"+";"+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+";"+";"+";"+";"+";";
						}
					}
				}
				else{
					String tipo = "";
					if(campeao instanceof Fera){
						tipo = "Fera;";
						campeao.nivel_de_energia = 1000.0;
					}
					if(campeao instanceof Lutador){
						tipo = "Lutador;";
						if(campeao.nivel_de_energia < 200.0)
							campeao.nivel_de_energia = 200.0;
					}
					if(campeao instanceof JiuJitero){
						campeao.nivel_de_energia = 200.0;
						tipo = "JiuJitero;";
					}
					if(campeao instanceof Judoca){
						campeao.nivel_de_energia = 200.0;
						tipo = "Judoca;";
					}
					if(campeao instanceof Karateca){
						campeao.nivel_de_energia = 200.0;
						tipo = "Karateca;";
					}
					if(campeao instanceof UfcFighter){
						campeao.nivel_de_energia = 200.0;
						tipo = "UfcFighter;";
					}
					all = all+"\n"+tipo+campeao.get_name()+";"+campeao.get_nivel_de_energia()+";"+";"+";"+";"+";"+";";
				}
			}
			Path fileName = Path.of(csvFilePath2);
			Files.writeString(fileName, all);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getQtdPersonagemVivos() {
		int retorno = 0;
		for (int i = 0; i < personagens.length; i++) {
			retorno += personagens[i].estavivo()? 1:0;
		}
		return retorno;
	}
	
	public int getIndiceProximoVivo(int index) {
		int i = index;
		while (i != (index -1)) {
			if(i > (personagens.length - 1)) 
				i = 0;
			if(personagens[i].estavivo()) return i;
			i++;
		}
		return -1;
	}
	
	private void realizarCombate(Personagem p1, Personagem p2) {
		if(Personagem.flag2){
			try {
				new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("\n\n\n\n\n\n                                                              O COMBATE JA VAI COMEÇAR\n\n                                                     "+p1+" VS."+p2+"\n\n\n\n\n\n");
			try{
				Thread.sleep(2500);                 //1500 milliseconds is one second.
			} 
			catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			try {
				new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println((Personagem.flag?"\n":"")+"Combate em andamento:"+p1+" vs. "+p2);
		while (p1.estavivo() && p2.estavivo()) {
			if(Personagem.flag2)
				System.out.print("\n                                             Lado Esquerdo Atacando->");
			p1.atacarpersonagem(p2);
			if(Personagem.flag)
				System.out.print("| "+p1.get_name()+"(Atacando)= "+(Math.round(p1.get_nivel_de_energia())>0?(Math.round(p1.get_nivel_de_energia())):0)+"|"+p2.get_name()+"="+(Math.round(p2.get_nivel_de_energia())>0?(Math.round(p2.get_nivel_de_energia())):0)+"\n");
			if(Personagem.flag2){
				System.out.print("\n");
				graph.barradevida(p1, p2);
				try{
					Thread.sleep(100);                 //1500 milliseconds is one second.
				} 
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				try {
					new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
				  } 
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(p2.estavivo()) {
				if(Personagem.flag2)
					System.out.print("\n                                              Lado Direito Atacando->");
				p2.atacarpersonagem(p1);
				if(Personagem.flag)
					System.out.print("| "+p1.get_name()+"= "+(Math.round(p1.get_nivel_de_energia())>0?(Math.round(p1.get_nivel_de_energia())):0)+"|"+p2.get_name()+"(Atacando)="+(Math.round(p2.get_nivel_de_energia())>0?(Math.round(p2.get_nivel_de_energia())):0)+"\n");
				if(Personagem.flag2){
					System.out.print("\n");
					graph.barradevida(p1, p2);
					try{
						Thread.sleep(100);                 //1500 milliseconds is one second.
					} 
					catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					try {
						new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
						} 
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
						
		}
		Personagem vencedor= p1.estavivo()?p1:p2;
		Personagem perdedor= p1.estavivo()?p2:p1;
		if(vencedor instanceof Gladiador && perdedor instanceof Gladiador){
			((Gladiador)vencedor).roubaritens((Gladiador)perdedor);
		}
		if(Personagem.flag2){
			try {
				new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("\n\n\n\n\n\n\n\n                                                                COMBATE ENCERRADO!\n\n"+"                                                     VENCEDOR: "+vencedor+"\n\n\n\n\n\n");
			try{
				Thread.sleep(2500);                 //1500 milliseconds is one second.
			} 
			catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
		}
		else
			System.out.println((Personagem.flag?"\n":"")+"Combate encerrado! Vencedor:" + vencedor);
	}
 	
	private void listarCombatentes() {
		System.out.println("Combatentes:");
		for (int i = 0; i < personagens.length; i++) {
			System.out.println(" "+ i + ": " + personagens[i]);
		}
	}
	
	private Personagem getCampeao() {
		for (int i = 0; i < personagens.length; i++) {
			if(personagens[i].estavivo()) return personagens[i];
		}
		return null;
	}
	
	public void iniciarCombates() {
		listarCombatentes();
		int index = 0;
		while (getQtdPersonagemVivos() > 1) {
			int posP1 = getIndiceProximoVivo(index);
			Personagem p1 = personagens[posP1];
			index = posP1 + 1;
				
			int posP2 = getIndiceProximoVivo(index);	
			
			if(posP2 != -1) {
				Personagem p2 = personagens[posP2];
				index = posP2+1;
				realizarCombate(p1,p2);				
			}
			
		
			
			//index = index==personagens.length?0:index+1;
			
		}
		if(Personagem.flag2){
			try {
				new ProcessBuilder("clear").inheritIO().start().waitFor();	// Para linux (acho que para mac também)
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("\n\n\n\n\n\n\n\n                                                                Fim dos Combates. Campeão do torneio:"+getCampeao()+"\n\n\n\n\n\n");
		}
		else
			System.out.println("Fim dos Combates. Campeão do torneio:" + getCampeao());
		getCampeao().name += qntdrounds;
		if(flagfinal)
			adicionarcampeoes(getCampeao(), csvFilePath2);
		
	}

}
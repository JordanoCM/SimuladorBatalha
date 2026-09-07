import java.util.Random;
public class Judoca extends Lutador implements Judo {
    static Integer sequencialname = 1;
    Integer x;
    Random sort = new Random();
	public Judoca(String nome) {
		super(nome);
	}
    public Judoca(String nome, Double nivelenergia) {
		super(nome);
        this.nivel_de_energia = nivelenergia;
	}
	public Judoca(){
        this.nivel_de_energia = 200.0;
        this.name = "Judoca sem nome "+sequencialname++;
	}
	public Double ipponSeioiNague() {
		return 40.0;

	}
	public Double haraiGoshi() {
		return 45.5;

	}
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(200);
        if(x >= 0 && x<=99){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Ippon Seioi Nague|");
            a_ser_atacado.defender(ipponSeioiNague());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Harai Goshi|");
            a_ser_atacado.defender(haraiGoshi());
        }
    }
    public void defender(Double poderdeataque){
        x = sort.nextInt(2);
        if(x == 0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Esquivar");
            nivel_de_energia -= esquivar(poderdeataque);
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Nenhuma");
            nivel_de_energia -= poderdeataque;
        }
    }
}
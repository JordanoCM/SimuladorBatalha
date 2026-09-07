import java.util.Random;
public class UfcFighter extends Lutador implements Judo, Karate, JiuJitsu {
    static Integer sequencialname = 1;
    Integer x;
    Random sort = new Random();
    public UfcFighter(String nome) {
        super(nome);
    }
    public UfcFighter(String nome, Double nivelenergia) {
        super(nome);
        this.nivel_de_energia = nivelenergia;
    }
    public UfcFighter() {
        this.nivel_de_energia = 200.0;
        this.name = "Lurador de UFC sem nome "+sequencialname++;
    }
    public Double mataleao(){
        return 40.0;
    }
    public Double kimura(){
        return 50.0;
    }
    public Double arklock(){
        return 30.0;
    }
    public Double ipponSeioiNague() {
		return 40.0;

	}
	public Double haraiGoshi() {
		return 45.5;
	}
    public Double magueri(){
        return 55.0;
    }
    public Double guedanBarai(){
        return 60.0;
    }
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(8);
        if(x==0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Ark Lock|");
            a_ser_atacado.defender(arklock());
        }
        else if(x==1){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Mata Leão|");
            a_ser_atacado.defender(mataleao());
        }
        else if(x==2){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Kimura|");
            a_ser_atacado.defender(kimura());
        }
        else if(x==3){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Ippon Seioi Nague|");
            a_ser_atacado.defender(ipponSeioiNague());
        }
        else if(x==4){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Harai Goshi|");
            a_ser_atacado.defender(haraiGoshi());
        }
        else if(x==5){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Magueri|");
            a_ser_atacado.defender(magueri());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Guedan Barai|");
            a_ser_atacado.defender(guedanBarai());
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

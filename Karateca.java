import java.util.Random;
public class Karateca extends Lutador implements Karate {
    static Integer sequencialname = 1;
    Integer x;
    Random sort = new Random();
    public Karateca(String nome) {
        super(nome);
    }
    public Karateca(String nome, Double nivelenergia) {
        super(nome);
        this.nivel_de_energia = nivelenergia;
    }
    public Karateca() {
        this.nivel_de_energia = 200.0;
        this.name = "Karateca sem nome "+sequencialname++;
    }
    public Double magueri(){
        return 55.0;
    }
    public Double guedanBarai(){
        return 60.0;
    }
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(200);
        if(x >= 0 && x<=99){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-magueri|");
            a_ser_atacado.defender(magueri());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-guedan Barai|");
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

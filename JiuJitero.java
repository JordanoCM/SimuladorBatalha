import java.util.Random;
public class JiuJitero extends Lutador implements JiuJitsu {
    static Integer sequencialname = 1;
    Integer x;
    Random sort = new Random();
    public JiuJitero(String nome) {
        super(nome);
    }
    public JiuJitero(String nome, Double nivelenergia) {
        super(nome);
        this.nivel_de_energia = nivelenergia;
    }
    public JiuJitero() {
        this.nivel_de_energia = 200.0;
        this.name = "JiuJitero sem nome "+sequencialname++;
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
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(176);
        if(x >= 0 && x<=100){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Ark Lock|");
            a_ser_atacado.defender(arklock());
        }
        else if(x>=100 && x<=50){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Mata Leão|");
            a_ser_atacado.defender(mataleao());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Kimura|");
            a_ser_atacado.defender(kimura());
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

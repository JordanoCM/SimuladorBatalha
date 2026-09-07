import java.util.Random;

public class Fera extends Personagem {
    static Integer sequencialname = 1;
    Integer x;
    private Random sort = new Random();
    public Fera(String nome){
        super(nome);
        this.nivel_de_energia = 1000.0;
    }
    public Fera(){
        this("Fera sem nome "+sequencialname++);
        this.nivel_de_energia = 300.0;
    }
    public Fera(String nome, Double nivelenergia){
        this(nome);
        this.nivel_de_energia = nivelenergia;
    }
    public Double morder(){
        return 50.0;
    }
    public Double arranhar(){
        return 15.0;
    }
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(20);
        if(x >= 0 && x<=8){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Morder|");
            a_ser_atacado.defender(morder());
        }
        else if(x>=9 && x<=18){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Arranhar|");
            a_ser_atacado.defender(arranhar());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Mordida Especial|");
            a_ser_atacado.defender(morder()*3);
        }
    }
    public void defender(Double poderdeataque){
        if(Personagem.flag || Personagem.flag2)
            System.out.print("   Defesa-Nenhuma");
        nivel_de_energia -= poderdeataque;
    }
}

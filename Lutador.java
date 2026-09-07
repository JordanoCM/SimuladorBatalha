import java.util.Random;

public class Lutador extends Personagem implements ArteMarcial {
    static Integer sequencialname = 1;
    Integer x;
    public Lutador(String nome, Double niveldenergia){
        super(nome);
        this.nivel_de_energia = niveldenergia;
    }
    public Lutador(String nome){
        super(nome);
        this.nivel_de_energia = 200.0;
    }
    public Lutador(){
        super("Lutador sem nome "+sequencialname++);
        this.nivel_de_energia = 150.0;
    }
    private Random sort = new Random();
    public Double socar(){
        return 10.0;
    }
    public Double chutar(){
        return 12.0;
    }
    public Double esquivar(Double poderdeataque){
        return (0.05*poderdeataque);
    }
    public Double ficar_de_guarda(Double poderdeataque){
        return (0.1*poderdeataque);
    }
    public void atacarpersonagem(Personagem a_ser_atacado){
        x = sort.nextInt(201);
        if(x >= 0 && x<=99){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Soco|");
            a_ser_atacado.defender(socar());
        }
        else if(x>=100 && x<=199){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Chute|");
            a_ser_atacado.defender(chutar());
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Soco Lendário Ceifador|");
            nivel_de_energia += a_ser_atacado.nivel_de_energia;
            a_ser_atacado.nivel_de_energia = 0.0;
        }
    }
    public void defender(Double poderdeataque){
        x = sort.nextInt(3);
        if(x == 0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Esquivar");
            nivel_de_energia -= esquivar(poderdeataque);
        }
        else if(x==1){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Nenhuma");
            nivel_de_energia -= poderdeataque;
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Ficar de guarda");
            nivel_de_energia -= ficar_de_guarda(poderdeataque);
        }
    }
}

import java.util.Random;
public class CrossbowCBayoneta extends Arma implements Fogo,Perfurante,EfeitoMoral{
    Integer x;
    public Integer sizeataques(){
        return 0;
    }
    private Random sort = new Random();
    public Double atirar(){
        return 50.0;
    }
    public Double coronhada(){
        return 12.0;
    }
    public Double furar(){
        return 25.0;
    }
    public Double furaregirar(){
        return 35.0;
    }
    public Double atordoar(){
        return 7.0;
    }
    public Double afastar(){
        return 6.0;
    }
    public Double golpear(){
        x = sort.nextInt(7);
        if(x == 0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Crossbow atirando|");
            return atirar();
        }
        else if(x == 1){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Crossbow coronhada|");
            return coronhada();
        }
        else if(x == 2){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Crossbow furar|");
            return furar();
        }
        else if(x == 3){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Crossbow furando e girando no bucho|");
            return furaregirar();
        }
        else if(x == 4){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Crossbow fazendo afastar|");
            return afastar();
        }
        else{
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Chute fazendo atordoar|");
            return atordoar();
        }
    }
}

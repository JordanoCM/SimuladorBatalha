import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Gladiador extends Lutador{
    static Integer gladiator_sequencial_name = 1;
    private List<Armadura> armaduras = new ArrayList<Armadura>();
    private List<Arma> armas = new ArrayList<Arma>();
    Integer x;
    Integer y;
    private Random sort = new Random();
    public Gladiador(String nome, String arma_description, String Golpeini, Double poder_defensivo, Double poder_ofensivo){
        this.name = nome;
        this.nivel_de_energia = 200.0;
        Arma arma1 = new Arma();
        Armadura protection = new Armadura();
        Golpes iniGolpe = new Golpes();
        iniGolpe.set_nome_do_golpe(Golpeini);
        iniGolpe.set_poder_ofensivo(poder_ofensivo);
        arma1.set_description(arma_description);
        arma1.set_ataques(iniGolpe);
        protection.setEstado_de_conservação(15.0);
        if(poder_defensivo>=48.0){
            protection.setEstado_de_conservação(28.0);
        }
        protection.setPoder_de_defesa(poder_defensivo);
        set_armas(arma1);
        set_armaduras(protection);
    }
    public Gladiador(String nome, Arma arma1, Armadura protection){
        this.name = nome;
        this.nivel_de_energia = 200.0;
        set_armaduras(protection);
        set_armas(arma1);
    }
    public Gladiador(){
        this.name = "Gladiador sem nome "+gladiator_sequencial_name++;
        this.nivel_de_energia = 200.0;
        Arma arma1 = new Arma();
        Armadura protection = new Armadura();
        Golpes iniGolpe = new Golpes();
        iniGolpe.set_nome_do_golpe("Ataque Direto Padrao");
        iniGolpe.set_poder_ofensivo(30.0);
        arma1.set_description("Espada de ferro-Afiacao I");
        arma1.set_ataques(iniGolpe);
        protection.setEstado_de_conservação(10.0);
        protection.setPoder_de_defesa(25.0);
        set_armas(arma1);
        set_armaduras(protection);
    }
    public Gladiador(String nome, Double nivelenergia){
        this.name = nome;
        this.nivel_de_energia = nivelenergia;
    }
    public void defender(Double poderdeataque){
        x = sort.nextInt(3);
        int z = armaduras.size();
        if(z>0)
            y = sort.nextInt(z);
        if(z == 0){
            if(x == 0){
                if(Personagem.flag || Personagem.flag2)
                    System.out.print("  Defesa-Nenhuma");
                nivel_de_energia -= poderdeataque;
            }
            else if(x == 1){
                if(Personagem.flag || Personagem.flag2)
                    System.out.print("  Defesa-Esquiva");
                nivel_de_energia -= esquivar(poderdeataque);
            }
            else if(x == 2){
                if(Personagem.flag || Personagem.flag2)
                    System.out.print("  Defesa-Ficar de guarda");
                nivel_de_energia -= ficar_de_guarda(poderdeataque);
            }
        }
        else if(x == 0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Armadura");
            nivel_de_energia -= get_armaduras(y).tankou(poderdeataque);
        }
        else if(x == 1){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("   Defesa-Armadura+Esquiva");
            nivel_de_energia -= esquivar(get_armaduras(y).tankou(poderdeataque));
        }
        else if(x == 2){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("  Defesa-Ficar de guarda+Armadura");
            nivel_de_energia -= ficar_de_guarda(get_armaduras(y).tankou(poderdeataque));
        }
        delbroken();
    }
    public void atacarpersonagem(Personagem a_ser_atacado){
        int j = armas.size();
        if(j!=0)
            x = sort.nextInt(3);
        else
            x = sort.nextInt(2);
        if(x == 0){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Soco|");
            a_ser_atacado.defender(super.socar());
        }
        else if(x == 1){
            if(Personagem.flag || Personagem.flag2)
                System.out.print("|Ataque-Chute|");
            a_ser_atacado.defender(super.chutar());
        }
        else{
            if(j != 0)
                x = sort.nextInt(j);
            if(get_armas(x).sizeataques() != 0){
                y = sort.nextInt((get_armas(x).sizeataques()));
                a_ser_atacado.defender(get_armas(x).get_ataques(y).get_poder_ofensivo());
                if(Personagem.flag || Personagem.flag2 && get_armas(x).sizeataques() == 0)
                    System.out.print("|Ataque-"+get_armas(x).get_description()+" Com o golpe "+get_armas(x).get_ataques(y).get_nome_do_golpe()+"|");
            }
            else{
                if(get_armas(x) instanceof CrossbowCBayoneta){
                    CrossbowCBayoneta crossbow = (CrossbowCBayoneta)get_armas(x);
                    a_ser_atacado.defender(crossbow.golpear());
                }
            }
        }
    }
    public void set_armaduras(Armadura beta){
        armaduras.add(beta);
    }
    public Armadura get_armaduras(Integer x){
        return armaduras.get(x);
    }
    public void set_armas(Arma beta){
        armas.add(beta);
    }
    public Arma get_armas(Integer x){
        return armas.get(x);
    }
    public void roubaritens(Gladiador a_ser_roubado){
        for(Arma roubo: a_ser_roubado.armas){
            if(armas.size() != 0){
                if(roubo.sizeataques() == 0 || get_armas(0).sizeataques() == 0)
                    set_armas(roubo);
                else if(roubo.sizeataques() !=0 && get_armas(0).sizeataques() !=0){
                    if(get_armas(0).get_ataques(0).get_poder_ofensivo()<=roubo.get_ataques(0).get_poder_ofensivo())
                        set_armas(roubo);
                }
        }
        }
        for(Armadura roubo2: a_ser_roubado.armaduras){
            set_armaduras(roubo2);
        }
    }
    private void delbroken(){
        Armadura x;
        for(int i=0; i<armaduras.size();i++){
            x = get_armaduras(i);
            if(x.getEstado_de_conservação()<=0.0)
                armaduras.remove(i);
        }
    }
    public Integer sizearma(){
        return armas.size();
    }
    public Integer sizearmadura(){
        return armaduras.size();
    }
}
public abstract class Personagem{
    public static Boolean flag;
    public static Boolean flag2;
    public Double nivel_de_energia;
    protected String name;
    public Boolean estavivo(){
        if(nivel_de_energia <= 0){
            return false;
        }
        else{
            return true;
        }
    }
    public abstract void atacarpersonagem(Personagem a_ser_atacado);
    public abstract void defender(Double poderdeataque);
    public Personagem(String nome){
        this.name = nome;
    }
    public Double get_nivel_de_energia(){
        return nivel_de_energia;
    }
    public String toString() {
		return  this.name + "[" + Math.round(nivel_de_energia) +"]";
	}
    public String get_name(){
        return name;
    }
}
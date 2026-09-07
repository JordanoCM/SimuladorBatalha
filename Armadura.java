public class Armadura{
    private String descricaoarmadura;
    private Double poder_de_defesa;
    private Double estado_de_conservação;
    public Double tankou(Double poderdeataque){
        if(estado_de_conservação >=0.0){
        poderdeataque -= poder_de_defesa;
        estado_de_conservação -= 1.0;
        if(poderdeataque>0)
            return poderdeataque;
        else
            return 0.0;
        }
        else{
            return poderdeataque;
        }
    }
    public void setEstado_de_conservação(Double estado_de_conservação) {
        this.estado_de_conservação = estado_de_conservação;
    }
    public Double getEstado_de_conservação() {
        return estado_de_conservação;
    }
    public void setPoder_de_defesa(Double poder_de_defesa) {
        this.poder_de_defesa = poder_de_defesa;
    }
    public Double getPoder_de_defesa() {
        return poder_de_defesa;
    }
    public void set_descricaoarmadura(String n){
        descricaoarmadura = n;
    }
    public String get_descricaoarmadura(){
        return descricaoarmadura;
    }
}

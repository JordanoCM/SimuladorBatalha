import java.util.ArrayList;
import java.util.List;

public class Arma implements Armamento{
    private String description;
    protected List<Golpes> ataques = new ArrayList<Golpes>();
    public void set_description(String alfa){
        description = alfa;
    }
    public String get_description(){
        return description;
    }
    public void set_ataques(Golpes beta){
        ataques.add(beta);
    }
    public Golpes get_ataques(Integer x){
        return ataques.get(x);
    }
    public Integer sizeataques(){
        return ataques.size();
    }
}

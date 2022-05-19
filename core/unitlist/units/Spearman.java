package core.unitlist.units;

public class Spearman extends Unit {

    public Spearman(int x, int y){
        super(x, y);
        setCost(20);
        setRank(2);
        setSkin("^");
    }
    public int getSalary(){
        return 6;
    }
}
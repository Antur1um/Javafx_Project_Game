package core.unitlist.units;

public class Wanderer extends Unit {

    public Wanderer(int x, int y){
        super(x, y);
        setCost(10);
        setRank(1);
        setSkin("@");
    }
    public int getSalary(){
        return 2;
    }
}
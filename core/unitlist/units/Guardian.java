package core.unitlist.units;

public class Guardian extends Unit {

    public Guardian(int x, int y){
        super(x, y);
        setCost(30);
        setRank(3);
        setSkin("%");
    }
    public int getSalary(){
        return 18;
    }
}
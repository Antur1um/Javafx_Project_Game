package core.unitlist;

import java.util.ArrayList;
import core.unitlist.units.Unit;
import core.unitlist.units.Spearman;
import core.unitlist.units.Guardian;

public class UnitList {
    
    private ArrayList<Unit> arr_uns = new ArrayList<Unit>();

    public void addUnit(Unit new_unit){
        arr_uns.add(new_unit);
    }

    public Unit getUnit(int index){
        return arr_uns.get(index);
    }

    public Unit getUnitByCoordinat(int x, int y){
        for(int i=0; i < arr_uns.size(); i++)
            if(arr_uns.get(i).getX() == x && arr_uns.get(i).getY() == y)
                return arr_uns.get(i);
        return null;
    }

    public void delUnit(int x, int y){
        for(int i=0; i < arr_uns.size(); i++)
            if(arr_uns.get(i).getX() == x && arr_uns.get(i).getY() == y)
                arr_uns.remove(i);
    }

    public boolean checkPoint(int x, int y){  // Проверяет есть ли по данным координатам юнит из его списка.
        for(int i=0; i < arr_uns.size(); i++)
            if(arr_uns.get(i).getX() == x && arr_uns.get(i).getY() == y)
                return true;
        return false;
    }

    public void mergeUnit(int x1, int y1, int x2, int y2){

        Unit unit1 = getUnitByCoordinat(x1, y1);
        Unit unit2 = getUnitByCoordinat(x2, y2);
        int index = arr_uns.indexOf(unit2);

        if(unit1.getRank() == unit2.getRank() && unit1.getRank() != 3){

            boolean action = unit2.getAction();

            if(unit1.getRank() == 1)
                unit2 = new Spearman(x2, y2);
            
            else if(unit1.getRank() == 2)
                unit2 = new Guardian(x2, y2);

            arr_uns.set(index, unit2);
            unit2.setAction(action);
            delUnit(x1, y1);
        }
        else return;
    }

    public int getSize(){
        return arr_uns.size();
    }

}
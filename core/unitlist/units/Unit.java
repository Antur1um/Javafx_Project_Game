package core.unitlist.units;

public class Unit {

    private String skin;
    private int position_x;
    private int position_y;
    private int rank;
    private int food; // на сколько уменьшится прибыль при добавлении юнита
    private boolean side; // в отличие от плитки либо красный, либо синий.
    private boolean action = true;  // 0-сходил, 1-не сходил

    public Unit(int x, int y){  // Отрисовка происходит после отрисовки поля
                              //поэтому юнит должен знать где он дожен рисоваться да и просто для проверок.
        position_x = x;
        position_y = y;
    }
    public String getSkin(){
        return skin;
    }
    public int getX(){
        return position_x;
    }
    public int getY(){
        return position_y;
    }
    public int getRank(){
        return rank;
    }
    public int getFood(){
        return food;
    }
    public boolean getSide(){
        return side;
    }
    public boolean getAction(){
        return action;
    }

    public void setSkin(String s){
        skin = s;
    }
    public void setX(int x){
        position_x = x;
    }
    public void setY(int y){
        position_y = y;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public void setFood(int food){
        this.food = food;
    }
    public void setSide(boolean s){
        side = s;
    }
    public void setAction(boolean a){
        action = a;
    }

}
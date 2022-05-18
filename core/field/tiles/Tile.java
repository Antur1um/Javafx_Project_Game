package core.field.tiles;

public class Tile {

    private int side = 0;  // 0-нечейная, 1-красных, 2-синих
    private int profit = 0; // сколько к доходу добавляет данный тайл (заросший - 0, захвачен -  1, c постройкой - 4  )
    private boolean build = false; //Есть ли на данном объекте постройка
    private String skin = "0"; 
    

    public int getSide(){
        return side;
    }
    public void setSide(int side){
        this.side = side;
    }
    public String getSkin(){
        return skin;
    }
    public void setSkin(String skin){
        this.skin = skin;
    }
    public int getProfit(){
        return profit;
    }
    public void setProfit(int new_profit){
        this.profit = new_profit;
    }
    public boolean getBuild(){
        return build;
    }
    public void setBuild(){
        this.build = true;
    }


    public int getType(){  // 0-тип пустая, 1- захвачена, 2 - захвачена заросшая ...
        return 0;
    }

}
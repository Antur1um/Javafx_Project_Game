package core.field.tiles;

public class Tile {

    private int side = 0;    // 0-нечейная, 1-красных, 2-синих
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

    public int getType(){  // 0-тип пустая, 1- захвачена, 2 - захвачена заросшая ...
        return 0;
    }

}
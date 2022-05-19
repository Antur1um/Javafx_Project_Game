package core.field.tiles;

public class CapturedTile extends Tile {
  
    public CapturedTile(int side){     // 0-нечейная, 1-красных, 2-синих
        setSide(side);
        
        if(side == 1) setSkin("x");
        if(side == 2) setSkin("#");
        else return;
    }  
    public int getType(){
        return 1;
    }

}
package core.field.tiles;

public class CapturedTile extends Tile {
  
    public CapturedTile(boolean side){

        if(!side)
        {
            setSkin("x");
            setSide(1);
        }
        else
        {
            setSkin("#");
            setSide(2);
        }  
    }
    public int getType(){
        return 1;
    }

}
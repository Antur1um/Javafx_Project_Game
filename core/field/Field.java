package core.field;

import core.field.tiles.Tile;
import core.player.PlayerController.*;  //не дает отдельно импортировать метод
import core.unitlist.UnitList;

public class Field {

    private Tile[][] tiles;
    private int height, width;
    

    public Field(int h, int w){
        this.height = h;
        this.width = w;

        tiles = new Tile[height][width];
        for(int i=0; i < height; i++)
            for(int j=0; j < width; j++)
                tiles[i][j] = new Tile();
    }

    public int getHeight(){
        return height;
    }
    public int getWidtht(){
        return  width;
    }
    public int getTotalProfit_for_Side(int side){
        int total_profit = 0;
        for(int i=0; i < height; i++)
            for(int j=0; j < width; j++)
                if(tiles[i][j].getSide() == side)
                total_profit+= tiles[i][j].getProfit();         
        return total_profit;
    }
   


    public void addTile(int x, int y, Tile new_tile){
        if(x >= 0 && x < width && y >= 0 && y < height)
            tiles[y][x] = new_tile;
        // Обязалеьно сделать обработку неправильных значений
    }
    public Tile getTile(int x, int y){
        return tiles[y][x];
        // Обязалеьно сделать обработку неправильных значений
    }
}


package core.field;

import core.field.tiles.Tile;

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
    public int getNumTiles_for_Side(int side){
        int value = 0;

        for(int i=0; i < height; i++)
            for(int j=0; j < width; j++)
                if(tiles[i][j].getSide() == side)
                    value++;
        return value;
    }
    public int getProfitTiles_for_Side(int side){
        int total_profit = 0;
        for(int i=0; i < height; i++)
            for(int j=0; j < width; j++)
                if(tiles[i][j].getSide() == side && tiles[i][j].getType() == 1)
                    total_profit++;         
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


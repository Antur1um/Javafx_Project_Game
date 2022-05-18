package core.player;

import core.field.Field;
import core.unitlist.UnitList;

public class PlayerController {

    private int side;              // 1-красный, 2-синий
    private Field fid;            // Поле общее для всех гроков
    private UnitList plr_units;  // Массив из всех юнитов игрока
    private int treasury; //Казна
    private int profit; // текущая прибыль

    public PlayerController(int side, Field field){
        this.side = side;
        this.fid = field;
        this.treasury = 10;
        this.profit = 5; 
        plr_units = new UnitList();

    }
    public int getProfit(){
        return profit;
    }

    public int getSide(){
        return side;
    }
    public int getTreasury(){
        return treasury;
    }

    public UnitList getUnitsList(){
        return plr_units;
    }

    public void UpdateTreasure(){
        this.treasury = this.treasury + this.profit;
    }

   public void CountMyProfit(){
       int Profit = fid.getTotalProfit_for_Side(side);
       int Food = plr_units.CountFood();
       profit = Profit - Food;
   }




    public boolean Check_path(int start_x, int start_y, int end_x, int end_y)  // проверяет может ли дойти юнит до точки
    {
        int HEIGHT = fid.getHeight();
        int WIDTH = fid.getWidtht();

        int grid[][] = new int[HEIGHT][WIDTH]; // создаем специальный массив

        for (int i=0; i < HEIGHT ; i++)
            for (int j=0; j < WIDTH; j++)
            { 
                if (fid.getTile(j, i).getSide() == side)
                    grid[i][j] = -2;         // где -2 свое поле
                else 
                    grid[i][j] = -1;   //  где -1 другое поле, и не конечая плитка!
            }

        int dx[] = {1, 0, -1, 0, -1, 1, -1, 1 };  
        int dy[] = {0, 1, 0, -1, 1, 1, -1, -1};
        int len; // длина пути
        int x, y, k;
        int d = 0; // счетчик пути
        boolean stop;
        
        grid[end_y][end_x] = -2;  
        grid[start_y][start_x] = 0;
        do {
            stop = true;
            for (y = 0; y < HEIGHT; y++)
                for(x = 0; x < WIDTH; x++)
                    if (grid[y][x] == d)
                    {
                        for(k=0; k < 8; k++)
                        {
                            int iy=y + dy[k], ix = x + dx[k];
                            if(iy >= 0 && iy <  HEIGHT && ix >= 0 && ix < WIDTH && grid[iy][ix] == -2)
                            {
                                stop = false;
                                grid[iy][ix] = d + 1;
                            }
                        }
                    }
            d++;
        } while(!stop && grid[end_y][end_x] == -2);

        if (grid[end_y][end_x] == -2) return false;

        len = grid[end_y][end_x];

        if( len > 5) return false;    // 5 - длина пути!
        else return true;
    }

    public boolean Check_zone(int x, int y)  // проверяет полученную точку на принадлежность к своей территории
    {                                       //  + её границы.
        if(fid.getTile(x, y).getSide() == side) return true;
        else{
            for (int i=y-1; i <= y+1 && i >= 0 && i < fid.getHeight(); i++)
                for(int j=x-1; j <= x+1 && j >= 0 && j < fid.getWidtht(); j++)
                    if(fid.getTile(j, i).getSide() == side) return true;
        }
        return false;
    }

   
}
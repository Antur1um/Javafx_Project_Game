package core.player;

import java.util.Scanner;

//import java.util.Scanner;
import core.field.Field;
import core.unitlist.UnitList;
import core.unitlist.units.Unit;
import core.unitlist.units.Wanderer;
import core.field.tiles.CapturedTile;

public class PlayerController {

    private int side;               // 1-красный, 2-синий
    private int treasury;          // Казна
    private Field fid;            // Поле общее для всех гроков
    private UnitList plr_units;  // Массив из всех юнитов игрока

    public PlayerController(int side, Field field){
        this.side = side;
        this.treasury = 10;
        this.fid = field;
        plr_units = new UnitList();
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
    public int countProfit(){
        return fid.getProfitTiles_for_Side(side) - plr_units.countSalary(); 
    }
    public void UpdateTreasury(){
        treasury = treasury + countProfit();
    }
    public void UpdateUnitAction(){
        for(int i=0; i < plr_units.getSize(); i++)
            plr_units.getUnit(i).setAction(true);
    }

    public boolean checkPath(int start_x, int start_y, int end_x, int end_y)  // проверяет может ли дойти юнит до точки
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

        if( len > 3) return false;    // 3 - длина пути!
        else return true;
    }

    public boolean checkZone(int x, int y)  // проверяет полученную точку на принадлежность к своей территории
    {                                       //  + её границы.
        if(fid.getTile(x, y).getSide() == side) return true;
        else{
            for (int i=y-1; i <= y+1 && i >= 0 && i < fid.getHeight(); i++)
                for(int j=x-1; j <= x+1 && j >= 0 && j < fid.getWidtht(); j++)
                    if(fid.getTile(j, i).getSide() == side) return true;
        }
        return false;
    }


    public void createUnit(int x, int y,  PlayerController p_emp){

        if (checkZone(x, y)){

            if (!plr_units.checkPoint(x, y)){  // Если юнит создается на своем поле или на границе
            
                Unit ut = new Wanderer(x, y);                

                if (fid.getTile(x, y).getSide() == p_emp.getSide()){  // Если создается на чужой территории 
                
                    int rank_unit = ut.getRank();
    
                    if(p_emp.getUnitsList().pointProtectionRank(x, y) > rank_unit) return;
                    else if (p_emp.getUnitsList().checkPoint(x, y)){
                        p_emp.getUnitsList().delUnit(x, y);
                        ut.setAction(false);  
                        fid.addTile(x, y, new CapturedTile(side));       
                    }
                    
                }
                else if(fid.getTile(x, y).getSide() == 0){   // Если создаем на границе и можем создать
                    ut.setAction(false);                                              // то активность 0 
                    fid.addTile(x, y, new CapturedTile(side));                       // Тайл заменяем на наш.
                }
                    
                plr_units.addUnit(ut);   // И добавлем юнит в список юнитов игрока.
            }
            else if (plr_units.checkPoint(x, y))  // Если юнит создается на уже сущесвущем нашем юните
            {
                Unit ut = new Wanderer(-1, -1);        // Создаю юнита вне поля, для проверки
                plr_units.addUnit(ut);
                plr_units.mergeUnit(-1, -1, x, y);  // отправляем на обьединение
                
                if(plr_units.checkPoint(-1, -1)) plr_units.delUnit(-1, -1);  // если же юнит не обьединлся, удаляем юнит его 
            }
        }

        else return;


    }

    public void moveUnit(int start_x, int start_y, int end_x, int end_y, PlayerController p_emp){

        if(start_x == end_x && start_y == end_y) return;
        else if(!plr_units.getUnitByCoordinat(start_x, start_y).getAction()) return;

        else if(checkPath(start_x, start_y, end_x, end_y) && checkZone(end_x, end_y))

            if(plr_units.checkPoint(end_x, end_y))   // если ходит на своего юнита
                plr_units.mergeUnit(start_x, start_y, end_x, end_y);

            else{

                if (fid.getTile(end_x, end_y).getSide() == p_emp.getSide()){ // если заходит на чужую территорию

                    int rank_unit = plr_units.getUnitByCoordinat(start_x, start_y).getRank();
                    if(p_emp.getUnitsList().pointProtectionRank(end_x, end_y) > rank_unit) return;
                    else if (p_emp.getUnitsList().checkPoint(end_x, end_y)) p_emp.getUnitsList().delUnit(end_x, end_y);
                }

                Unit unit_mv = plr_units.getUnitByCoordinat(start_x, start_y);
                unit_mv.setX(end_x);
                unit_mv.setY(end_y);
                unit_mv.setAction(false);

                if(fid.getTile(end_x, end_y).getSide() != side)    // Если поле куда сходил игрок было не его сделать его.
                    fid.addTile(end_x, end_y, new CapturedTile(side));
            }
        
    }
}
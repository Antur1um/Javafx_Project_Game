package client;

import java.util.Scanner;
import core.field.Field;
import core.field.tiles.CapturedTile;
import core.unitlist.UnitList;
import core.unitlist.units.Unit;
import core.unitlist.units.Wanderer;
import core.player.PlayerController;

public class Dispatcher {

    Field fid;
    Render render;
    PlayerController p1;
    PlayerController p2;

    public Dispatcher(Field fid, PlayerController p1, PlayerController p2){
        this.fid = fid;
        this.p1 = p1;
        this.p2 = p2;
        render = new Render();
    }

    Scanner console = new Scanner(System.in);

    public void menuHome(){

       // System.out.println("\033\143");
        render.drawField(fid, p1.getUnitsList(), p2.getUnitsList());
        
        System.out.println();
        System.out.println("1. Выбрать юнит");
        System.out.println("2. Создать юнит");
        System.out.println("3. Завершить ход");
        p1.CountMyProfit();
        p1.UpdateTreasure();
        System.out.println("Казна: " + p1.getTreasury());
        System.out.println("Прибыль " + p1.getProfit());
        int s = console.nextInt();

        if(s == 1) menuMoveUnit();
        else if(s == 2) menuCreateUnit();
        else if(s == 3) menuСomplete();
        else menuHome();

    }
    public void menuMoveUnit(){

        System.out.print("Координаты X: ");
        int x = console.nextInt();
        System.out.print("Координаты Y: ");
        int y = console.nextInt();

        if(p1.getUnitsList().checkPoint(x, y)){
            System.out.flush();
            System.out.println("Куда переместить?");
            System.out.print("Координаты X: ");
            int x1 = console.nextInt();
            System.out.print("Координаты Y: ");
            int y1 = console.nextInt();

            if(p1.Check_path(x, y, x1, y1) && p1.Check_zone(x1, y1))
                if(p1.getUnitsList().checkPoint(x1, y1))
                    p1.getUnitsList().mergeUnit(x, y, x1, y1);
                else{
                    Unit unit_mv = p1.getUnitsList().getUnitByCoordinat(x, y);
                    unit_mv.setX(x1);
                    unit_mv.setY(y1);
                    unit_mv.setAction(false);

                    if(fid.getTile(x1, y1).getSide() != 1)
                        fid.addTile(x1, y1, new CapturedTile(false));
                }
            else{
                System.out.println("Путь не найден");
                console.nextInt();
            }
        }
        else{
            System.out.println("Юнита нет...");
            console.nextInt();
        }

        menuHome();
    }
    public void menuCreateUnit(){

        System.out.print("Координаты X: ");
        int x = console.nextInt();
        System.out.print("Координаты Y: ");
        int y = console.nextInt();

        System.out.println(p1.Check_zone(x, y));
        console.nextInt();
        if (p1.Check_zone(x, y) && !p1.getUnitsList().checkPoint(x, y)){
            
            Unit ut = new Wanderer(x, y);

            if(fid.getTile(x, y).getSide() == 0){
                ut.setAction(false);
                fid.addTile(x, y, new CapturedTile(false));
            }

            p1.getUnitsList().addUnit(ut);
        }
        else if (p1.Check_zone(x, y) && p1.getUnitsList().checkPoint(x, y))
        {
            Unit ut = new Wanderer(-1, -1);  // Создаю юнита вне поля, для проверки
            p1.getUnitsList().addUnit(ut);
            p1.getUnitsList().mergeUnit(-1, -1, x, y);
        }

        menuHome();

    }
    public void menuСomplete(){
        menuHome();
    }


    
}

class Render {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_BLUE = "\u001B[34m";


    public void drawField(Field f, UnitList ur, UnitList ub){
        int height = f.getHeight();
        int widtht = f.getWidtht();

        System.out.println("\033\143");

        for(int i=0; i < height; i++){
            for(int j=0; j < widtht; j++){
                boolean v = true;

                if(v)
                {
                    for (int k=0; k < ur.getSize(); k++)
                        if(ur.getUnit(k).getX() == j && ur.getUnit(k).getY() == i)
                        {
    
                            System.out.print(" " + COLOR_RED + ur.getUnit(k).getSkin() + COLOR_RESET);
                            v = false;
                            break;
                        }
                
                    for (int k=0; k < ub.getSize(); k++)
                        if(ub.getUnit(k).getX() == j && ub.getUnit(k).getY() == i)
                        {
                           
                            System.out.print(" " + COLOR_BLUE + ub.getUnit(k).getSkin() + COLOR_RESET);
                            v = false;
                            break;
                        }
                }
            
                    
                if(v)
                {
                    System.out.print(COLOR_RESET);
                 
                    if (f.getTile(j, i).getSide() == 1)
                        System.out.print(COLOR_RED);
                    else if (f.getTile(j, i).getSide() == 2)
                        System.out.print(COLOR_BLUE);
                        System.out.print(" " + f.getTile(j, i).getSkin());
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }
        
}
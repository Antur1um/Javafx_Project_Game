package client;

import java.util.Scanner;
import core.field.Field;
import core.unitlist.UnitList;
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

    public void menuHome(PlayerController current_plr){

       // System.out.println("\033\143");
        render.drawField(fid, p1.getUnitsList(), p2.getUnitsList());
        
        System.out.println(); 
        if(current_plr.getSide() == 1) System.out.println("\u001B[31m");
        else if(current_plr.getSide() == 2)  System.out.println("\u001B[36m");
        System.out.println("Казна: " + current_plr.getTreasury());
        System.out.println("Прибыль: " + current_plr.countProfit() + "\u001B[0m");
        System.out.println("1. Выбрать юнит");
        System.out.println("2. Создать юнит");
        System.out.println("3. Завершить ход");
        int s = console.nextInt();

        if(s == 1) menuMoveUnit(current_plr);
        else if(s == 2) menuCreateUnit(current_plr);
        else if(s == 3) menuСomplete(current_plr);
        else menuHome(current_plr);

    }
    public void menuMoveUnit(PlayerController current_plr){

        System.out.print("Координаты X: ");
        int x = console.nextInt();
        System.out.print("Координаты Y: ");
        int y = console.nextInt();

        if(current_plr.getUnitsList().checkPoint(x, y)){
            System.out.flush();
            System.out.println("Куда переместить?");
            System.out.print("Координаты X: ");
            int x1 = console.nextInt();
            System.out.print("Координаты Y: ");
            int y1 = console.nextInt();

            current_plr.moveUnit(x, y, x1, y1);
        }
        else{
            System.out.println("Юнита нет...");
            console.nextInt();
        }

        menuHome(current_plr);
    }
    public void menuCreateUnit(PlayerController current_plr){

        System.out.print("Координаты X: ");
        int x = console.nextInt();
        System.out.print("Координаты Y: ");
        int y = console.nextInt();

        current_plr.createUnit(x, y);

        menuHome(current_plr);

    }
    public void menuСomplete(PlayerController current_plr){
        current_plr.UpdateTreasury();
        return;
    }

}

class Render {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_BLUE = "\u001B[36m";


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
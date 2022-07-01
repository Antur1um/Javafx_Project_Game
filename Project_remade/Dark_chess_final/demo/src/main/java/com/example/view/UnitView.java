package com.example.view;

import com.example.game.Unit.Stranger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.HashMap;

import static com.example.view.ScreenController.TILE_SIZE;
import com.example.view.ScreenController.*;


public class UnitView extends ImageView {
    private double X; // Текущая позиция юнита
    private double Y; // Используются чтобы вернуть его назад в случае недопустимого движения

    private int BoardX;

    private int BoardY;

    private boolean Side;

private String getSkin(String t){
    HashMap<String, String> skins = new HashMap<>();
    skins.put("SPEARMAN_RED", "src/main/resources/com/example/view/units_skins/spearman_red.png" );
    skins.put("SPEARMAN_BLUE", "src/main/resources/com/example/view/units_skins/spearman_blue.png" );
    skins.put("GUARDIAN_RED", "src/main/resources/com/example/view/units_skins/guardian_red.png" );
    skins.put("GUARDIAN_BLUE", "src/main/resources/com/example/view/units_skins/guardian_blue.png" );
    skins.put("STRANGER_RED", "src/main/resources/com/example/view/units_skins/wanderer_red.png");
    skins.put("STRANGER_BLUE", "src/main/resources/com/example/view/units_skins/wanderer_blue.png");
    return skins.get(t);
}


    public double getXPos(){
        return  X;
    }

    public double getYPos(){
        return Y;
    }

    public void setUnitX(double newX){X = newX;}
    public void setUnitY(double newY){X = newY;}

    private int power;

    private int food;

    private int cost;




    public void toBoard(double x, double y){
        relocate(x * TILE_SIZE,  y * TILE_SIZE - (TILE_SIZE / 1.5));
        relocate(x * TILE_SIZE,  y * TILE_SIZE - (TILE_SIZE / 1.5));
        setUnitX(x * TILE_SIZE);
        setUnitY(y * TILE_SIZE - (TILE_SIZE / 1.5));
    }


    public UnitView(String type, int x, int y) {
        File file = new File(getSkin(type.toString()));
        Image image = new Image(file.toURI().toString());
        if(type.contains("RED")){
            setSideUnit(true);
        }
        else {
            setSideUnit(false);
        }
        if(type.contains("STRAN")){
            setPower(1);
            setCost(10);
            setfood(2);
        } else if (type.contains("SPEAR")) {
            setPower(2);
            setCost(20);
            setfood(6);
        }
        else{
            setPower(3);
            setCost(30);
            setfood(18);}

        setImage(image);
        X = x * TILE_SIZE;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        BoardX = (int)x;
        BoardY = (int)y;
        setFitHeight(205);
        setFitWidth(128);
        relocate(X, Y);

        Unit_Draggable_maker mover = new Unit_Draggable_maker();
        mover.makeDraggable(this);
    }


    public void move(double x, double y){

        X = x * TILE_SIZE;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        relocate(X, Y);
    }

    public boolean getSide() {
        return Side;
    }

    public void setSideUnit(boolean side) {
        Side = side;
    }


    public void setBoardX(int x){
        BoardX = x;
    }

    public void setBoardY(int y){
        BoardY = y;
    }

    public int getBoardX() {
        return BoardX;
    }

    public int getBoardY() {
        return BoardY;
    }

    public void abortMove() {
        relocate(X, Y);
    }



    public void setPower(int power) {
        this.power = power;
    }
    public int getPower() {
        return power;
    }

    public void setfood(int food){this.food = food;}
    public int getFood() {
        return food;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    /*public static  final Comparator<Unit> COMPARE_BY_COUNT = new Comparator<Unit>() {
        @Override
        public double compare(Unit o1, Unit o2) {
            return o1.getYPos() - o2.getYPos();
        }
    }*/

}




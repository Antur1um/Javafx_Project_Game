package com.example.game;

import com.example.game.Unit.Unit;

import java.util.ArrayList;

public class Player {

    private int MyProfit = 50;
    private int MyTreasure = 50;
    //private ArrayList unitlist = new ArrayList<Unit>(); // Скорее всего эта штука не нужна


    public int getMyProfit() {
        return MyProfit;
    }

    public int getMyTreasure() {
        return MyTreasure;
    }

    public void setMyProfit(int myProfit) {
        MyProfit = myProfit;
    }

    public void setMyTreasure(int myTreasure) {
        if (myTreasure >= 0){
            MyTreasure = myTreasure;
        }
        else {
            MyTreasure = 0;
        }

    }

    public Player(){}

    //public void addUnit(Unit unit){
        //unitlist.add(unit);
    //}



}

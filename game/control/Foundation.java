package ShougiGUI.game.control;

import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.board.mass.koma.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class Foundation{
    public static void appear(Mass mass,Koma koma){ mass.setKoma(koma); }
    public static void vanish(Mass mass){ mass.delete(); }
    public static void deth(Mass mass,Komadai[] komadai){
        Koma koma = mass.getKoma();
        int bw = koma.getOwner();
        Komadai target = komadai[(bw+1)%2];
        target.add(koma);
        vanish(mass);
    }
    public static void rebirth(Mass current,Mass distination,Komadai[] komadai){
        Koma koma = current.getKoma().clone();
        System.out.println(current.getKoma().equals(koma));
        distination.setKoma(koma);
        komadai[koma.getOwner()].dis(koma);
    }
    
    public static void move(Mass current,Mass distination,Komadai[] komadai,boolean grow){
        if(grow && current.getKoma()!=null) current.getKoma().growup();
        if(distination.getKoma() == null){
            appear(distination,current.getKoma());
            vanish(current);
        }else{
            //Koma koma = distination.getKoma();
            deth(distination,komadai);
            appear(distination,current.getKoma());
            vanish(current);
        }
    }
    
    public static boolean enemyTeritory(Mass current,Mass destination){
        Integer[] cPos = current.getPosition();
        Integer[] dPos = destination.getPosition();
        Koma koma = current.getKoma();
        if(koma.getGrowable() == false) return false;
        int bw = koma.getOwner();
        int teritory = 1;
        if(bw == 1) teritory = 7;
        if((cPos[0]>=teritory-1 && cPos[0]<=teritory+1) || (dPos[0]>=teritory-1 && dPos[0]<=teritory+1)) return true;
        else return false;
    }
}
        

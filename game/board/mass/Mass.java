package ShougiGUI.game.board.mass;

import ShougiGUI.game.board.mass.koma.*;
import ShougiGUI.game.control.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Mass extends Button{
    private Koma koma;
    //private String code;
    private Integer[] position = {0,0};
    
    public Mass(int i,int j){
        this("",i,j); //駒台においてはi=num,j=quantity
    }
    
    public Mass(Koma koma,int i,int j){
        this(i,j);
        this.koma = koma;
    }
    
    public Mass(String code,int i,int j){
        super();
        setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        setPrefSize(56,67);
        position[0] = i;
        position[1] = j;
        //this.code = code;
        if(code.equals("")) return;
        this.koma = toKoma(code);
        setText(koma.getName());
    }
    
    //public String getCode(){ return code; }
    public Koma getKoma(){ return koma; }
    public void setKoma(Koma koma){
        this.koma = koma;
        setText(koma.getName());
    }
    public int getPosX(){ return position[0]; }
    public int getPosY(){ return position[1]; }
    public Integer[] getPosition(){ return position; }
    public void addPosY(){ position[1]++; }
    public void disPosY(){ position[1]--; }
    public void delete(){
        this.koma = null;
        setText("");
    }
    
    public Koma toKoma(String code){
        Koma koma;
        switch(code){
            case "":
                koma = null; break;
            case "k":
                koma = new Gyoku(0); break;
            case "K":
                koma = new Gyoku(1); break;
            case "g":
                koma = new Kin(0); break;
            case "G":
                koma = new Kin(1); break;
            case "s":
                koma = new Gin(0); break;
            case "+s":
                koma = new Gin(0);
                koma.growup(); break;
            case "S":
                koma = new Gin(1); break;
            case "+S":
                koma = new Gin(1);
                koma.growup(); break;
            case "n":
                koma = new Keima(0); break;
            case "+n":
                koma = new Keima(0);
                koma.growup(); break;
            case "N":
                koma = new Keima(1); break;
            case "+N":
                koma = new Keima(1);
                koma.growup(); break;
            case "l":
                koma = new Kyou(0); break;
            case "+l":
                koma = new Kyou(0);
                koma.growup(); break;
            case "L":
                koma = new Kyou(1); break;
            case "+L":
                koma = new Kyou(1);
                koma.growup(); break;
            case "b":
                koma = new Kaku(0); break;
            case "+b":
                koma = new Kaku(0);
                koma.growup(); break;
            case "B":
                koma = new Kaku(1); break;
            case "+B":
                koma = new Kaku(1);
                koma.growup(); break;
            case "r":
                koma = new Hisha(0); break;
            case "+r":
                koma = new Hisha(1);
                koma.growup(); break;
            case "R":
                koma = new Hisha(1); break;
            case "+R":
                koma = new Hisha(1);
                koma.growup(); break;
            case "p":
                koma = new Hu(0); break;
            case "+p":
                koma = new Hu(0);
                koma.growup(); break;
            case "P":
                koma = new Hu(1); break;
            case "+P":
                koma = new Hu(1);
                koma.growup(); break;
             
            default:
                koma = null;
        }
        return koma;
    }
    
    public String translatePosition(){
        String r = Integer.toString(9-position[1]);
        String s;
        int t = position[0];
        if(t==0) s="一";
        else if(t==1) s="二";
        else if(t==2) s="三";
        else if(t==3) s="四";
        else if(t==4) s="五";
        else if(t==5) s="六";
        else if(t==6) s="七";
        else if(t==7) s="八";
        else s="九";
        return r+s;
    }
    
    public String toString(){
        String str = translatePosition();//+":"+code;
        System.out.println(koma);
        return str;
    }
}

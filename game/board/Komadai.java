package ShougiGUI.game.board;

import ShougiGUI.game.board.mass.koma.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class Komadai{
    private final String[] names = {"飛","角","金","銀","桂","香","歩"};
    private Mass[] mass = new Mass[7]; //順に飛、角、金、銀、桂、香、歩
    private VBox row;
    private HBox[] line = new HBox[3];
    
    public Komadai(int owner,String str){
        Koma[] koma = new Koma[7];
        Integer[] quantity = getQuantity(owner,str);
        for(int i=0;i<7;i++) koma[i] = numToKoma(i,owner);
        boolean reverse = false;
        if(owner == 1) reverse = true;
        for(int i=0;i<line.length;i++) line[i] = new HBox();
        if(reverse){
            mass[6] = new Mass(koma[6],6,quantity[6]);
            mass[6].setText(Integer.toString(quantity[6])+names[6]);
            line[2].getChildren().add(mass[6]);
        }
        
        for(int i=0;i<line.length;i++){
            int v=i;
            for(int j=0;j<2;j++){
                int h=j;
                if(reverse){ v=2-i; h=1-j; }
                int index = v*2+h;
                mass[index] = new Mass(koma[index],index,quantity[index]);
                mass[index].setText(Integer.toString(quantity[index])+names[index]);
                line[v].getChildren().add(mass[index]);
            }
        }
        
        if(reverse == false){
            mass[6] = new Mass(koma[6],6,quantity[6]);
            mass[6].setText(Integer.toString(quantity[6])+names[6]);
            line[2].getChildren().add(mass[6]);
        }
        
        row = new VBox();
        for(int i=0;i<line.length;i++){
            int num = i; row.setAlignment(Pos.BOTTOM_CENTER);
            if(reverse){num = 2-i; row.setAlignment(Pos.TOP_CENTER);}
            row.getChildren().add(line[num]);
            line[num].setAlignment(Pos.CENTER);
        }
    }
    
    public Komadai(int owner){ this(owner,"-"); }
    
    public Mass getMass(int index){ return mass[index]; }
    public VBox getVBox(){ return row; }
    public HBox getHBox(int i){ return line[i]; }
    public void add(Koma koma){
        int num = komaToNum(koma);
        mass[num].addPosY();
        mass[num].setText(Integer.toString(mass[num].getPosY())+names[num]);
    }
    public void dis(Koma koma){
        int num = komaToNum(koma);
        mass[num].disPosY();
        mass[num].setText(Integer.toString(mass[num].getPosY())+names[num]);
    }
    
    public Koma numToKoma(int num,int owner){
        Koma koma;
        switch(num){
            case 0:
                koma = new Hisha(owner); break;
            case 1:
                koma = new Kaku(owner); break;
            case 2:
                koma = new Kin(owner); break;
            case 3:
                koma = new Gin(owner); break;
            case 4:
                koma = new Keima(owner); break;
            case 5:
                koma = new Kyou(owner); break;
            case 6:
                koma = new Hu(owner); break;
            default:
                koma = null;
        }
        return koma;
    }
    
    public int komaToNum(Koma koma){
        int num;
        String name = koma.getName();
        switch(name){
            case "飛":
                num = 0; break;
            case "龍":
                num = 0; break;
            case "角":
                num = 1; break;
            case "馬":
                num = 1; break;
            case "金":
                num = 2; break;
            case "銀":
                num = 3; break;
            case "成銀":
                num = 3; break;
            case "桂":
                num = 4; break;
            case "成桂":
                num = 4; break;
            case "香":
                num = 5; break;
            case "成香":
                num = 5; break;
            case "歩":
                num = 6; break;
            case "と金":
                num = 6; break;
            default:
                num = -1;
        }
        return num;
    }
    
    public Integer[] getQuantity(int bw,String str){
        Integer[] quantity = new Integer[7];
        boolean mode = true;
        if(bw == 1) mode = false;
        for(int i=0;i<quantity.length;i++) quantity[i] = 0;
        int k=0;
        int num;
        String s;
        while(k<str.length()){
            num = 1;
            s = str.substring(k,k+1);
            if (Banjou.isNumber(s)){
                num = Integer.parseInt(s);
                k+=1;
                s = str.substring(k,k+1);
            }
            if(mode){
                switch(s){
                    case "R":
                        quantity[0] = num; break;
                    case "B":
                        quantity[1] = num; break;
                    case "G":
                        quantity[2] = num; break;
                    case "S":
                        quantity[3] = num; break;
                    case "N":
                        quantity[4] = num; break;
                    case "L":
                        quantity[5] = num; break;
                    case "P":
                        quantity[6] = num; break;
                }
            }else{
                switch(s){
                    case "r":
                        quantity[0] = num; break;
                    case "b":
                        quantity[1] = num; break;
                    case "g":
                        quantity[2] = num; break;
                    case "s":
                        quantity[3] = num; break;
                    case "n":
                        quantity[4] = num; break;
                    case "l":
                        quantity[5] = num; break;
                    case "p":
                        quantity[6] = num; break;
                }
            }
            k+=1;
        }
        return quantity;
    }
}

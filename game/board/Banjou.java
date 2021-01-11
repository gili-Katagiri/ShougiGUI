package ShougiGUI.game.board;

import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.control.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Banjou{
    private Mass[][] mass = new Mass[9][9];
    private GridPane gp = new GridPane();
    
    public Banjou(){
        this("lnsgkgsnl/1b5r1/ppppppppp/9/9/9/PPPPPPPPP/1R5B1/LNSGKGSNL");
    }
    
    public Banjou(String kyokumen){
        int k=0;
        int num;
        String s;
        for (int i=8; i>=0; i--){
            for (int j=0; j<9; j++){
                s = kyokumen.substring(k,k+1);
                if (s.equals("+")){
                    s = kyokumen.substring(k,k+2);
                    k++;
                    mass[i][j] = new Mass(s,i,j);
                }
                else if (isNumber(s)){
                    num = Integer.parseInt(s);
                    for(int l=j;l<j+num;l++){
                        mass[i][l] = new Mass(i,l);
                    }
                    j = j+num-1;
                }
                else{
                    mass[i][j] = new Mass(s,i,j);
                }
                k+=1;
            }
            k+=1;
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                gp.add(mass[i][j],j,i);
            }
            gp.getColumnConstraints().add(new ColumnConstraints(56));
            gp.getRowConstraints().add(new RowConstraints(67));
        }
    }
    
    public Mass getMass(int i,int j){ return mass[i][j]; }
    public GridPane getGp(){ return gp; }
    public static boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    /*public String toString(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.println(mass[i][j]);
            }
        }
        String str="+-------------------+\n";
        for(int i=0;i<9;i++){
            str+="| ";
            for(int j=0;j<9;j++){
                if(mass[i][j].getCode().isEmpty()){
                    str+="  ";
                }else{
                    str=str+mass[i][j].getCode()+" ";
                }
            }
            str += "|\n";
        }
        str+="+-------------------+";
        return str;
    }*/
}

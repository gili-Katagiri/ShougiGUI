package ShougiGUI.game.board;

import ShougiGUI.game.control.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class BoardBase{
    private Banjou shougiban;
    private Komadai[] komadai = new Komadai[2];
    
    public BoardBase(String banjou,String komadai){
        //String[] str = kyokumen.split(" ",0);
        //for(String s:str) System.out.println(s);
        shougiban = new Banjou(banjou);
        for(int i=0;i<this.komadai.length;i++) this.komadai[i] = new Komadai(i,komadai);
    }
    public BoardBase(){ this("lnsgkgsnl/1b5r1/ppppppppp/9/9/9/PPPPPPPPP/1R5B1/LNSGKGSNL","-") ;}
    public Banjou getShougiban(){ return shougiban; }
    //public Status getStatus(){ return status; }
    public Komadai[] getKomadai(){ return komadai; }
    public Komadai getKomadai(int i){ return komadai[i]; }
}

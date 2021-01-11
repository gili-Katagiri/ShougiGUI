package ShougiGUI.game.control;

import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.board.mass.koma.*;
import java.util.ArrayList;

public class Oute{
    private Integer[] position = {0,0};
    private boolean oute;
    
    public Oute(Banjou shougiban,int bw){
        setOute(shougiban,bw);
    }
    
    public boolean getOute(){ return oute; }
    public void setOute(Banjou shougiban,int bw){
        setPosition(shougiban,bw);
        this.oute = decide(shougiban,bw);
    }
    
    public Integer[] whereKing(Banjou shougiban,int bw){
        for(int i=0;i<9;i++) for(int j=0;j<9;j++){
            Mass tmp = shougiban.getMass(i,j);
            if(tmp.getKoma() == null) continue;
            else{
                Koma koma = tmp.getKoma();
                if(koma.getName().equals("玉") && koma.getOwner() == bw){
                    return tmp.getPosition();
                }
            }
        }
        Integer[] re = {0,0};
        return re;
    }
    
    public void setPosition(Banjou shougiban,int bw){
        Integer[] pos = whereKing(shougiban,bw);
        this.position = pos;
    }
    public Integer[] getPosition(){ return position; }
    
    public boolean decide(Banjou shougiban,int bw){
        //System.out.println("king_position:("+position[0]+","+position[1]+")");
        boolean[] deg = new boolean[9];
        for(int i=0;i<9;i++){deg[i] = true;}
        Integer[] len = {8,8};
        Gyoku god = new Gyoku(bw);
        god.setKoma("神",false,deg,len);
        Mass mass = new Mass(god,position[0],position[1]);
        ArrayList<Integer[]> enemyList = Move.mayMove(shougiban,mass,false);
        for(Integer[] pos:enemyList){
            //System.out.println("("+pos[0]+","+pos[1]+")");
            ArrayList<Integer[]> enemyReach = Move.mayMove(shougiban,shougiban.getMass(pos[0],pos[1]),true);
            for(Integer[] reach:enemyReach) if(position[0] == reach[0] && position[1] == reach[1]) return true;
        }
        return false;
    }
    public String toString(){
        String str;
        str = " King:("+position[0]+","+position[1]+") oute:"+oute;
        return str;
    }
}

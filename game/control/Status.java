package ShougiGUI.game.control;

import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.board.mass.koma.*;
//import ShougiGUI.Game;
import java.util.ArrayList;

public class Status{
    private int bw;
    private int time;
    private boolean preMove;
    private boolean sammon;
    private Mass preMass;
    private ArrayList<Integer[]> list = new ArrayList<Integer[]>();
    private Oute[] oute = new Oute[2];
    
    public Status(Banjou shougiban,String bw,String time){
        if(bw.equals("b")) this.bw = 0;
        else this.bw = 1;
        this.time = Integer.parseInt(time);
        preMove = false;
        sammon = false;
        for(int i=0;i<2;i++) oute[i] = new Oute(shougiban,i);
    }
    public Status(Banjou shougiban){ this(shougiban,"b","1"); }
    
    public int getBw(){ return bw; }
    public void changeBw(){ bw = (bw+1)%2; }
    public boolean getPreMove(){ return preMove; }
    public boolean getSammon(){ return sammon; }
    public void setPreMove(){ this.preMove = true; }
    public void setPreMass(Mass mass){ this.preMass = mass; }
    public Mass getPreMass(){ return preMass; }
    public boolean inList(Integer[] distination){
        for(Integer[] position:list){
            if(position[0] == distination[0] && position[1] == distination[1]) return true;
        }
        return false;
    }
    public ArrayList<Integer[]> getList(){ return list; }
    public Oute getOute(int bw){ return oute[bw]; }
    public void reset(){
        System.out.println("reset");
        this.list.clear();
        preMove = false;
        sammon = false;
        preMass = null;
    }
    public void advance(Banjou shougiban){
        setOute(shougiban);
        changeBw();
        time++;
    }
    
    public void setOute(Banjou shougiban){
        System.out.println("王手をチェックします");
        for(int i=0;i<2;i++) oute[i].setOute(shougiban,i);
    }
    
    public void setList(ArrayList<Integer[]> list,boolean sammon){
        for(Integer[] data:list) this.list.add(data);
        this.sammon = sammon;
    }
    
    public void printList(){
        for(Integer[] pos:list) System.out.print("("+pos[0]+","+pos[1]+") ");
        System.out.println("");
    }
    
    public String toString(){
        String str = Integer.toString(time)+"手目"+" 手番:";
        if(bw == 0) str += "先手 preMove:";
        else str += "後手 preMove:";
        str = str+preMove; str+="\n";
        for(int i=0;i<2;i++){ str+=oute[i].toString(); }
        return str;
    }
        
}

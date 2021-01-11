package ShougiGUI.game.control;

import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.game.board.mass.koma.*;
import java.util.ArrayList;

public class Move extends Foundation{
    public static ArrayList<Integer[]> mayMove(Banjou ban,Mass mass,boolean mode){
        ArrayList<Integer[]> may = new ArrayList<Integer[]>();
        int x = mass.getPosX(), y = mass.getPosY();
        Koma koma = mass.getKoma();
        int bw = koma.getOwner();
        for(int i=0;i<8;i++){
            if(koma.getDegree(i)){
                int[] vect = vector(i,koma.getOwner());
                for(int j=1;j<=koma.getLength(i%2);j++){
                    Integer[] distination = new Integer[2];
                    distination[0] = x+vect[0]*vect[2]*j; distination[1] = y+vect[1]*j;
                    if(distination[0]<0||distination[1]<0||distination[0]>8||distination[1]>8) break;
                    Mass target = ban.getMass(distination[0],distination[1]);
                    if(target.getKoma() == null){
                        if(mode) may.add(distination);
                    }else{
                        Koma exist = target.getKoma();
                        if(exist.getOwner() == bw) break;
                        else may.add(distination); break;
                    }
                }
            }
        }
        if(koma.getDegree(8)){
            int[] vect = vector(8,koma.getOwner());
            int flag = 1;
            do{
                Integer[] distination = new Integer[2];
                distination[0] = x+vect[0]*vect[2]; distination[1] = y+vect[1]*flag;
                if(distination[0]>=0&&distination[1]>=0&&distination[0]<=8&&distination[1]<=8){
                    Mass target = ban.getMass(distination[0],distination[1]);
                    if(target.getKoma() == null){
                        if(mode) may.add(distination);
                    }else{
                        Koma exist = target.getKoma();
                        if(exist.getOwner() != bw) may.add(distination);
                    }
                }
                flag = flag*(-1);
            }while(flag != 1);
        }
        return may;
    }
    
    public static ArrayList<Integer[]> mayAppear(Banjou shougiban,Mass mass){
        ArrayList<Integer[]> list = new ArrayList<Integer[]>();
        Koma koma = mass.getKoma();
        int limit = 0,bw = koma.getOwner();
        boolean mode = false;
        if(bw == 1) mode = true;
        String name = koma.getName();
        if(name.equals("桂")) limit = 2;
        else if(name.equals("香")||name.equals("歩")) limit = 1;
        boolean[] nihu = new boolean[9]; for(int i=0;i<9;i++) nihu[i] = false;
        for(int j=0;j<9;j++){
            if(name.equals("歩")){
                for(int i=limit;i<9;i++){
                    int num = i;
                    if(mode) num = 8-i;
                    Mass tmp = shougiban.getMass(num,j);
                    if(tmp.getKoma() == null) continue;
                    else if(tmp.getKoma().getName().equals("歩") && tmp.getKoma().getOwner() == bw) nihu[j] = true;
                }
            }
            if(nihu[j]) continue;
            for(int i=limit;i<9;i++){
                int num = i;
                if(mode) num = 8-i;
                if(shougiban.getMass(num,j).getKoma()==null){
                    Integer[] position = {num,j};
                    list.add(position);
                }
            }
        }
        return list;
    }
    
    public static int[] vector(int degree,int owner){
        int[] vect = new int[3];//[0]に下方向、[1]に右方向、[2]にbw定数
        if(owner==0) vect[2]=1;
        else vect[2]=-1;
        switch(degree){
            case 0:
                vect[0]=-1; vect[1]=0; break;
            case 1:
                vect[0]=-1; vect[1]=-1; break;
            case 2:
                vect[0]=0; vect[1]=-1; break;
            case 3:
                vect[0]=1; vect[1]=-1; break;
            case 4:
                vect[0]=1; vect[1]=0; break;
            case 5:
                vect[0]=1; vect[1]=1; break;
            case 6:
                vect[0]=0; vect[1]=1; break;
            case 7:
                vect[0]=-1; vect[1]=1; break;
            case 8:
                vect[0]=-2; vect[1]=1; break;
            default:
                vect[0]=0; vect[1]=1; break;
        }
        return vect;
    }
}

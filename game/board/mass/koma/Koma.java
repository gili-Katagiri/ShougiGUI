package ShougiGUI.game.board.mass.koma;

public abstract class Koma implements Cloneable{
    private String name;
    private boolean growable;
    private boolean[] moveDegree = {false,false,false,false,false,false,false,false,false} ;
    private Integer[] moveLength = {0,0};
    
    public Koma(){ this("no name"); }
        
    
    public Koma(String name){
        this.name = name;
        if(name.equals("金") || name.equals("玉")){
            this.growable = false;
            //System.out.println(name);
        }else{
            this.growable = true;
            //System.out.println(name+growable);
        }
    }
    
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public void setGrowable(boolean grw){ growable = grw; }
    public boolean getGrowable(){ return growable; }
    public void setDegree(boolean[] deg){
        for(int i=0;i<deg.length;i++) moveDegree[i] = deg[i];
    }
    public boolean getDegree(int i){ return moveDegree[i]; }
    public void setLength(Integer[] len){
        for(int i=0;i<2;i++) moveLength[i] = len[i];
    }
    public int getLength(int i){ return moveLength[i]; }
    
    public void setKoma(String name,boolean growable,boolean[] deg,Integer[] len){
        setName(name); setGrowable(growable); setDegree(deg); setLength(len);
    }
    
    public abstract void growup();
    //public abstract Integer[] getPosition();
    public abstract int getOwner();
    public abstract boolean getGrow();
    
    @Override
    public Koma clone(){
        Koma koma;
        try {
            koma = (Koma)super.clone();
        } catch (CloneNotSupportedException ce){
            throw new RuntimeException();
        }
        koma.setKoma(name,growable,moveDegree,moveLength);
        return koma;
    }
    
    public String moveTest(){
        String str = "十字方向:"+moveLength[0]+" X方向:"+moveLength[1]+" ";
        for(boolean t:moveDegree){
            if(t){
                str += "1 ";
            }else{
                str += "0 ";
            }
        }
        return str;
    }
    
    public void gyoku(){
        moveLength[0] = 1;
        moveLength[1] = 1;
        for(int k=0;k<8;k++){ moveDegree[k] = true; }
    }
    
    public void gold(){
        moveLength[0] = 1;
        moveLength[1] = 1;
        for(int k=0;k<8;k++){ if(k != 3&&k != 5){ moveDegree[k] = true; } }
    }
    
    public void silver(){
        moveLength[0] = 1;
        moveLength[1] = 1;
        for(int k=0;k<8;k++){ if(k != 2&&k != 4&&k != 6){ moveDegree[k] = true; } }
    }
    
    public void knight(){
        moveLength[0] = 0;
        moveLength[1] = 0;
        moveDegree[8] = true;
    }
    
    public void lance(){
        moveLength[0] = 8;
        moveLength[1] = 0;
        moveDegree[0] = true;
    }
    
    public void porn(){
        moveLength[0] = 1;
        moveLength[1] = 0;
        this.moveDegree[0] = true;
    }
    
    public void rook(){
        moveLength[0] = 8;
        moveLength[1] = 0;
        for(int k=0;k<4;k++){ this.moveDegree[k*2] = true; }
    }
    
    public void bishop(){
        moveLength[0] = 0;
        moveLength[1] = 8;
        for(int k=0;k<4;k++){ moveDegree[k*2+1] = true; }
    }
    
    public void ryu(){
        moveLength[0] = 8;
        moveLength[1] = 1;
        for(int k=0;k<8;k++){ moveDegree[k] = true; }
    }
    
    public void uma(){
        moveLength[0] = 1;
        moveLength[1] = 8;
        for(int k=0;k<8;k++){ moveDegree[k] = true; }
    }
    
    public void reset(){
        name = "no name";
        moveLength[0] = 0;
        moveLength[1] = 0;
        for(int k=0;k<9;k++){ moveDegree[k] = false; }
    }
    
    public String toString(){
        String str = this.name+" growable:"+growable+" "+moveTest();
        return str;
    }
}
    

package ShougiGUI.game.board.mass.koma;

public class Gyoku extends Koma{
    private int owner;
    
    public Gyoku(Integer owner){
        super("玉");
        this.owner = owner;
        gyoku();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return false; }
    public void growup(){}
    
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Gyoku clone(){
        Gyoku k = (Gyoku)super.clone();
        k.setOwner(owner);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner;
    }
}

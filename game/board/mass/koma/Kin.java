package ShougiGUI.game.board.mass.koma;

public class Kin extends Koma{
    private int owner;
    
    public Kin(Integer owner){
        super("金");
        this.owner = owner;
        gold();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return false; }
    public void growup(){}
    
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Kin clone(){
        Kin k = (Kin)super.clone();
        k.setOwner(owner);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner;
    }
}

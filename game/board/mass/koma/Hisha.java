package ShougiGUI.game.board.mass.koma;

public class Hisha extends Koma{
    private int owner;
    private boolean grow;
    
    public Hisha(Integer owner){
        super("飛");
        this.owner = owner;
        rook();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    @Override
    public void growup(){
        if(super.getGrowable()){
            reset(); setName("龍"); ryu();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Hisha clone(){
        Hisha k = (Hisha)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

package ShougiGUI.game.board.mass.koma;

public class Hu extends Koma{
    private int owner;
    private boolean grow;
    
    public Hu(Integer owner){
        super("歩");
        this.owner = owner;
        porn();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    @Override
    public void growup(){
        if(super.getGrowable()){
            reset(); setName("と金"); gold();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Hu clone(){
        Hu k = (Hu)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

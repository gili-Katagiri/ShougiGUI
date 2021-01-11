package ShougiGUI.game.board.mass.koma;

public class Gin extends Koma{
    private int owner;
    private boolean grow;
    
    public Gin(Integer owner){
        super("銀");
        this.owner = owner;
        silver();
    }
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    public void growup(){
        if(super.getGrowable()){
            reset(); setName("成銀"); gold();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Gin clone(){
        Gin k = (Gin)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

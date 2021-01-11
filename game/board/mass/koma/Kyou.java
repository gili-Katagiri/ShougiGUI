package ShougiGUI.game.board.mass.koma;

public class Kyou extends Koma{
    private int owner;
    private boolean grow;
    
    public Kyou(Integer owner){
        super("香");
        this.owner = owner;
        grow = false;
        lance();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    @Override
    public void growup(){
        if(getGrowable()){
            reset();
            setName("成香");
            gold();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Kyou clone(){
        Kyou k = (Kyou)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

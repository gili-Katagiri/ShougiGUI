package ShougiGUI.game.board.mass.koma;

public class Keima extends Koma{
    private int owner;
    private boolean grow;
    
    public Keima(Integer owner){
        super("桂");
        this.owner = owner;
        knight();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    @Override
    public void growup(){
        if(super.getGrowable()){
            reset(); setName("成桂"); gold();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Keima clone(){
        Keima k = (Keima)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

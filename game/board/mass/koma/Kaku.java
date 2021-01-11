package ShougiGUI.game.board.mass.koma;

public class Kaku extends Koma{
    private int owner;
    private boolean grow;
    
    public Kaku(Integer owner){
        super("角");
        this.owner = owner;
        bishop();
    }
    
    public int getOwner(){ return owner; }
    public boolean getGrow(){ return grow; }
    
    @Override
    public void growup(){
        if(super.getGrowable()){
            reset(); setName("馬"); uma();
            this.grow = true;
        }else{
            System.out.println("growable = false");
        }
        setGrowable(false);
    }
    
    public void setGrow(boolean grow){ this.grow = grow; }
    public void setOwner(int owner){ this.owner = owner; }
    
    @Override
    public Kaku clone(){
        Kaku k = (Kaku)super.clone();
        k.setOwner(owner); k.setGrow(true);
        return k;
    }
    
    @Override
    public String toString(){
        System.out.println(super.toString());
        return " ->owner:"+owner+" grow:"+grow;
    }
}

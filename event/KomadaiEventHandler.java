package ShougiGUI.event;

import javafx.event.*;
import ShougiGUI.game.control.*;
import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;

public class KomadaiEventHandler implements EventHandler<ActionEvent>{
    private Banjou shougiban;
    private Status status;
    public KomadaiEventHandler(Banjou shougiban,Status status){
        this.shougiban = shougiban;
        this.status = status;
    }
    public void handle(ActionEvent e){
        Mass tmp = (Mass) e.getSource();
        if(status.getPreMove()){
            System.out.println("preMove:true");
            status.reset();
        }else{
            System.out.println("preMove:false");
            if(tmp.getKoma().getOwner() == status.getBw() && tmp.getPosY()>0){
                System.out.println("登録します");
                status.setPreMass(tmp);
                status.setList(Move.mayAppear(shougiban,tmp),true);
                status.setPreMove();
                status.printList();
            }else{
                status.reset();
            }
        }
    }
}

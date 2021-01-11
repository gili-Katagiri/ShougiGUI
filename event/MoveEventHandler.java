package ShougiGUI.event;

import javafx.event.*;
import ShougiGUI.game.control.*;
import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
//import javafx.collections.*;

public class MoveEventHandler implements EventHandler<ActionEvent>{
    private BoardBase bb;
    private Status status;
    
    public MoveEventHandler(BoardBase bb,Status status){
        this.bb = bb;
        this.status = status;
    }
    
    public void handle(ActionEvent e){
        Mass tmp = (Mass) e.getSource();
        boolean grow = false;
        if(status.getPreMove()){
            System.out.println("preMove:true");
            System.out.println(status.getPreMass());
            if(status.inList(tmp.getPosition())){
                if(Move.enemyTeritory(status.getPreMass(),tmp) && status.getSammon()==false){
                    System.out.println("敵地です");
                    Alert dialog = new Alert(Alert.AlertType.NONE);
                    dialog.setTitle("成りますか?");
                    ButtonType[] controlButton = new ButtonType[3];
                    controlButton[0] = new ButtonType("成る",ButtonBar.ButtonData.YES);
                    controlButton[1] = new ButtonType("不成",ButtonBar.ButtonData.NO);
                    controlButton[2] = new ButtonType("戻る",ButtonBar.ButtonData.CANCEL_CLOSE);
                    for(int i=0;i<3;i++) dialog.getButtonTypes().add(controlButton[i]);
                    Optional<ButtonType> res = dialog.showAndWait();
                    if(res.get().getButtonData() == ButtonBar.ButtonData.YES){
                        System.out.println("成ります");
                        grow = true;
                    }else if(res.get().getButtonData() == ButtonBar.ButtonData.NO){
                        System.out.println("成りません");
                        grow = false;
                    }else{
                        System.out.println("キャンセル");
                        status.reset();
                        return;
                    }
                }
                if(tmp.getKoma() == null){
                    if(status.getSammon()){
                        System.out.println("召喚します");
                        Move.rebirth(status.getPreMass(),tmp,bb.getKomadai());
                        status.reset();
                        status.advance(bb.getShougiban());
                    }else{
                        System.out.println("空移動します");
                        Move.move(status.getPreMass(),tmp,bb.getKomadai(),grow);
                        status.reset();
                        status.advance(bb.getShougiban());
                    }
                }else if(tmp.getKoma().getOwner() == status.getBw()){
                    System.out.println("味方です");
                    status.reset();
                }else{
                    System.out.println("殺移動します");
                    Move.move(status.getPreMass(),tmp,bb.getKomadai(),grow);
                    status.reset();
                    status.advance(bb.getShougiban());
                }
            }else{
                System.out.println("リストに入っていません");
                status.reset();
            }
        }else{
            System.out.println("preMove:false");
            if(tmp.getKoma() == null) status.reset();
            else if(tmp.getKoma().getOwner() == status.getBw()){
                System.out.println("登録します");
                status.setPreMass(tmp);
                status.setList(Move.mayMove(bb.getShougiban(),tmp,true),false);
                status.setPreMove();
                status.printList();
            }else{
                status.reset();
            }
        }
    }
}

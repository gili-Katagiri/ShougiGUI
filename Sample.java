package ShougiGUI;

import ShougiGUI.game.board.*;
import ShougiGUI.game.board.mass.*;
import ShougiGUI.event.*;
//import ShougiGUI.game.board.mass.koma.*;
import ShougiGUI.game.control.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.geometry.*;
//import javafx.geometry.*;


public class Sample extends Application{
    
    private final String kyokumen = "3k3nl/4gs1r1/1+L1p5/3+b1pN1p/3n3p1/S1P3P2/lPKP1+b1PP/2GR5/s1G5+P w Sgsl7p 119";
    private String[] str = kyokumen.split(" ",0);
    private BoardBase bb = new BoardBase();//(str[0],str[2]);
    private Banjou bj = bb.getShougiban();
    private Status status = new Status(bj);//(bj,str[1],str[3]);
    //private Komadai bk = bb.getKomadai(0);
    //private Komadai wk = bb.getKomadai(1);
    
    public static void main(String[] args){
        launch(args);
    }
    public void start(Stage stage)throws Exception{
        BorderPane bp = new BorderPane();
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) bj.getMass(i,j).setOnAction(new MoveEventHandler(bb,status));
        for(int i=0;i<2;i++) for(int j=0;j<7;j++){
            bb.getKomadai(i).getMass(j).setOnAction(new KomadaiEventHandler(bj,status));
        }
        bp.setCenter(bj.getGp());
        bp.setLeft(bb.getKomadai(1).getVBox()); bp.setRight(bb.getKomadai(0).getVBox());
        BorderPane.setMargin(bj.getGp(),new Insets(0,20,0,20));
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.setTitle("将棋");
        stage.show();
    }
}

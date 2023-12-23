package Piece;
import javafx.scene.layout.Pane;
public class Advisor extends Piece{
	public Advisor(double x, double y, double alpha, char side, Pane pane){
		super(x, y, alpha, side, pane);
		if(side == 'R'){
			image_view = createImageView("/Images/Pieces/red_advisor.png");
		}
		else{
			image_view = createImageView("/Images/Pieces/black_advisor.png");
		}
		setupImageView();
	}

	public String toString(){
		return "Ad";
	}
}
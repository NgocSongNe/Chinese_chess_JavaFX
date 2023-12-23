package Piece;
import javafx.scene.layout.Pane;
public class Chariot extends Piece{
	public Chariot(double x, double y, double alpha, char side, Pane pane){
		super(x, y, alpha, side, pane);
		if(side == 'R'){
			image_view = createImageView("/Images/Pieces/red_chariot.png");
		}
		else{
			image_view = createImageView("/Images/Pieces/black_chariot.png");
		}
		setupImageView();
	}

	public String toString(){
		return "Ch";
	}
}
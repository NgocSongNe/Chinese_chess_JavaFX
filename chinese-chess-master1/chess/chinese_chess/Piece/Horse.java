package Piece;
import javafx.scene.layout.Pane;
public class Horse extends Piece{
	public Horse(double x, double y, double alpha, char side, Pane pane){
		super(x, y, alpha, side, pane);
		if(side == 'R'){
			image_view = createImageView("/Images/Pieces/red_horse.png");
		}
		else{
			image_view = createImageView("/Images/Pieces/black_horse.png");
		}
		setupImageView();
	}

	public String toString(){
		return "Ho";
	}
}
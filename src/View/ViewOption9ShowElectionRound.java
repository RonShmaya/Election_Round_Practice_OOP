package View;

import java.util.List;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewOption9ShowElectionRound extends ViewOption {
	private List<Integer> typeResult;
	public ViewOption9ShowElectionRound(Pane pane, MainView mainViewListener) {
		super(pane, "Election Round Result", mainViewListener);
		if (!(mainViewListener.getIsWasElection())) {
			msgUser.setText("Election Not Finish Yet");
			borderpane.setCenter(msgUser);
		} else {
			mainViewListener.uiAskForResultElection();
			String[] ans = new String[2];
			ans = mainViewListener.getElectionResult();
			Label result = new Label(ans[0]);
			Label resultTotal = new Label(ans[1]);
			borderpane.setCenter(result);
			VBox total=new VBox();
			total.getChildren().add(resultTotal);
			borderpane.setRight(total);
			mainViewListener.getTypePartyResult();
			typeResult=mainViewListener.getTypePartyResultToOption();
			GridPane showTypes=new GridPane();
			showTypes.setPadding(new Insets(10));
			showTypes.setHgap(10);
			showTypes.setVgap(10);
			Label leftLabel=new Label("Left:"+typeResult.get(0));
			Label centerLabel=new Label("Center:"+typeResult.get(1));
			Label rightLabel=new Label("Right:"+typeResult.get(2));
			Rectangle left=new Rectangle(15, typeResult.get(0)*25);
			Rectangle center=new Rectangle(15, typeResult.get(1)*25);
			Rectangle right=new Rectangle(15, typeResult.get(2)*25);
			left.setFill(Color.ANTIQUEWHITE);
			center.setFill(Color.GREEN);
			right.setFill(Color.DARKMAGENTA);
			left.setStrokeWidth(3);
			center.setStrokeWidth(3);
			right.setStrokeWidth(3);
			VBox leftBox=new VBox();
			VBox centerBox=new VBox();
			VBox rightBox=new VBox();
			leftBox.getChildren().addAll(leftLabel,left);
			centerBox.getChildren().addAll(centerLabel,center);
			rightBox.getChildren().addAll(rightLabel,right);
			showTypes.add(leftBox, 0, 0);
			showTypes.add(centerBox, 1, 0);
			showTypes.add(rightBox, 2, 0);
			total.getChildren().add(showTypes);
		}
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
	}
}

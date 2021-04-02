package View;

import java.util.List;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ViewOption5ShowBallots extends ViewOption {
	private String[] ballotShow;

	public ViewOption5ShowBallots(Pane pane, MainView mainViewListener) {
		super(pane, "Show All Ballots", mainViewListener);
		ballotShow = mainViewListener.getBallotShow();
		proprtay.add(new Label(ballotShow[0]), 0, 0);
		proprtay.add(new Label(ballotShow[1]), 0, 1);
		proprtay.add(new Label(ballotShow[2]), 0, 2);
		proprtay.add(new Label(ballotShow[3]), 0, 3);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
	}

}

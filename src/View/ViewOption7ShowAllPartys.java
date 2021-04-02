package View;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ViewOption7ShowAllPartys extends ViewOption {
	public ViewOption7ShowAllPartys(Pane pane, MainView mainViewListener) {
		super(pane, "Show All Partys", mainViewListener);
		Label show = new Label(mainViewListener.getShowAllParyts());
		borderpane.setBottom(show);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
	}

}

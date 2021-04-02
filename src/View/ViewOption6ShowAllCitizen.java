package View;

import Model.ElectionRound.eChangesUserNeedToKnow;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ViewOption6ShowAllCitizen extends ViewOption {

	public ViewOption6ShowAllCitizen(Pane pane, MainView mainViewListener) {
		super(pane, "Show All Citizen", mainViewListener);
		Label citizen = new Label(mainViewListener.getShowAllCitizen());
		borderpane.setBottom(citizen);
	}

	@Override
	public void mainViewUpdateOption(eChangesUserNeedToKnow ans) {
	}

}

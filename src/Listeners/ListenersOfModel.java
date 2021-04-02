package Listeners;

import java.util.List;

import Exseptions.IdException;
import Kind_Of_Persons.Person;
import Model.Ballot;
import Model.ElectionRound.eChangesUserNeedToKnow;
import Model.Party.eType;

public interface ListenersOfModel {
	void modelUpdateUiMountAndYear(eChangesUserNeedToKnow ans);

	void modelUpdateUiMsgOfActionMenu(eChangesUserNeedToKnow ans);

	void modelSendUiBallotDetails(String address, String className, int ballotId);

	void modelSendUiPartyDetails(String name, eType type);

	void modelSendUiCitizenDetails(String name, String kind, int id);

	void modelUpdateUiBallotFullShow(String[] ballotsString);

	void modelUpdateUiCitizenFullShow(String citizenString);

	void modelUpdateUiPartysFullShow(String partyString);

	void modelUpdateUiElectionWas(boolean answer);

	void modelUpdateUiResultElection(String[] electionResult);

	void modelUpdateUiTypePartysResult(List<Integer> typeNumbers);
}

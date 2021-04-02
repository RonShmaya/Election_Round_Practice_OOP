package Listeners;

import Exseptions.IdException;
import Kind_Of_Persons.Person;
import Model.Party.eType;

public interface ListenersOfUi {
	void uiUpdateModelMountAndYear(int mount, int year);

	void uiUpdateModelBallotDetails(String address, String kind);

	void uiUpdateModelCitizenDetails(Person citizenToAdd);

	void uiUpdateModelPartyDetails(String name, eType type, int year);

	void uiUpdateModelPartyMemberDetails(int idCitzen, int PartyIndex);

	void uiUpdateModelElectionWas();

	void uiUpdateModelWantToVote(int indexOfCitizen, boolean ans);

	void uiUpdateModelSelection(int indexOfCitizen, int indexOfParty);

	void uiAskForResultSelection();

	void uiAskForHardCoded();

	void uiAskFromModelTypesPartysResult();
}

package ControlerAndProgram;

import java.util.List;

import Kind_Of_Persons.Person;
import Listeners.ListenersOfModel;
import Listeners.ListenersOfUi;
import Model.ElectionModel;
import Model.ElectionRound.eChangesUserNeedToKnow;
import Model.Party.eType;
import View.MainView;

public class ElectionController implements ListenersOfModel, ListenersOfUi {
	private MainView electionView;
	private ElectionModel electionModel;

	public ElectionController(ElectionModel model, MainView view) {
		electionModel = model;
		electionView = view;
		electionView.addListener(this);
		electionModel.addListener(this);
	}

	// model update Ui Msg Action
	@Override
	public void modelUpdateUiMountAndYear(eChangesUserNeedToKnow ans) {
		electionView.updateResultYearMountInUi(ans);
	}

	@Override
	public void modelUpdateUiMsgOfActionMenu(eChangesUserNeedToKnow ans) {
		electionView.updateResultMsgFromModel(ans);
	}

	// model update Ui
	@Override
	public void modelUpdateUiResultElection(String[] electionResult) {
		electionView.electionResult(electionResult);
	}

	@Override
	public void modelSendUiBallotDetails(String address, String className, int ballotId) {
		electionView.addBallotMainView(address, className, ballotId);
	}

	@Override
	public void modelSendUiCitizenDetails(String name, String kind, int id) {
		electionView.addVoterMainView(name, id, kind);
	}

	@Override
	public void modelSendUiPartyDetails(String name, eType type) {
		electionView.addPartyMainView(name, type);
	}

	@Override
	public void modelUpdateUiBallotFullShow(String[] ballotShow) {
		electionView.ballotFullShow(ballotShow);
	}

	@Override
	public void modelUpdateUiCitizenFullShow(String citizenString) {
		electionView.citizenFullShow(citizenString);
	}

	@Override
	public void modelUpdateUiPartysFullShow(String partyString) {
		electionView.partysFullShow(partyString);
	}

	@Override
	public void modelUpdateUiElectionWas(boolean answer) {
		electionView.isWasElection(answer);
	}
	
	@Override
	public void modelUpdateUiTypePartysResult(List<Integer> typeNumbers) {
		electionView.modelSendPartysTypeResult(typeNumbers);	
	}
	
	// Ui update Model
	@Override
	public void uiUpdateModelMountAndYear(int mount, int year) {
		modelUpdateUiMountAndYear(electionModel.setMountAndYear(mount, year));
	}

	@Override
	public void uiUpdateModelBallotDetails(String address, String kind) {
		modelUpdateUiMsgOfActionMenu(electionModel.addBallot(address, kind));
	}

	@Override
	public void uiUpdateModelCitizenDetails(Person citizenToAdd) {
		modelUpdateUiMsgOfActionMenu(electionModel.addCitizen(citizenToAdd, citizenToAdd.getBallotId()));
	}

	@Override
	public void uiUpdateModelPartyDetails(String name, eType type, int year) {
		modelUpdateUiMsgOfActionMenu(electionModel.addParty(name, type, year));
	}

	@Override
	public void uiUpdateModelPartyMemberDetails(int idCitzen, int PartyIndex) {
		modelUpdateUiMsgOfActionMenu(electionModel.addMemberParty(idCitzen, 0, PartyIndex));
	}

	@Override
	public void uiUpdateModelElectionWas() {
		electionModel.setElectionSelectWas(true);
	}

	@Override
	public void uiUpdateModelWantToVote(int indexOfCitizen, boolean ans) {
		electionModel.electionRoundwantToVote(indexOfCitizen, ans);
	}

	@Override
	public void uiUpdateModelSelection(int indexOfCitizen, int indexOfParty) {
		electionModel.electionRoundSelections(indexOfCitizen, indexOfParty);
	}

	// ui ask from model
	@Override
	public void uiAskForHardCoded() {
		electionModel.hardCoded();

	}

	@Override
	public void uiAskForResultSelection() {
		electionModel.electionRoundResult();
	}

	@Override
	public void uiAskFromModelTypesPartysResult() {
		electionModel.electionPartysTypeResult();
		
	}

}

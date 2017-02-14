package oisisi.controllers.menuActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import oisisi.Application;

public class RegularViewAction extends AbstractAction {

	private static final long serialVersionUID = 8393411888301644562L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Application.getInstance().getCenterPanelView().switchToRegularView();
	}

}

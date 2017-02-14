package oisisi.controllers.menuActions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import oisisi.Application;

public class CompactViewAction extends AbstractAction {

	private static final long serialVersionUID = -1125053908545705770L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Application.getInstance().getCenterPanelView().switchToCompactView();
	}

}

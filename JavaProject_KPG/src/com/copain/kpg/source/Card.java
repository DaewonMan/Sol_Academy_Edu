package com.copain.kpg.source;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card  {

	public ImageIcon[] il;
	JLabel[] jl;

	public Card() {

		il = new ImageIcon[20];
		jl = new JLabel[20];

		for (int i = 0; i < il.length; i++) {
			il[i] = new ImageIcon("C:\\Edu18\\workspaceJava\\JavaProject_KPG\\src\\" + (i + 1) + ".jpg");
		}
		for (int i = 0; i < jl.length; i++) {
			jl[i] = new JLabel(il[i]);
		}

	}

}

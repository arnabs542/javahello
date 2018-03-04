package test.testreflection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class A extends Object implements ActionListener {
	private int a = 3;
	public Integer b = new Integer(4);

	public A() {
	}

	public A(int id, String name) {
	}

	public int abc(int id, String name) {
		return 0;
	}

	public void actionPerformed(ActionEvent e) {
	}
}

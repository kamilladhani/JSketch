
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;

class DrawShape {

	public Shape shape;
	public Color color;
	public int thickness;
	public Color fill;

	DrawShape(Shape s, Color c, int t) {
		shape = s;
		color = c;
		thickness = t;
		fill = null;
	}

}
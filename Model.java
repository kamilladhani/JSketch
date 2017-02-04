import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
// Need to keep tool, color, and thickness in model

public class Model extends Observable {

	private String curr_tool;
	private Color curr_color;
	private int curr_thickness;
	public ArrayList<DrawShape> shapes;
	public DrawShape selected;
	public Boolean fit_to_window;
	public Dimension canvas_size;
	public Dimension canvas_prev_size;
	public Dimension start_window;
	public JScrollPane jsp;
	public JFrame j;
	
	Model(JFrame f) {
		j = f;
		shapes = new ArrayList<DrawShape>();
		curr_tool = "select";
		curr_color = Color.RED;
		curr_thickness = 5;
		fit_to_window = true;
		setChanged();
	}
	
	public Color getColor() {
		return curr_color;
	}

	public void setColor(Color color) {
		curr_color = color;
		setChanged();
		notifyObservers();
	}

	public int getThickness() {
		return curr_thickness;
	}

	public void setThickness(int thickness) {
		curr_thickness = thickness;
		setChanged();
		notifyObservers();
	}
	
	public String getTool() {
		return curr_tool;
	}

	public void setTool(String tool) {
		curr_tool = tool;
		setChanged();
		notifyObservers();
	} 	

	public void noticePlease() {
		setChanged();
		notifyObservers();
	}

	public void updateScrollPane(CanvasView canvas) {
		if (fit_to_window) {
			int width = (int)canvas_size.getWidth()-((int)start_window.getWidth() - j.getWidth());
			int height = (int)canvas_size.getHeight()-((int)start_window.getHeight() - j.getHeight());
			System.out.println(width);
			System.out.println(height);
			System.out.println("");
			// canvas.setSize(Math.min(width, height), Math.min(width, height)); 
			canvas.setPreferredSize(new Dimension(Math.min(width, height), Math.min(width, height))); 
			jsp.setSize(new Dimension(Math.min(width, height), Math.min(width, height)));
			jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			jsp.revalidate();
			canvas.revalidate();
			System.out.println(Math.min(width, height));
			// jsp.setPreferredSize(new Dimension(Math.min(jsp.getWidth(), jsp.getHeight()), Math.min(jsp.getWidth(), jsp.getHeight())));
			// jsp.revalidate();
		} else {
			canvas.setPreferredSize(canvas_size);
			// newjp.setSize(canvas_size);
			if (canvas_size.getWidth() < canvas.getSize().getWidth() ||
				canvas_size.getHeight() < canvas.getSize().getHeight()) {
				jsp.setSize(canvas_size);	
				jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			} 

			jsp.revalidate();
		}
	}

}

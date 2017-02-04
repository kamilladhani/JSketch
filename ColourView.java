
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;

class ColorView extends JPanel implements Observer {

	// Buttons
	private JButton chooser;
	private OnPressButton b1;
	private OnPressButton b2;
	private OnPressButton b3;
	private OnPressButton b4;
	private OnPressButton b5;
	private OnPressButton b6;
	// the model that this view is showing
	private Model model;

	ColorView(Model model_) {
		// set the model
		model = model_;

		Box vertBox = Box.createVerticalBox();
		// JPanel vertBox = new JPanel();
		// vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.Y_AXIS));

		JPanel toolBox = new JPanel();
		GridLayout g = new GridLayout(0,2,3,3);
		toolBox.setLayout(g);

		b1 = new OnPressButton(Color.RED);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b1.color);
				if (model.selected!=null) {
					model.selected.color = Color.RED;
				}
			}
		});
		b2 = new OnPressButton(Color.ORANGE);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b2.color);
				if (model.selected!=null) {
					model.selected.color = Color.ORANGE;
				}
			}
		});
		b3 = new OnPressButton(Color.YELLOW);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b3.color);
				if (model.selected!=null) {
					model.selected.color = Color.YELLOW;
				}
			}
		});
		b4 = new OnPressButton(Color.GREEN);
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b4.color);
				if (model.selected!=null) {
					model.selected.color = Color.GREEN;
				}
			}
		});
		b5 = new OnPressButton(Color.BLUE);
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b5.color);
				if (model.selected!=null) {
					model.selected.color = Color.BLUE;
				}
			}
		});
		b6 = new OnPressButton(Color.PINK);
		b6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setColor(b6.color);
				if (model.selected!=null) {
					model.selected.color = Color.PINK;
				}
			}
		});


		// Set sizes
		b1.setMinimumSize(new Dimension(10,10));
		b1.setPreferredSize(new Dimension(20,20));
		b1.setMaximumSize(new Dimension(30,30));
		b2.setMinimumSize(new Dimension(10,10));
		b2.setPreferredSize(new Dimension(20,20));
		b2.setMaximumSize(new Dimension(30,30));
		b3.setMinimumSize(new Dimension(10,10));
		b3.setPreferredSize(new Dimension(20,20));
		b3.setMaximumSize(new Dimension(30,30));
		b4.setMinimumSize(new Dimension(10,10));
		b4.setPreferredSize(new Dimension(20,20));
		b4.setMaximumSize(new Dimension(30,30));
		b5.setMinimumSize(new Dimension(10,10));
		b5.setPreferredSize(new Dimension(20,20));
		b5.setMaximumSize(new Dimension(30,30));
		b6.setMinimumSize(new Dimension(10,10));
		b6.setPreferredSize(new Dimension(20,20));
		b6.setMaximumSize(new Dimension(30,30));

		toolBox.add(b1);
		toolBox.add(b2);
		toolBox.add(b3);
		toolBox.add(b4);
		toolBox.add(b5);
		toolBox.add(b6);

		vertBox.add(toolBox);
		
	    chooser = new JButton("Chooser");
	    chooser.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Color color = JColorChooser.showDialog(vertBox, "Select a Color", model.getColor());
	            model.setColor(color);
	            if (model.selected!=null) {
	            	model.selected.color = color;
	           	}
	        }
	    });

	    vertBox.add(chooser);
	    this.add(vertBox);


	}


	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		if (model.getColor()==Color.RED) {
			b1.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b1.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getColor()==Color.ORANGE) {
			b2.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b2.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getColor()==Color.YELLOW) {
			b3.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b3.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getColor()==Color.GREEN) {
			b4.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b4.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getColor()==Color.BLUE) {
			b5.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b5.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getColor()==Color.PINK) {
			b6.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b6.setBorder(UIManager.getBorder("Button.border"));
		}
		// b1.setButtonColor(c1);
		// repaint();
		System.out.println("ColorView: update");	
	}
} 

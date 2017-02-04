
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;

class ThicknessView extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	ThicknessButton b1;
	ThicknessButton b2;
	ThicknessButton b3;
	ThicknessButton b4;

	ThicknessView(Model model_) {
		// set the model
		model = model_;

		Box vertBox = Box.createVerticalBox();
		// this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel thickBox = new JPanel();
		GridLayout g = new GridLayout(0,1);
		g.setVgap(5);
		thickBox.setLayout(g);

		b1 = new ThicknessButton(3);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setThickness(3);
			}
		});

		b2 = new ThicknessButton(5);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setThickness(5);
			}
		});
		b3 = new ThicknessButton(10);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setThickness(10);
			}
		});
		b4 = new ThicknessButton(20);
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.setThickness(20);
			}
		});

		//set sizes:
		b1.setMinimumSize(new Dimension(100, 22));
		b1.setPreferredSize(new Dimension(120, 25));
		b1.setMaximumSize(new Dimension(160, 25));

		b2.setMinimumSize(new Dimension(100, 22));
		b2.setPreferredSize(new Dimension(120, 25));
		b2.setMaximumSize(new Dimension(160, 25));

		b3.setMinimumSize(new Dimension(100, 22));
		b3.setPreferredSize(new Dimension(120, 25));
		b3.setMaximumSize(new Dimension(160, 25));

		b4.setMinimumSize(new Dimension(100, 22));
		b4.setPreferredSize(new Dimension(120, 25));
		b4.setMaximumSize(new Dimension(160, 25));

		thickBox.add(b1);
		thickBox.add(b2);
		thickBox.add(b3);
		thickBox.add(b4);

		vertBox.add(thickBox);
		this.add(vertBox);

		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		
		// addMouseListener(new MouseAdapter() {
		// 		public void mouseClicked(MouseEvent e) {
		// 			model.incrementCounter();
		// 		}
		// });
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		if (model.getThickness()==b1.thickness) {
			b1.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b1.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getThickness()==b2.thickness) {
			b2.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b2.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getThickness()==b3.thickness) {
			b3.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b3.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getThickness()==b4.thickness) {
			b4.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			b4.setBorder(UIManager.getBorder("Button.border"));
		}

		System.out.println("ThicknessView: update");	
	}
} 

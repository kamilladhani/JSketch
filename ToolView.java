
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;

class ToolView extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	//Buttons
	private JButton select;
	private JButton erase;
	private JButton line;
	private JButton circle;
	private JButton rectangle;
	private JButton fill;

	ToolView(Model model_) {

		// create UI

		Box vertBox = Box.createVerticalBox();

		JPanel toolBox = new JPanel();
		GridLayout g = new GridLayout(0,2,3,3);
		toolBox.setLayout(g);

		ImageIcon[] icons = new ImageIcon[6];
		for (int i=0; i<6; i++){
			icons[i]=new ImageIcon(""+(i+1)+".GIF");
		}

		// Make buttons
		select = new JButton(icons[0]);
		select.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("select");
	        }
        });

		erase = new JButton(icons[1]);
		erase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("erase");
	        }
        });

        line = new JButton(icons[2]);
		line.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("line");
	        }
        });

        circle = new JButton(icons[3]);
		circle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("circle");
	        }
        });

        rectangle = new JButton(icons[4]);
		rectangle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("rectangle");
	        }
        });

        fill = new JButton(icons[5]);
		fill.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            model.setTool("fill");
	        }
        });

        // Set sizes
		select.setMinimumSize(new Dimension(10,30));
		select.setPreferredSize(new Dimension(70,30));
		select.setMaximumSize(new Dimension(100,30));
		erase.setMinimumSize(new Dimension(10,10));
		erase.setPreferredSize(new Dimension(20,20));
		erase.setMaximumSize(new Dimension(30,30));
		line.setMinimumSize(new Dimension(10,10));
		line.setPreferredSize(new Dimension(20,20));
		line.setMaximumSize(new Dimension(30,30));
		circle.setMinimumSize(new Dimension(10,10));
		circle.setPreferredSize(new Dimension(20,20));
		circle.setMaximumSize(new Dimension(30,30));
		rectangle.setMinimumSize(new Dimension(10,10));
		rectangle.setPreferredSize(new Dimension(20,20));
		rectangle.setMaximumSize(new Dimension(30,30));
		fill.setMinimumSize(new Dimension(10,10));
		fill.setPreferredSize(new Dimension(20,20));
		fill.setMaximumSize(new Dimension(30,30));

		toolBox.add(select);
		toolBox.add(erase);
		toolBox.add(line);
		toolBox.add(circle);
		toolBox.add(rectangle);
		toolBox.add(fill);

		vertBox.add(toolBox);
		this.add(vertBox);


		// set the model
		model = model_;

	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		if (model.getTool()=="select") {
			select.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			select.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getTool()=="erase") {
			erase.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			erase.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getTool()=="line") {
			line.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			line.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getTool()=="circle") {
			circle.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			circle.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getTool()=="rectangle") {
			rectangle.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			rectangle.setBorder(UIManager.getBorder("Button.border"));
		}
		if (model.getTool()=="fill") {
			fill.setBorder(BorderFactory.createDashedBorder(Color.BLACK)); 
		} else {
			fill.setBorder(UIManager.getBorder("Button.border"));
		}
	}
}

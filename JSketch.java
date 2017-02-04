import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.lang.Math.*;
import java.lang.Double;

// Resources used:
// - PaintDemo -> Icons, General layout
// - hellomvc4 -> MVC Setup (observer pattern)
// - OnPressButton -> Custom buttons for Color palette
// - FontAwesome -> Select tool icon

// TODO:

public class JSketch extends JFrame{

	public JSketch() {	
		super();
		this.setTitle("JSketch");
		// this.setSize(800,600);
		this.setMinimumSize(new Dimension(590,484));
		// this.setPreferredSize(new Dimension(800,600));
		this.getContentPane().setLayout(new BorderLayout());		
		// JFrame frame = new JFrame("JSketch");

		// create Model and initialize it
		Model model = new Model(this);

		// create views
		CanvasView canvas = new CanvasView(model);
		model.addObserver(canvas);
		MenuBarView menu = new MenuBarView(model);
		model.addObserver(menu);
		ToolView tools = new ToolView(model);
		model.addObserver(tools);
		ColorView palette = new ColorView(model);
		model.addObserver(palette);
		ThicknessView thick = new ThicknessView(model);
		model.addObserver(thick);


		// // Make the focus on the canvas (for KeyListener)
  //   	this.requestFocus();
		// this.setFocusable(true);
		// this.addKeyListener( new KeyAdapter() {
	 //      	public void keyPressed(KeyEvent e) {
	 //        	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	 //          		model.selected = null;
	 //          		System.out.println("unselected1");
	 //        	}
	 //        	System.out.println("unseladsaected1");
	 //      	}
	 //    });

		// Set layouts
		this.setJMenuBar(menu);

		JPanel holder = new JPanel();
		holder.setMinimumSize(new Dimension(120, getHeight()));
		holder.setPreferredSize(new Dimension(150, getHeight()));
		holder.setMaximumSize(new Dimension(200, getHeight()));
		holder.setLayout(new BorderLayout());
		holder.setBackground(Color.GRAY);

		JPanel holder2 = new JPanel();
		holder2.setLayout(new BoxLayout(holder2, BoxLayout.Y_AXIS));

		holder2.add(tools);

		JPanel smallPanel = new JPanel();
		smallPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		smallPanel.setSize(getWidth(),50);
		smallPanel.setBackground(Color.GRAY);
		holder2.add(smallPanel);

		holder2.add(palette);

		JPanel smallPanel2 = new JPanel();
		smallPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		smallPanel2.setSize(getWidth(), 50);
		smallPanel2.setBackground(Color.GRAY);
		holder2.add(smallPanel2);

		holder2.add(thick);

		holder.add(holder2, BorderLayout.NORTH);
		this.add(holder, BorderLayout.WEST);

		canvas.setPreferredSize(new Dimension(600, 600));
		model.jsp = new JScrollPane(canvas);
		JScrollPane jsp = model.jsp;
		// JPanel newjp = new JPanel();
		// newjp.add(jsp);
		getContentPane().add(jsp, BorderLayout.CENTER);
		//this.add(canvas, BorderLayout.CENTER); 

		this.addComponentListener(new ComponentListener(){
			public void componentResized(ComponentEvent e){
				model.updateScrollPane(canvas);
			}
            public void componentMoved( ComponentEvent e ) { System.out.println("moved");}
            public void componentShown( ComponentEvent e ) {}
            public void componentHidden( ComponentEvent e ) {}
		});

		menu.viewFull.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		model.fit_to_window = false;
        		model.canvas_size = canvas.getSize();
				model.noticePlease();
        	}
       	});

		JFrame j = this;
		menu.viewFit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		model.fit_to_window = true;
        		model.canvas_size = canvas.getSize();
        		model.start_window = j.getSize();
				model.noticePlease();
        	}
       	});

 		this.pack();
		this.setVisible(true);

		model.canvas_size = canvas.getSize();
		model.canvas_prev_size = canvas.getSize();
		model.start_window = this.getSize();

		// let all the views know that they're connected to the model
		model.notifyObservers();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action unselector = new AbstractAction() {
  //  			public void actionPerformed(ActionEvent e) {
  //  				model.selected = null;
  //  				System.out.println("HIIDIDIT");
		// 	}
		// };
  //       getRootPane().getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "unselect");
  //       getRootPane().getActionMap().put( "unselect", unselector);

		// for (Component c : getRootPane().getComponents()) {
		// 	c.addKeyListener( new KeyAdapter() {
		//       	public void keyPressed(KeyEvent e) {
		//         	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		//           		model.selected = null;
		//           		System.out.println("unselected1");
		//         	}
		//         	System.out.println("unseladsaected1");
		//       	}
		//     });
	 //    }

		// JButton jb1 = new JButton("Components");
		// this.add(jb1, BorderLayout.SOUTH);
		// jb1.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
		// 		showComponentHierarchy();
		// 	}
		// });
		
		// create the window
		// JPanel p = new JPanel(new GridLayout(2,1));
		// frame.getContentPane().add(p);
		// p.add(view);
		
		// p.add(view2);
		
		// frame.setPreferredSize(new Dimension(300,300));
		// frame.pack();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
	} 


	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JSketch js = new JSketch();
			}
		});
	}
}
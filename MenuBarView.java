
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


class MenuBarView extends JMenuBar implements Observer {
	
	// the model that this view is showing
	private Model model;
	JMenu file;
	JMenu view;
	JMenuItem newFile; 
	JMenuItem loadFile;
	JMenuItem saveFile;
	JMenuItem viewFull;
	JMenuItem viewFit;
	
	MenuBarView(Model model_) {
		file = new JMenu("File");
		view = new JMenu("View");

		newFile = new JMenuItem("New");
		loadFile = new JMenuItem("Load");
		saveFile = new JMenuItem("Save");
		viewFull = new JMenuItem("Full Size");
		viewFit = new JMenuItem("Fit to Window");

		file.add(newFile);
		file.add(loadFile);
		file.add(saveFile);
		view.add(viewFull);
		view.add(viewFit);
		this.add(file);
		this.add(view);

		// set the model 
		model = model_;

		newFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		model.selected = null;
        		model.shapes.clear();
        		model.noticePlease();
        	}
        });

		loadFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		model.shapes.clear();
	            JFileChooser jfc = new JFileChooser();
	            int chosen = jfc.showOpenDialog(loadFile);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	try {
			        	File f = jfc.getSelectedFile();
			        	FileReader fr = new FileReader(f.getPath());
			        	BufferedReader br = new BufferedReader(fr);
			        	String s;
			        	while ((s = br.readLine()) != null) {
			        		String[] los = s.split(" ");
			        		Shape sh = null; 
			        		if (los[0].equals("Rectangle")) {
			        			sh = new Rectangle2D.Double(Double.parseDouble(los[4]), Double.parseDouble(los[5]), Double.parseDouble(los[6]), Double.parseDouble(los[7]));
			        		} else if (los[0].equals("Circle")) {
			        			sh = new Ellipse2D.Double(Double.parseDouble(los[4]), Double.parseDouble(los[5]), Double.parseDouble(los[6]), Double.parseDouble(los[7]));
			        		} else if (los[0].equals("Line")) {
			        			sh = new Line2D.Double(Double.parseDouble(los[4]), Double.parseDouble(los[5]), Double.parseDouble(los[6]), Double.parseDouble(los[7]));
			        		}
			        		DrawShape d = new DrawShape(sh, new Color(Integer.parseInt(los[1])), Integer.parseInt(los[2]));
			        		if (!los[3].equals("null")) {
			        			d.fill = new Color(Integer.parseInt(los[3]));
			        		}
			        		model.shapes.add(d);
			        	}
			    		br.close();
			    		model.noticePlease();
			        } catch (IOException ex) {
			        	System.out.println(ex.getMessage());
			    	}
		      	}
	        }
        });

        saveFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            JFileChooser jfc = new JFileChooser();
	            int chosen = jfc.showSaveDialog(saveFile);
		        if (chosen == JFileChooser.APPROVE_OPTION) {
		        	try {
			        	File f = jfc.getSelectedFile();
			        	FileWriter fw = new FileWriter(f.getPath());
			        	BufferedWriter bw = new BufferedWriter(fw);
			        	for (int i=0; i<model.shapes.size(); i++) {
			        		String s = "";
			        		DrawShape ds = model.shapes.get(i);
			        		String scolor = String.valueOf(ds.color.getRGB());
			        		String sfill = (ds.fill==null) ? "null" : String.valueOf(ds.fill.getRGB());
			        		if (model.shapes.get(i).shape instanceof Rectangle2D.Double) {
			        			Rectangle2D.Double r = (Rectangle2D.Double) ds.shape;
			        			s = s + "Rectangle " + 
			        				scolor + " " +
			        				ds.thickness + " " + 
			        				sfill + " " +
			        				Math.round(r.getX()) + " " + 
			        				Math.round(r.getY()) + " " +
			        				Math.round(r.getWidth()) + " " +
			        				Math.round(r.getHeight());
			        		} else if (model.shapes.get(i).shape instanceof Ellipse2D.Double) {
			        			Ellipse2D.Double r = (Ellipse2D.Double) ds.shape;
			        			s = s + "Circle " + 
			        				scolor + " " +
			        				ds.thickness + " " + 
			        				sfill + " " +
			        				r.getX() + " " + 
			        				r.getY() + " " +
			        				r.getWidth() + " " +
			        				r.getHeight();
			        		} else if (model.shapes.get(i).shape instanceof Line2D.Double) {
			        			Line2D.Double r = (Line2D.Double) ds.shape;
			        			s = s + "Line " + 
			        				scolor + " " +
			        				ds.thickness + " " + 
			        				sfill + " " +
			        				r.getX1() + " " + 
			        				r.getY1() + " " +
			        				r.getX2() + " " +
			        				r.getY2();
			        		}

			        		System.out.println(s);
			        		bw.write(s + "\n");
			        		model.noticePlease();
			        	}

			        	bw.close();
			        } catch (IOException ex) {
			        	System.out.println(ex.getMessage());
			    	}
		      	}
	        }
        });
		
	} 

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Menu: update");
	}
} 

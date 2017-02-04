
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;	
import java.util.Observable;
import java.util.Observer;

class CanvasView extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	private int startx;
	private int starty;
	private int endx;
	private int endy;
	private double dragx1=0;
	private double dragy1=0;
	private double dragx2=0;
	private double dragy2=0;
	private Boolean released;

	CanvasView(Model model_) {
		// create UI
		// JPanel mainPanel = new JPanel();
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setBackground(Color.WHITE);
		released = true;

		// set the model
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				released = false;
				startx = e.getX();
    			starty = e.getY();
    			System.out.println(startx + "," + starty);
    			if (model.getTool()=="select") {
    				Boolean didselect = false;
    				for (int i=model.shapes.size()-1; i>=0; i--) {
    					if (((model.shapes.get(i).shape instanceof Rectangle2D.Double) || 
    						(model.shapes.get(i).shape instanceof Ellipse2D.Double)) &&
    						model.shapes.get(i).shape.contains(e.getPoint())) {
    						model.selected = model.shapes.get(i);
    						System.out.println("Clicked a shape");
    						if (model.shapes.get(i).shape instanceof Ellipse2D.Double) {
    							Ellipse2D.Double s = (Ellipse2D.Double) model.shapes.get(i).shape;
	    						dragx1 = s.getX();
	    						dragy1 = s.getY();
	    					} else if (model.shapes.get(i).shape instanceof Rectangle2D.Double){
	    						Rectangle2D.Double s = (Rectangle2D.Double) model.shapes.get(i).shape;
	    						dragx1 = s.getX();
	    						dragy1 = s.getY();
	    					}
	    					model.setColor(model.selected.color);
    						model.setThickness(model.selected.thickness);
    						repaint();
    						didselect = true;
    						break;
    					} else if (model.shapes.get(i).shape instanceof Line2D.Double &&
    							   model.shapes.get(i).shape.intersects(new Rectangle2D.Double(e.getX()-10, e.getY()-10, 20, 20))) {
    						model.selected = model.shapes.get(i);
    						System.out.println("Clicked a line");
    						Line2D.Double s = (Line2D.Double) model.shapes.get(i).shape;
    						dragx1 = s.getX1();
    						dragy1 = s.getY1();
    						dragx2 = s.getX2();
    						dragy2 = s.getY2();
    						model.setColor(model.selected.color);
    						model.setThickness(model.selected.thickness);
    						repaint();
    						didselect = true;
    						break;
    					}
    				}
    				if (!didselect) {
    					model.selected = null;
    					System.out.println("Unselected Shape");
    				}
    			} else if (model.getTool()=="erase") {
    				for (int i=model.shapes.size()-1; i>=0; i--) {
    					if (model.shapes.get(i).shape.contains(e.getPoint())) {
    						model.shapes.remove(i);
    						System.out.println("Removed a shape");
    						repaint();
    						break;
    					}
    				}
    			} else if (model.getTool()=="fill") {
    				for (int i=model.shapes.size()-1; i>=0; i--) {
    					if (model.shapes.get(i).shape.contains(e.getPoint())) {
    						model.shapes.get(i).fill = model.getColor();
    						System.out.println("Filled a shape");
    						repaint();
    						break;
    					}
    				}
    			} 
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				released = true;
				endx = e.getX();
    			endy = e.getY();
    			if (model.getTool()=="rectangle" && startx!=endx && starty!=endy) {
	    			int x1 = startx<=endx ? startx : endx;
					int x2 = startx<=endx ? endx : startx;
					int y1 = starty<=endy ? starty : endy;
					int y2 = starty<=endy ? endy : starty;
					Rectangle2D.Double r = new Rectangle2D.Double(x1, y1, x2-x1, y2-y1);
	    			model.shapes.add(new DrawShape(r, model.getColor(), model.getThickness()));
	    			System.out.println("start:" + startx + "," + starty + "end:"+ endx + "," + endy);
	    		} else if (model.getTool()=="circle" && startx!=endx && starty!=endy) {
					int x1 = startx<=endx ? startx : endx;
					int x2 = startx<=endx ? endx : startx;
					int y1 = starty<=endy ? starty : endy;
					int y2 = starty<=endy ? endy : starty;
					int width = Math.min(x2-x1,y2-y1);
					int height = Math.min(x2-x1,y2-y1);
					int sx = (endx>startx) ? startx : startx-width;
					int sy = (endy>starty) ? starty : starty-height;
					Ellipse2D.Double c = new Ellipse2D.Double(sx, sy, width, height);
					model.shapes.add(new DrawShape(c, model.getColor(), model.getThickness()));
	    		} else if (model.getTool()=="line") {
	    			Line2D.Double l = new Line2D.Double(startx, starty, endx, endy);
	    			model.shapes.add(new DrawShape(l, model.getColor(), model.getThickness()));
	    		}
    			repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				endx = e.getX();
    			endy = e.getY();
    			if (model.getTool()=="select") {
    				if (model.selected == null) {
    					// nothing
    				} else if(model.selected.shape instanceof Line2D.Double) {
    					Line2D.Double s = (Line2D.Double) model.selected.shape;
    					System.out.println(endy-starty);
    					s.setLine(dragx1+(endx-startx),
    					          dragy1+(endy-starty),
    					          dragx2+(endx-startx),
    					          dragy2+(endy-starty));
    				} else if (model.selected.shape instanceof Ellipse2D.Double) {
    					Ellipse2D.Double s = (Ellipse2D.Double) model.selected.shape;
						s.setFrame(dragx1+(endx-startx),
    					           dragy1+(endy-starty),
    					           s.getWidth(),
    					           s.getHeight());
    				} else if (model.selected.shape instanceof Rectangle2D.Double) {
    					Rectangle2D.Double s = (Rectangle2D.Double) model.selected.shape;
    					s.setRect(dragx1+(endx-startx),
    					          dragy1+(endy-starty),
    					          s.getWidth(),
    					          s.getHeight());
    				}
				}
    			System.out.println("mouseDRAG");
    			repaint();
			}
		});

		CanvasView t = this;
		this.addComponentListener(new ComponentListener(){
			public void componentResized(ComponentEvent e){
				Dimension c_size = t.getSize();
				double ratio = c_size.getWidth()/model.canvas_prev_size.getWidth();
				for (int i=0; i<model.shapes.size(); i++) {
					if (model.shapes.get(i).shape instanceof Rectangle2D.Double) {
						Rectangle2D.Double shape = (Rectangle2D.Double) model.shapes.get(i).shape; 
						shape.setFrame(shape.getX() * ratio,
									   shape.getY() * ratio,
									   shape.getWidth() * ratio,
									   shape.getHeight() * ratio);
					} else if (model.shapes.get(i).shape instanceof Ellipse2D.Double) {
						Ellipse2D.Double shape = (Ellipse2D.Double) model.shapes.get(i).shape; 
						shape.setFrame(shape.getX() * ratio,
									   shape.getY() * ratio,
									   shape.getWidth() * ratio,
									   shape.getHeight() * ratio);
					} else if (model.shapes.get(i).shape instanceof Line2D.Double) {
						Line2D.Double shape = (Line2D.Double) model.shapes.get(i).shape; 
						shape.setLine(shape.getX1() * ratio,
									   shape.getY1() * ratio,
									   shape.getX2()* ratio,
									   shape.getY2() * ratio);
					}
				}
				model.canvas_prev_size = c_size;
				model.noticePlease();
			}
			public void componentMoved( ComponentEvent e ) {}
            public void componentShown( ComponentEvent e ) {}
            public void componentHidden( ComponentEvent e ) {}
		});

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i=0; i<model.shapes.size(); i++) {
			System.out.println( model.shapes.get(i).getClass());
			if (model.shapes.get(i).shape instanceof Rectangle2D.Double) {
				Shape shape = model.shapes.get(i).shape; 
				int sx = (int)shape.getBounds().getX();
				int ex = (int)shape.getBounds().getWidth() + (int)shape.getBounds().getX();
				int sy = (int)shape.getBounds().getY();
				int ey = (int)shape.getBounds().getY() + (int)shape.getBounds().getHeight();
				int x1 = sx<=ex ? sx : ex;
				int x2 = sx<=ex ? ex : sx;
				int y1 = sy<=ey ? sy : ey;
				int y2 = sy<=ey ? ey : sy;
		        System.out.println(model.shapes);
		        if (model.shapes.get(i).fill != null) {
		        	g2.setColor(model.shapes.get(i).fill);
		        	g2.fillRect(x1, y1, x2-x1, y2-y1);
		        }
		       	g2.setColor(model.shapes.get(i).color);
		       	if (model.selected == model.shapes.get(i)) {
		        	g2.setStroke(new BasicStroke(model.shapes.get(i).thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		    	} else {
		    		g2.setStroke(new BasicStroke(model.shapes.get(i).thickness));
		   	 	}
		        g2.drawRect(x1, y1, x2-x1, y2-y1);

			} else if (model.shapes.get(i).shape instanceof Ellipse2D.Double) {
				Shape shape = model.shapes.get(i).shape; 
				int sx = (int)shape.getBounds().getX();
				int ex = (int)shape.getBounds().getWidth() + (int)shape.getBounds().getX();
				int sy = (int)shape.getBounds().getY();
				int ey = (int)shape.getBounds().getY() + (int)shape.getBounds().getHeight();
				int x1 = sx<=ex ? sx : ex;
				int x2 = sx<=ex ? ex : sx;
				int y1 = sy<=ey ? sy : ey;
				int y2 = sy<=ey ? ey : sy;
		        System.out.println(model.shapes.get(i).color);
		        if (model.shapes.get(i).fill != null) {
		        	g2.setColor(model.shapes.get(i).fill);
		        	g2.fillOval((int)shape.getBounds().getX(), (int)shape.getBounds().getY(), (int)shape.getBounds().getWidth(), (int)shape.getBounds().getHeight());
		        }
		        g2.setColor(model.shapes.get(i).color);
		        if (model.selected == model.shapes.get(i)) {
		        	g2.setStroke(new BasicStroke(model.shapes.get(i).thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		    	} else {
		    		g2.setStroke(new BasicStroke(model.shapes.get(i).thickness));
		   	 	}
		        g2.drawOval((int)shape.getBounds().getX(), (int)shape.getBounds().getY(), (int)shape.getBounds().getWidth(), (int)shape.getBounds().getHeight());
		    } else if (model.shapes.get(i).shape instanceof Line2D.Double) {
		    	Line2D.Double shape = (Line2D.Double) model.shapes.get(i).shape; 
		    	g2.setColor(model.shapes.get(i).color);
		    	if (model.selected == model.shapes.get(i)) {
		        	g2.setStroke(new BasicStroke(model.shapes.get(i).thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		    	} else {
		    		g2.setStroke(new BasicStroke(model.shapes.get(i).thickness));
		   	 	}
		        g2.drawLine((int)shape.getX1(), (int)shape.getY1(), (int)shape.getX2(), (int)shape.getY2());
		    }
		} 
		if (!released) {
			if (model.getTool()=="rectangle") {
				int x1 = startx<=endx ? startx : endx;
				int x2 = startx<=endx ? endx : startx;
				int y1 = starty<=endy ? starty : endy;
				int y2 = starty<=endy ? endy : starty;
				g2.setColor(model.getColor());
				g2.setStroke(new BasicStroke(model.getThickness()));
				g2.drawRect(x1, y1, x2-x1, y2-y1);
			} else if (model.getTool()=="circle") {
				int x1 = startx<=endx ? startx : endx;
				int x2 = startx<=endx ? endx : startx;
				int y1 = starty<=endy ? starty : endy;
				int y2 = starty<=endy ? endy : starty;
				int width = Math.min(x2-x1,y2-y1);
				int height = Math.min(x2-x1,y2-y1);
				int sx = (endx>startx) ? startx : startx-width;
				int sy = (endy>starty) ? starty : starty-height;
				g2.setColor(model.getColor());
				g2.setStroke(new BasicStroke(model.getThickness()));
				g2.drawOval(sx, sy, width, height);
			} else if (model.getTool()=="line") {
				g2.setColor(model.getColor());
				g2.setStroke(new BasicStroke(model.getThickness()));
				g2.drawLine(startx, starty, endx, endy);
			}
		}
		g2.setStroke(new BasicStroke(5));
	}


	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		if (model.getTool()!="select") {
			model.selected = null;
		}
		model.updateScrollPane(this);
		repaint();
		System.out.println("CanvasView: update");	
	}
}

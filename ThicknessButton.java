import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ThicknessButton extends JComponent {

	ActionEvent ae = null;
	int thickness;
	Boolean dash;

	public ThicknessButton(int t) {
		thickness = t;
		this.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					fireActionPerformed(new ActionEvent(this, 0, "ON PRESS FIRE"));
					repaint();
				}
		});
	}

	 public void addActionListener(ActionListener l) {
	     listenerList.add(ActionListener.class, l);
	 }

	 public void removeActionListener(ActionListener l) {
	     listenerList.remove(ActionListener.class, l);
	 }


	 protected void fireActionPerformed(ActionEvent e) {
	     Object[] listeners = listenerList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i]==ActionListener.class) {
	             ((ActionListener)listeners[i+1]).actionPerformed(e);
	         }
	     }
	 }

	 public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillRect(10, (getHeight()-thickness)/2, getWidth()-20, thickness);
	 }

}

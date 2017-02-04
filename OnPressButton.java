import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class OnPressButton extends JComponent {

	ActionEvent ae = null;
	Color color;
	Boolean dash;

	public OnPressButton(Color c) {
		color = c;
		// setBorder(BorderFactory.createRaisedBevelBorder());
		this.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					fireActionPerformed(new ActionEvent(this, 0, "ON PRESS FIRE"));
					// setBorder(BorderFactory.createLoweredBevelBorder());
					repaint();
				}

				// public void mouseReleased(MouseEvent e){
				// 	setBorder(BorderFactory.createRaisedBevelBorder());
				// 	repaint();
				// }

				// public void mouseEntered(MouseEvent e){
				// 	setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				// 					Color.WHITE, Color.BLACK));
				// 	repaint();
				// }
				// public void mouseExited(MouseEvent e){
				// 	setBorder(BorderFactory.createRaisedBevelBorder());
				// 	repaint();
				// }

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
        g2.setColor(color);
        g2.fillRect(2, 2, getWidth()-4, getHeight()-4);

	 }

}

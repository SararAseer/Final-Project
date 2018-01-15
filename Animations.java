import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.TimeUnit;


public class Animations extends JFrame implements ActionListener{
    private double[] circlex={.0,.1,.2,.3,.4,.5,.6,.7,.8,.9,1};
    private double initial;
    private Container pane;
    private Color Background1 = new Color(242, 229, 255);
    private Color framec = new Color(22, 29, 255);
    private double xinc;
    private JButton Start;
    private boolean yes;
    private Image db;
    private Graphics dbg;
    
    public static void main(String[] args) {
	Animations Test = new Animations();
	Test.setVisible(true);
	
	
    }
    
    public Animations(){
	pane = this.getContentPane();
	Toolkit tk = Toolkit.getDefaultToolkit();
	this.setSize(sizex(.99),sizey(.99));
	xinc=.005;
	this.setLayout(null);
	this.setTitle("Byte Sized");
	this.setLocationRelativeTo(null);
	this.setBackground(Background1);
	pane.setBackground(Background1);
	Start = new JButton("Start");
	Start.setBounds(sizex(.4),sizey(.4),sizex(.1),sizey(.1));
	Start.setBorderPainted(false);
	Start.setOpaque(true);
	Start.setContentAreaFilled(true);
	Start.setForeground(Color.BLACK);
	Start.setBackground(framec);
	Start.setEnabled(true);
	Start.addActionListener(this);
	pane.add(Start);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
    public int sizex(double newx){
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int x = ((int) tk.getScreenSize().getWidth());  
	return (int)(newx*x);

    }

    public int sizey(double newy){
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int y = ((int) tk.getScreenSize().getHeight());
	return (int)(y*newy);

    }
    
    public void paint(Graphics g){
	db=createImage(getWidth(),getHeight());
	dbg=db.getGraphics();
	paintComponent(dbg);
	g.drawImage(db,0,0,this);

    }
    
    public void paintComponent(Graphics g){
	g.setColor(framec);
	g.fillRect(sizex(0),sizey(.05),sizex(1),sizey(.2));
        g.setColor(Color.BLACK);
        for (int i = 0; i < circlex.length; i++) {
	    g.fillOval(sizex(circlex[i]), sizey(.1), sizex(.05), sizex(.05) );
        }

    }

    public void move(){
	yes=true;
	Timer timer = new Timer(1, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		    for (int i = 0; i < circlex.length; i++) {
			if (circlex[i] + xinc < .99) {
			    circlex[i] += xinc;
			} else {
			    circlex[i] = 0;
			}
		    }

		    repaint();
		}
	    });
        timer.start();
    }

        public void actionPerformed(ActionEvent e){
	    if(e.getSource()==Start){
		Start.setVisible(false);
		Start.repaint();
		move();
		repaint();
		System.out.println("Start");

	    }
	}

}

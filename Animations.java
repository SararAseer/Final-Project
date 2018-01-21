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
    private double[] circlex={.0,.2,.4,.6,.8,1};
    private double[] circlex2={.1,.3,.5,.7,.9};
    private double[] circlex3={.39,.42,.45,.48,.51,.54,.57};
    private double[] circley={.55,.6,.65,.7,.75,.8};
    private double initial;
    private Container pane;
    private Color Background1 = new Color(242, 229, 255);
    private Color framec = new Color(42, 44, 111);
    private Color framec2 = new Color(107, 0, 254);
    private double xinc;
    private JButton Start;
    private String[][] Connected;
    private String Player;
    private boolean yes;
    private boolean color;
    private Image db;
    private Graphics dbg;
    private JTextField name;

    
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
	/*/
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
	/*/
	Connected=new String[6][7];
	for (int x = 0 ; x < Connected.length ; x++){
	    for (int q = 0 ; q < Connected[x].length ; q++){
		Connected[x][q]=("_");
	    }
	    }
	    
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    public int sizex(double newx){
	Toolkit tk = Toolkit.getDefaultToolkit();  
	int x = ((int) tk.getScreenSize().getWidth());  
	return (int)(newx*x);

    }

    public void setxy(double[] inputx, double []inputy){
	circlex3=inputx;
	circley=inputy;


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

    public void setText(String  g){
	Player=g;
	
    }

     public void Board(String [][]  x){
	Connected=x;
	
    }
    
    public void paintComponent(Graphics g){
	g.setColor(framec);
	g.fillRect(sizex(0),sizey(.08),sizex(1),sizey(.12));
	g.fillRect(sizex(0),sizey(.78),sizex(1),sizey(.12));
	String m=Player+" HAS WON!";
	Font font= new Font("TimesRoman", Font.PLAIN, 25);
	FontMetrics fm = g.getFontMetrics ( font );
	int sw = fm.stringWidth ( m );
	g.setFont ( font );
	g.setColor ( Color.BLACK );
	g.drawString ( m , ( this.getWidth() + sw ) / 2 - sw , sizey(.06));
	String a= "Byte Sized";
	sw = fm.stringWidth ( a);
	g.drawString ( a , ( this.getWidth() + sw ) / 2 - sw , sizey(.96));		
        for (int i = 0; i < circlex.length; i++) {
	    g.setColor(Color.YELLOW);
	    g.fillOval(sizex(circlex[i]), sizey(.1), sizex(.04), sizex(.04) );
	    g.fillOval(sizex(circlex[i]), sizey(.8), sizex(.04), sizex(.04) );
        }
	 for (int i = 0; i < circlex2.length; i++) {
	     g.setColor(Color.RED);
	    g.fillOval(sizex(circlex2[i]), sizey(.1), sizex(.04), sizex(.04) );
	    g.fillOval(sizex(circlex2[i]), sizey(.8), sizex(.04), sizex(.04) );
	 }
	 for (int s =Connected.length-1; s>-1; s--){
	     for (int i =0; i<Connected[s].length; i++){
		 if (Connected[s][i].equals("_")){
		     g.setColor(Color.BLACK);
		     g.fillOval(sizex(circlex3[i]),sizey(circley[s]+.1),sizex(.025),sizey(.03));

		 }
		 if (!(Connected[s][i].equals("_")) && Connected[s][i].equals("Yellow")){
		     g.setColor(Color.YELLOW);
		     g.fillOval(sizex(circlex3[i]),sizey(circley[s]+.1),sizex(.025),sizey(.03));
		 }
		 else if(!(Connected[s][i].equals("_")) && Connected[s][i].equals("Red")){
		     g.setColor(Color.RED);
		     g.fillOval(sizex(circlex3[i]),sizey(circley[s]+.1),sizex(.025),sizey(.03));
		 }				    
	     }

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
		    for (int i = 0; i < circlex2.length; i++) {
			if (circlex2[i] + xinc < .99) {
			    circlex2[i] += xinc;
			} else {
			    circlex2[i] = 0;
			}
		    }
		    repaint();
		}
		
	    });
        timer.start();
    }

        public void actionPerformed(ActionEvent e){
	    if(e.getSource()==Start){
		move();
		System.out.println("Start");

	    }
	}

}

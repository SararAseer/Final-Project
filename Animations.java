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


public class Animations  extends JFrame {
    private Container pane;
    private Color Background1;

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
    
    public Animations(){
	Background1 =new Color(242, 229, 255);
	pane = this.getContentPane();
	Toolkit tk = Toolkit.getDefaultToolkit();
	this.setSize(sizex(.99),sizey(.99));	
	this.setLayout(null);
	this.setTitle("Connect-4");
	this.setLocationRelativeTo(null);
	this.setBackground(Background1);
	pane.setBackground(Background1);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }



}

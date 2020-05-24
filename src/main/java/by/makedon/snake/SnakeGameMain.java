package by.makedon.snake;

import by.makedon.snake.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGameMain extends JPanel implements ActionListener
{
	public static int speed = 12;
	
	snake s = new snake(5,6,5,5);
	apple a = new apple(Math.abs((int) (Math.random()*WIDTH-1)),Math.abs((int) (Math.random()*HEIGTH-1)));

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH*SCALE,HEIGTH*SCALE);
		
		for (int x=0; x<WIDTH*SCALE; x=x+SCALE)
		{
			g.setColor(Color.black);
			g.drawLine(x, 0, x, HEIGTH*SCALE);
		}
		
		for (int y=0; y<HEIGTH*SCALE; y=y+SCALE)
		{
			g.setColor(Color.black);
			g.drawLine(0, y, WIDTH*SCALE, y);
		}
		
		for (int l=1; l<s.length; l++)
		{
			g.setColor(Color.green);
			g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE-6, SCALE-6);
			
			g.setColor(Color.white);
			g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE-6, SCALE-6);
		}
		
		g.setColor(Color.red);
		g.fillOval(a.posX*SCALE+4,a.posY*SCALE+4,SCALE-8,SCALE-8);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		s.move();
		if ((s.sX[0]==a.posX)&&(s.sY[0]==a.posY))
		{
			if (s.length==WIDTH*HEIGTH-1) 
			{
				JOptionPane.showMessageDialog(null,"YOU'RE WIN !!!");
				jFrame.dispose();
				System.exit(0);
			}
			
			a.setRandomPosition();				
			s.length++;
		}
		
		for (int l=1;l<s.length;l++)
		{
			if ((s.sX[l]==a.posX)&&(s.sY[l]==a.posY)) a.setRandomPosition();
			if ((s.sX[0]==s.sX[l])&&(s.sY[0]==s.sY[l])) 
			{
				timer.stop();
				int result = JOptionPane.showConfirmDialog((Component) null,"GAME OVER! BEGIN AGAIN?","Game Over",JOptionPane.OK_CANCEL_OPTION);
				
				if (result == 2) 
				{
					jFrame.dispose();
					return;
				}
				
				jFrame.setVisible(false);
				s.length=2;
				s.direction=0;
				a.setRandomPosition();
				s.sX[0]=5;
				s.sX[1]=6;
				s.sY[0]=5;
				s.sY[1]=5;				
				jFrame.setVisible(true);
				timer.start();				
			}
		}
		repaint();
	}
}

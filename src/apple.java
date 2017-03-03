public class apple 
{
	public int posY;
	public int posX;
	
	public apple(int x, int y)
	{
		posX=x;
		posY=y;
	}
	
	public void setRandomPosition()
	{
		posX = Math.abs((int) (Math.random()*SnakeGameMain.WIDTH-1));
		posY = Math.abs((int) (Math.random()*SnakeGameMain.HEIGTH-1));
	}
}

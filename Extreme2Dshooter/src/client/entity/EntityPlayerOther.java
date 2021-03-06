package client.entity;

public class EntityPlayerOther extends EntityLiving
{
	public int id = -1;
	
	public EntityPlayerOther(int i, double x, double y, double vx, double vy)
	{
		name = "PlayerOther";
		id = i;
		onfeet = false;
		
		health = 20F;
		
		posX = x;
		posY = y;
		velX = vx;
		velY = vy;
		
		hitbox[0][0] = -12;
		hitbox[0][1] = -12;
		hitbox[1][0] = -12;
		hitbox[1][1] = 12;
		hitbox[2][0] = 12;
		hitbox[2][1] = -12;
		hitbox[3][0] = 12;
		hitbox[3][1] = 12;
	}
	
	public void tick()
	{	
		if(groundCollision())
		{
			velY = 0D;
		}
		else
		{
			if(velY < 5)
			{
				velY += 0.2D;
			}
		}
		
		posX += velX;
		posY += velY;
	}
}

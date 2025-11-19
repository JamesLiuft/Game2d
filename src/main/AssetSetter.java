package main;

import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 */
public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gamePanel) {
		this.gp = gamePanel;
	}
	
	public void setObject() {
		gp.objs[0] = new OBJ_Key();
		gp.objs[0].worldX  = 23 * gp.tileSize;
		gp.objs[0].worldY = 7 * gp.tileSize;
		gp.objs[1] = new OBJ_Key();
		gp.objs[1].worldX  = 23 * gp.tileSize;
		gp.objs[1].worldY = 40 * gp.tileSize;
		gp.objs[2] = new OBJ_Key();
		gp.objs[2].worldX  = 38 * gp.tileSize;
		gp.objs[2].worldY = 8 * gp.tileSize;
		
		gp.objs[3] = new OBJ_Door();
		gp.objs[3].worldX  = 10 * gp.tileSize;
		gp.objs[3].worldY = 11 * gp.tileSize;
		gp.objs[4] = new OBJ_Door();
		gp.objs[4].worldX  = 8 * gp.tileSize;
		gp.objs[4].worldY = 28 * gp.tileSize;
		gp.objs[5] = new OBJ_Door();
		gp.objs[5].worldX  = 12 * gp.tileSize;
		gp.objs[5].worldY = 22 * gp.tileSize;
		
		gp.objs[6] = new OBJ_Chest();
		gp.objs[6].worldX  = 10 * gp.tileSize;
		gp.objs[6].worldY = 7 * gp.tileSize;
		
	}


}

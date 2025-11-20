package main;

import entity.Entity;
import objects.SuperObject;

/**
 * <p>
 * CollisionChecker
 * </p>
 * 
 * @author James
 * @version 1.0
 * @date 2025Äê11ÔÂ19ÈÕ
 */
public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		super();
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

//		the left x position of solidArea in the world
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
//		the right x position of solidArea in the world
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
//		the top y position of solidArea in the world
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
//		the bottom y position of solidArea in the world
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
//		get collision point position
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;

		default:
			break;
		}
	}

	/**
	 * check collision for object and player
	 * 
	 * @param entity
	 * @param player
	 * @return
	 */
	public int checkObject(Entity entity,boolean player) {
        int index = 999;

        for(int i = 0; i < gp.objs.length; i++){
            if (gp.objs[i] != null){
                // get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object solid area position
                gp.objs[i].solidArea.x = gp.objs[i].worldX + gp.objs[i].solidArea.x;
                gp.objs[i].solidArea.y = gp.objs[i].worldY + gp.objs[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.objs[i].solidArea)){
                            if(gp.objs[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objs[i].solidArea.x = gp.objs[i].solidAreaDefaultX;
                gp.objs[i].solidArea.y = gp.objs[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}

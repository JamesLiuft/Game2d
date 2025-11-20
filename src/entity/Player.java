package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import objects.SuperObject;

/**
 * @author James
 * @date 2025年10月11日 22点11分
 * @version 1.0
 */
public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
    public int hasKey = 0;

	public final int screenX, screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		super();
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;

		solidArea = new Rectangle(8, 16, 32, 32);

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();

		getPlayerImage();

	}

	/**
	 * Set player default values
	 */
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * update player status
	 */
	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			moving();
		} else {
			spriteRunningShow();
		}

	}

	/**
	 * show sprite running by changing {@code spriteNum} flag
	 */
	private void spriteRunningShow() {
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}

	}

	/**
	 * 
	 */
	private void moving() {

		if (keyH.upPressed) {
			direction = "up";
		} else if (keyH.downPressed) {
			direction = "down";
		} else if (keyH.leftPressed) {
			direction = "left";
		} else if (keyH.rightPressed) {
			direction = "right";

		}
//		check tile collision
		collisionOn = false;
//		check player's collision with tiles
		gp.cChecker.checkTile(this);

//		check object's collision
		int index = gp.cChecker.checkObject(this, true);
		pickUpObject(index);

//		if collision is false player can move,otherwise it can not move
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;

			default:
				break;
			}
		}
		spriteRunningShow();

	}

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.objs[i].name;
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.objs[i] = null;
                    System.out.println("keys num:" + hasKey);
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.objs[i] = null;
                        hasKey--;
                    }
                    System.out.println("keys remain:" + hasKey);
                    break;
                case "Boots":
                    speed += 2;
                    gp.objs[i] = null;
                    break;
                case "Chest":
                    break;
            }
        }

    }


	/**
	 * draw player's image
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}

			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}

			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}

			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}

			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}

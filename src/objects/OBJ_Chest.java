package objects;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Chest extends SuperObject {

	public OBJ_Chest(GamePanel gp) {
		super();
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			image = ut.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

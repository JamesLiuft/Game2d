package objects;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Door extends SuperObject {

	public OBJ_Door(GamePanel gp) {
		super();
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			image = ut.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}

	@Override
	public void doAction() {
		super.doAction();
		this.image = null;
	}
	
	
}

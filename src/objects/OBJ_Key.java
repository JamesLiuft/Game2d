package objects;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Key extends SuperObject {

	public OBJ_Key(GamePanel gp) {
		super();
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = ut.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doAction() {
		super.doAction();
		this.disappear();
	}

	private void disappear() {
		this.image =null;
	}
	
}

package objects;

import javax.imageio.ImageIO;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Door extends SuperObject {

	public OBJ_Door() {
		super();
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
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

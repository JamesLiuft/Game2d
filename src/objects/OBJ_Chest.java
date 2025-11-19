package objects;

import javax.imageio.ImageIO;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Chest extends SuperObject {

	public OBJ_Chest() {
		super();
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

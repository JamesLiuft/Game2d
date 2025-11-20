package objects;

import javax.imageio.ImageIO;

/**
 * @author James
 * @date 2025Äê11ÔÂ19ÈÕ
 * @version 1.0
 * 
 */
public class OBJ_Key extends SuperObject {

	public OBJ_Key() {
		super();
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
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

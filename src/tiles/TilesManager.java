package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TilesManager {
	GamePanel gp;
	UtilityTool uTool = new UtilityTool();
	public Tile[] tiles;
//	tile flags in txt file
	public int mapTileNum[][];
	String mapFilePath = "/maps/worldV2.txt";

	public TilesManager(GamePanel gp) {
		super();
		this.gp = gp;
		tiles = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap(mapFilePath);
	}

	private void getTileImage() {
		try {
//			PLACEHOLDER
			setUpTiles(0,"grass00",false);
			setUpTiles(1,"grass00",false);
			setUpTiles(2,"grass00",false);
			setUpTiles(3,"grass00",false);
			setUpTiles(4,"grass00",false);
			setUpTiles(5,"grass00",false);
			setUpTiles(6,"grass00",false);
			setUpTiles(7,"grass00",false);
			setUpTiles(8,"grass00",false);
			setUpTiles(9,"grass00",false);
//			PLACEHOLDER
			
			setUpTiles(10,"grass00",false);
			setUpTiles(11,"grass01",false);
			
			setUpTiles(12,"water00",true);
			setUpTiles(13,"water01",true);
			setUpTiles(14,"water02",true);
			setUpTiles(15,"water03",true);
			setUpTiles(16,"water04",true);
			setUpTiles(17,"water05",true);
			setUpTiles(18,"water06",true);
			setUpTiles(19,"water07",true);
			setUpTiles(20,"water08",true);
			setUpTiles(21,"water09",true);
			setUpTiles(22,"water10",true);
			setUpTiles(23,"water11",true);
			setUpTiles(24,"water12",true);
			setUpTiles(25,"water13",true);
			
			setUpTiles(26,"road00",false);
			setUpTiles(27,"road01",false);
			setUpTiles(28,"road02",false);
			setUpTiles(29,"road03",false);
			setUpTiles(30,"road04",false);
			setUpTiles(31,"road05",false);
			setUpTiles(32,"road06",false);
			setUpTiles(33,"road07",false);
			setUpTiles(34,"road08",false);
			setUpTiles(35,"road09",false);
			setUpTiles(36,"road10",false);
			setUpTiles(37,"road11",false);
			setUpTiles(38,"road12",false);

			setUpTiles(39,"earth",false);
			setUpTiles(40,"wall",true);
			setUpTiles(41,"tree",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpTiles(int index,String imageName,boolean collision) {
		try {
			tiles[index] = new Tile();
			tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tiles[index].image = uTool.scaleImage(tiles[index].image, gp.tileSize, gp.tileSize);
			tiles[index].collision = collision;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * load map
	 */
	private void loadMap(String mapFilePath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapFilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
//					a line txt array
					String numbers[] = line.split(" ");
//					get tile flag number one by one
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * draw the map to screen
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int worldCol = 0, worldRow = 0;
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
//			check the border while drawing map
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}

}

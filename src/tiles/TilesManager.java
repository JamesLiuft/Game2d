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
	String mapFilePath = "/maps/world01.txt";

	public TilesManager(GamePanel gp) {
		super();
		this.gp = gp;
		tiles = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap(mapFilePath);
	}

	private void getTileImage() {
		try {
			
			setUpTiles(0,"grass",false);
			setUpTiles(1,"wall",true);
			setUpTiles(2,"water",true);
			setUpTiles(3,"earth",false);
			setUpTiles(4,"tree",true);
			setUpTiles(5,"sand",false);
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

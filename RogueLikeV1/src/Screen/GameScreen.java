package Screen;

import java.awt.event.KeyEvent;

import Creatures.Creature;
import Resources.CreatureFactory;
import Resources.World;
import Resources.WorldBuilder;
import asciiPanel.AsciiPanel;

public class GameScreen implements Screen {
	private World world;
	private Creature player;
	private int screenWidth;
	private int screenHeight;
	
	public GameScreen(){
		screenWidth = 100;
		screenHeight = 48;
		createWorld();
		
		CreatureFactory cF = new CreatureFactory(world);
		createCreatures(cF);
		
	}
	
	private void createCreatures(CreatureFactory cF) {
		player = cF.newPlayer();
		  
	    for (int i = 0; i < 8; i++){
	        cF.newGhoul();
	    }
	    
	    for (int i = 0; i < 2; i++){
	        cF.newCopyCat();
	    }
		
	}

	private void createWorld(){
		world = new WorldBuilder(120, 68)
					.makeCaves()
					.buildWorld();
	}
	
	public int getScrollX() { return Math.max(0, Math.min(player.x - screenWidth / 2, world.getWidth() - screenWidth)); }
	
	public int getScrollY() { return Math.max(0, Math.min(player.y - screenHeight / 2, world.getHeight() - screenHeight)); }
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		
		int left = getScrollX();
		int top = getScrollY(); 
		
		displayTiles(terminal, left, top);
		
		terminal.write(player.getGlyph(), player.x - left, player.y - top, player.getColor());
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {

		for(int x = 0; x < screenWidth ; x++) 
		{
			for(int y = 0; y < screenHeight; y++) 
			{
				int wx = x + left;
				int wy = y + top;
				terminal.write(world.returnGlyph(wx, wy), x, y, world.returnColor(wx, wy));
			}
		}

		for(Creature c : world.creatures) 
		{
			if((c.x >= left && c.x < left + screenWidth) && (c.y >= top && c.y < top + screenHeight)) 
			{
				terminal.write(c.getGlyph(), c.x - left, c.y - top, c.getColor());
			}
		}
	}
	
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
		case KeyEvent.VK_ESCAPE: return new MenuScreen();
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_H: player.moveBy(-1, 0); break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L: player.moveBy( 1, 0); break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_K: player.moveBy( 0,-1); break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_J: player.moveBy( 0, 1); break;
		case KeyEvent.VK_Y: player.moveBy(-1,-1); break;
		case KeyEvent.VK_U: player.moveBy( 1,-1); break;
		case KeyEvent.VK_B: player.moveBy(-1, 1); break;
		case KeyEvent.VK_N: player.moveBy( 1, 1); break;
		}
		world.update();
		return this;
	}
}


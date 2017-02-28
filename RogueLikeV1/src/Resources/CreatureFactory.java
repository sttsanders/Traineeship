package Resources;

import Ai.CopyCatAi;
import Ai.GhoulAi;
import Ai.PlayerAi;
import Creatures.Creature;
import asciiPanel.AsciiPanel;

public class CreatureFactory {
	private World world;
	
	public CreatureFactory(World world){
		this.world = world;
	}
	
	public Creature newPlayer(){
		Creature player = new Creature(world, '@', AsciiPanel.brightWhite, 150, 10, 10);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}
	
	public Creature newGhoul()
	{
		Creature ghoul = new Creature(world, 'G', AsciiPanel.brightGreen, 70, 15, 5);
		world.addAtEmptyLocation(ghoul);
		new GhoulAi(ghoul);
		return ghoul;
	}
	
	public Creature newCopyCat()
	{
		Creature copyCat = new Creature(world, 'C', AsciiPanel.brightRed, 20, 5, 1);
		System.out.println("adding copyCat");
		world.addAtEmptyLocation(copyCat);
		new CopyCatAi(copyCat, this);
		return copyCat;
	}
	
	
}


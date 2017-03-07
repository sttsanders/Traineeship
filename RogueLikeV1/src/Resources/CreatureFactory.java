package Resources;

import java.util.List;

import Ai.BeeAi;
import Ai.BeeNestAi;
import Ai.CopyCatAi;
import Ai.CopyCatCloneAi;
import Ai.GhoulAi;
import Ai.KoboldAi;
import Ai.PlayerAi;
import Ai.QuillBoarAi;
import Creatures.Creature;
import asciiPanel.AsciiPanel;

public class CreatureFactory {
	private World world;
	
	public CreatureFactory(World world){
		this.world = world;
	}
	
	/**
	 * 
	 * @param messages
	 * @return
	 */
	public Creature newPlayer(List<String> messages){
		Creature player = new Creature(world, '@',"You", AsciiPanel.brightWhite, 150, 10, 10);
		
		Item ironArmor = new Item('i', AsciiPanel.green, "iron armor", ItemType.ARMOR, 0, 4);
		Item ironHelmet = new Item('i', AsciiPanel.green, "iron helmet", ItemType.HELMET, 0, 3);
		Item sword = new Item('i', AsciiPanel.green, "sword",ItemType.WEAPON, 4, 0);
		
		player.equip(ironArmor);
		player.equip(ironHelmet);
		player.equip(sword);
		
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages);
		System.out.println("--------player--------");
		System.out.println("Weapon: " + player.getEquippedWeapon().getName());
		System.out.println("helmet: " + player.getEquippedHelmet().getName());
		System.out.println("Armor: " + player.getEquippedArmor().getName());
		System.out.println("----------------------");
		return player;
	}
	
	/**
	 * Creates new Ghoul object and inserts it into the world
	 * @return
	 */
	public Creature newGhoul(int depth)
	{
		Creature ghoul = new Creature(world, 'G',"Ghoul", AsciiPanel.brightGreen, 70, 15, 5);
		world.addAtEmptyLocation(ghoul, depth);
		new GhoulAi(ghoul);
		return ghoul;
	}
	
	/**
	 * Creates new CopyCat object and inserts it into the world
	 * @return
	 */
	public Creature newCopyCat(int depth)
	{
		Creature copyCat = new Creature(world, 'C',"Copy Cat", AsciiPanel.brightRed, 20, 5, 1);
		world.addAtEmptyLocation(copyCat, depth);
		new CopyCatAi(copyCat, this);
		return copyCat;
	}
	
	/**
	 * Creates new CopyCatClone object and inserts it into the world
	 * @return
	 */
	public Creature newCopyCatCopy(int depth)
	{
		Creature copyCatClone = new Creature(world, 'c',"Copy Cat Clone", AsciiPanel.brightRed, 20, 5, 1);
		world.addAtEmptyLocation(copyCatClone, depth);
		new CopyCatCloneAi(copyCatClone);
		return copyCatClone;
	}
	
	/**
	 * Creates new Kobold object and inserts it into the world
	 * @return
	 */
	public Creature newKobold(int depth)
	{
		Creature kobold = new Creature(world, 'K',"Kobold", AsciiPanel.brightYellow, 15, 6, 0);
		world.addAtEmptyLocation(kobold, depth);
		new KoboldAi(kobold);
		return kobold;
	}
	
	/**
	 * Creates new Quillboar object and inserts it into the world
	 * @return
	 */
	public Creature newQuillBoar(int depth)
	{
		Creature quillBoar = new Creature(world, 'Q',"Quillboar", AsciiPanel.brightMagenta, 80, 12, 4);
		world.addAtEmptyLocation(quillBoar, depth);
		new QuillBoarAi(quillBoar);
		return quillBoar;
	}
	
	/**
	 * Creates new Beenest object and inserts it into the world
	 * @return
	 */
	public Creature newBeeNest(int depth)
	{
		Creature beeNest = new Creature(world, 'B',"Bee Nest", AsciiPanel.brightCyan, 120, 0, 8);
		world.addAtEmptyLocation(beeNest, depth);
		new BeeNestAi(beeNest,this);
		return beeNest;
	}

	/**
	 * Creates creatures for world seeding
	 * @param cF
	 */
	public void createCreatures() {
		// TODO Auto-generated method stub
		for (int z = 0; z < world.getDepth(); z++)
		{
//			for (int i = 0; i < 1; i++){
//		        newGhoul(z);
//		    }
//		
		    for (int i = 0; i < 15; i++){
		        newQuillBoar(z);
		    }
		    
//		    for (int i = 0; i < 0; i++){
//		        newCopyCat(z);
//		    }
		    
		    for (int i = 0; i < 15; i++){
		        newKobold(z);
		    }
//		    
		    for (int i = 0; i < 4; i++){
		        newBeeNest(z);
		    }
		}
	}

	/**
	 * Creates new Bee object and inserts it into the world
	 * @return
	 */
	public Creature newBee(int depth) {
		// TODO Auto-generated method stub
		Creature bee = new Creature(world, 'b',"Bee", AsciiPanel.cyan, 5, 20, 0);
		world.addAtEmptyLocation(bee, depth);
		new BeeAi(bee);
		return bee;
	}
	
	
	
}


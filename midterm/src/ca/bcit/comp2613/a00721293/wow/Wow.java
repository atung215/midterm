package ca.bcit.comp2613.a00721293.wow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * 
 * @author Henry
 * 
 *         Copy this package to your repo (a package can copied via 1) highlighting the package
 *         folder, 2) right click and then 3) copy).  If that fails, copy the files one by one ;)
 *         
 *         
 *         WoW - World Of Warcraft questions
 *         
 * 
 *         1) Modify the Race enum to include HUMAN 
 *         
 *         2) Implement rollCharacters (generates 100 Random Characters) PROPERLY
 *         
 *         3) Sort the characters by level, strength, armour 
 *         
 *         4) Print out the characters in a
 *         readable format (modify printCharacters)
 *         
 *         5) Implement checkAndRemoveAnyHordeSpies (hint: ORCs are Horde)
 *         
 *         6) Print out the
 *         characters again after we remove those pesky Horde spies
 *         
 *         7) Modify checkForPandaren : If we have any Pandaren races in our characters list, throw a DontHaveTheExpansionException
 *         
 *         8) Bonus: read the comments of the method: myCharactersAfterMyArchNemesisHacksIntoMyAccount and implement getLevelOfMissingCharacter
 *         
 *         When you are finished, please commit this package as
 *         ca.bcit.comp2613.<your student id>.wow in your GitHub project folder 
 *         note that package name should *always* be lowercase 
 *         
 *        
 */
public class Wow 
{
	private static Random rand = new Random();
	public static void main(String[] args) throws Exception 
	{
		System.out.println("---------------ROLL CHARACTERS---------------");;
		
		ArrayList<Character> characters = rollCharacters();
		printCharacters(characters);
		
		System.out.println("---------------REMOVE ORCS---------------");;
		
		checkAndRemoveAnyHordeSpies(characters);
		printCharacters(characters);
		checkForPandaren(characters);		
	}
	
	private static Race getRandomRace() 
	{
		Race retval = null;
		Race[] races = Race.values();		
		retval = races[rand.nextInt(races.length)];
		return retval;
	}
	
	private static int getRandomLevel()
	{
		return 1 + rand.nextInt(86); // TODO is this right?
	}

	private static int getRandomStrength() 
	{
		return rand.nextInt(101); // returns a number between 0 and 100
	}
	
	private static int getRandomArmour()
	{
		return rand.nextInt(101); // returns a number between 0 and 100
	}
	
	private static ArrayList<Character> rollCharacters()
	{
		// TODO create 100 Random Characters 
				// The Race's assigned should be pretty random
				// Each Character's  
				// level must be 1-85
				// strength, 0-100
				// armour, 0-100
				// find and fix the bug
		ArrayList<Character> retval = new ArrayList<Character>();
		for (int i = 0; i < 100; i++) 
		{
			Race race = getRandomRace();
			int level = getRandomLevel();
			int strength = getRandomStrength();
			int armour = getRandomArmour();
			Character character = new Character(race, level, strength, armour);
			retval.add(character);
		}
		return retval;
	}

	private static void checkAndRemoveAnyHordeSpies(ArrayList<Character> characters)
	{
		// TODO loop through each character in the characters list
		// if the character is of Race: ORC, remove that character from the
		// list
		Iterator<Character> iterator = characters.iterator();
		while (iterator.hasNext()) 
		{
			Character character = iterator.next();			
			if (character.getRace().equals(Race.ORC))
			{ //TODO not quite right is it?
				iterator.remove();
			}
		}
	}

	private static void printCharacters(ArrayList<Character> characters)
	{
		Comparator<Character> chars = new Comparator<Character>()
		{
			// TODO compares Characters by level, strength and armour
			// note that this soln isn't quite correct,
			// find the bug and fix it
			@Override
			public int compare(Character char1, Character char2)
			{
				int retval = Integer.valueOf(char1.getLevel()).compareTo(char2.getLevel());
				if (retval == 0)
				{
					retval = Integer.valueOf(char1.getStrength()).compareTo(char2.getStrength());
					if (retval == 0) 
					{
						retval = Integer.valueOf(char1.getArmour()).compareTo(char2.getArmour());
					}
				}
				return retval;
			}
		};
		Collections.sort(characters, chars);
		for (Character character : characters)
		{
			//TODO how do I print out the character's armour?
			System.out.println("Level: " + character.getLevel() + ", Strength " + character.getStrength() + ", Armour: " + character.getArmour() + ", Race: " + character.getRace());
		}
	}
	
	private static void checkForPandaren(ArrayList<Character> characters) throws DontHaveTheExpansionException
	{
		// TODO if there are any PANDAREN races in our List, throw a DontHaveTheExpansionException
		// hint, you should change this method signature
		Iterator<Character> iterator = characters.iterator();
		while (iterator.hasNext()) 
		{
			Character character = iterator.next();			
			if (character.getRace().equals(Race.PANDAREN))
			{ //TODO not quite right is it?
				throw new DontHaveTheExpansionException("No Expansion Yet");
			}
		}
	}
	
	
	/**
	 * Bonus Question (2% of overall grade)
	 * So here's the scenario:
	 * I have 85 characters in my WoW account.  Ironically enough, each character's level is unique
	 * i.e. I have a Level1, Level2, Level3 ... Level85 characters (although in a shuffled order)
	 * Then one day, my arch-nemesis hacks into my account and DELETEs only one of my characters.
	 * Write a program that finds out which character level he DELETEd.
	 * Maximum marks will be given for efficiency.  It is possible to solve this problem by looping through
	 * the arraylist just **ONCE**
	 * Also note, that although this isn't necessarily a Java question, this is a permutation of an interview question
	 * used by a few Fortune 100 companies.  
	 * 
	 * Its a true *computer science* question
	 * Also note that if you can solve this question, most likely you have a greater computer science
	 * mind than 90% of the instructors at BCIT and you're bored silly of this course because its too easy for you ;)
	 * 
	 * And just for the record, it took me *OVER* half an hour to solve this.  Its tricky ;)
	 */
	public static ArrayList<Character> myCharactersAfterMyArchNemesisHacksIntoMyAccount() 
	{
		ArrayList<Character> retval = new ArrayList<Character>();
		for (int i = 1; i <= 85; i++) 
		{
			Character character = new Character(Race.ORC, i, i, i);
			retval.add(character);
		}
		Collections.shuffle(retval);
		// then my archNemesis randomly DELETE's one of my characters
		Random random = new Random();
		int randIndex = random.nextInt(86);
		retval.remove(randIndex);		
		return retval;
	}
	
	// TODO
	// Your turn!
	// implement getLevelOfMissingCharacter
	public static int getLevelOfMissingCharacter()
	{
		ArrayList<Character> characters = myCharactersAfterMyArchNemesisHacksIntoMyAccount();
		// from the characters above, find out which Level character is missing
		return -1;
	}

}

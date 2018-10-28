import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author Brandon Chambers
 *
 *         Main is used to read in the input files and test and run the Burger
 *         class
 */
public class Main
{
	static Burger testBaronBurger;
	static Burger testBurger;
	static Burger burger;
	static Burger baronBurger;

	static final boolean theWorks = true;
	static final boolean simpleBurger = false;

	static File file = new File("customer.txt");

	static int orderNum = 0;

	public static void main(String[] args)
	{
		BufferedReader br;
		ArrayList<String> customerOrder = new ArrayList<String>();
		try
		{
			br = new BufferedReader(new FileReader(file));
			String order = null;

			while ((order = br.readLine()) != null)
			{
				customerOrder.add(order);
				order = null;
			}
			br.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		for (String s : customerOrder)
		{
			parseLine(s);
		}

		System.out.println("\n-----(uncomment in main)--testBurger()-----------------------------");
		// testBurger();
		System.out.println("\n-----(uncomment in main)--testMyStack()-----------------------------");
		// testMyStack();
	}

	// parses a line of input from the file and outputs the burger
	public static void parseLine(String line)
	{
		burger = new Burger(simpleBurger);

		int butIndex = -1;
		int withIndex = -1;

		boolean foundBaron = false;
		ArrayList<String> str = new ArrayList<String>();
		StringTokenizer arr = new StringTokenizer(line);
		while (arr.hasMoreTokens())
		{
			str.add(arr.nextToken());
		}

		if (str.contains("no"))
			str.remove(str.indexOf("no"));
		if (str.contains("and"))
			str.remove(str.indexOf("and"));
		if (str.contains("but"))
			butIndex = str.indexOf("but");
		if (str.contains("with"))
			withIndex = str.indexOf("with");

		if (str.contains("Baron"))
		{
			foundBaron = true;
			burger = new Burger(theWorks);
		}

		if (butIndex > -1)
		{
			for (int i = withIndex + 1; i < butIndex; i++)
			{
				if (foundBaron)
				{
					burger.removeCategory(str.get(i));
					burger.removeIngredient(str.get(i));
				}
				else
				{
					burger.addCategory(str.get(i));
					burger.addIngredient(str.get(i));
				}
			}
			for (int j = butIndex + 1; j <= str.size() - 1; j++)
			{
				if (foundBaron)
				{
					burger.addCategory(str.get(j));
					burger.addIngredient(str.get(j));
				}
				else
				{
					burger.removeCategory(str.get(j));
					burger.removeIngredient(str.get(j));
				}
			}
		}

		if (butIndex == -1)
		{
			for (int i = withIndex + 1; i <= str.size() - 1; i++)
			{
				if (foundBaron)
				{
					burger.removeCategory(str.get(i));
					burger.removeIngredient(str.get(i));
				}
				else if (!foundBaron)
				{
					burger.addCategory(str.get(i));
					burger.addIngredient(str.get(i));
				}
			}

		}

		if (str.contains("Chicken"))
		{
			burger.changePatties("Chicken");
		}
		if (str.contains("Veggie"))
		{
			burger.changePatties("Veggie");
		}
		if (str.contains("Double"))
		{
			burger.addPatty();
		}
		if (str.contains("Triple"))
		{
			burger.addPatty();
			burger.addPatty();
		}

		System.out.println("Processing Order " + orderNum + ": " + line + "\n"
		        + burger + "\n");
		if (orderNum < 100)
			orderNum++;
		if (orderNum == 99)
			orderNum = 0;
		burger.clear();
	}

	// test method for MyStack
	static void testMyStack()
	{
		MyStack<String> stack = new MyStack<String>();
		System.out.println("stack.isEmpty() -- " + stack.isEmpty());
		stack.push("bun");
		stack.push("beef");
		stack.push("cheese");
		stack.push("pickle");
		System.out.println("We have now called stack.push('') with bun, beef, cheese, pickle");
		System.out.println("stack.peek() -- " + stack.peek());
		System.out.println("stack.isEmpty() -- " + stack.isEmpty());
		System.out.println("sys.out.println(stack) -- " + stack.toString());
		System.out.println("stack.size() -- " + stack.size());
		System.out.println("stack.pop() -- " + stack.pop());
		System.out.println("stack.peek() -- " + stack.peek());
		System.out.println("stack.size() -- " + stack.size());
		System.out.println("stack.toString() -- " + stack.toString());
		System.out.println("stack.pop() -- " + stack.pop());
		System.out.println("stack.size() -- " + stack.size());
		System.out.println("stack.toString() -- " + stack.toString());
		System.out.println("stack.pop() -- " + stack.pop());
		System.out.println("stack.size() -- " + stack.size());
		System.out.println("stack.toString() -- " + stack.toString());
		System.out.println("stack.pop() -- " + stack.pop());
		System.out.println("stack.size() -- " + stack.size());
		System.out.println("stack.toString() -- " + stack.toString());
	}

	// test method for Burger
	static void testBurger()
	{

		testBurger = new Burger(simpleBurger);
		System.out.println("\nburger.toString() --\t\t\t"
		        + testBurger.toString() + "\n");

		testBurger.addCategory("Cheese");
		System.out.println("burger.addCategory('Cheese')-- \t\t"
		        + testBurger.toString() + "\n");

		testBurger.removeIngredient("Mozzarella");
		System.out.println("burger.removeIngredient('Mozzarella')-- "
		        + testBurger.toString() + "\n");

		testBurger.addIngredient("Mozzarella");
		System.out.println("burger.addIngredient('Mozzarella')--\t"
		        + testBurger.toString() + "\n");

		testBurger.addPatty();
		System.out.println("burger.addPatty()  --  \t\t"
		        + testBurger.toString() + "\n");

		testBurger.addCategory("Sauces");
		System.out.println("burger.addCategory('Sauces')-- \t\t"
		        + testBurger.toString() + "\n");

		testBurger.removeCategory("Cheese");
		System.out.println("burger.removeCategory('Cheese')-- \t"
		        + testBurger.toString() + "\n");

		testBaronBurger = new Burger(theWorks);
		System.out.println("baronBurger.toString() --\t\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.addPatty();
		System.out.println("baronBurger.addPatty() -- \t\t" + testBaronBurger
		        + "\n");

		testBaronBurger.changePatties("Chicken");
		System.out.println("baronBurger.changePatties('Chicken')--\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.removeIngredient("Onions");
		System.out.println("baronBurger.removeIngredient('Onions')--"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.changePatties("Beef");
		System.out.println("baronBurger.changePatties('Beef')--\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.removeCategory("Cheese");
		System.out.println("baronBurger.removeCategory('Cheese')-- \t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.addCategory("Cheese");
		System.out.println("baronBurger.addCategory('Cheese')-- \t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.removeCategory("Veggies");
		System.out.println("baronBurger.removeCategory('Veggies')--\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.addPatty();
		System.out.println("baronBurger.addPatty() -- \t\t" + testBaronBurger
		        + "\n");

		testBaronBurger.removeCategory("Sauces");
		System.out.println("baronBurger.removeCategory('Sauces')--\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.changePatties("Chicken");
		System.out.println("baronBurger.changePatties('Chicken')--\t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.removePatty();
		System.out.println("baronburger.removePatty()-- \t\t" + testBaronBurger
		        + "\n");

		testBaronBurger.addPatty();
		System.out.println("baronBurger.addPatty() -- \t\t" + testBaronBurger
		        + "\n");

		testBaronBurger.addPatty();
		System.out.println("baronBurger.addPatty() -- \t\t" + testBaronBurger
		        + "\n");
		testBaronBurger.addPatty();
		System.out.println("baronBurger.addPatty() -- \t\t" + testBaronBurger
		        + "\n");

		testBaronBurger.addCategory("with");
		System.out.println("baronBurger.addCategory('with')-- \t"
		        + testBaronBurger.toString() + "\n");
		testBaronBurger.addCategory("Sauces");
		System.out.println("baronBurger.addCategory('Sauces')-- \t"
		        + testBaronBurger.toString() + "\n");

		testBaronBurger.clear();
		System.out
		        .println("testBaronBurger.clear()  -- \t\t" + testBaronBurger);

	}
}

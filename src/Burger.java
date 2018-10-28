/**
 * 
 * @author Brandon Chambers 
 *         Brendan Crawford
 * 
 *         the Burger Class
 * 
 *         The Burger class may not use any List type in Java or arrays, it must
 *         be fully implemented in the MyStack class to store ingredients. Hint:
 *         The Burger Baron chefs tend to stack ingredients in two stacks on top
 *         of each bun and join them top-to-top in the final construction.
 * 
 *
 */
public class Burger
{

	MyStack<String> burger = new MyStack<String>();
	MyStack<String> recipe = new MyStack<String>();
	MyStack<String> tempBurger = new MyStack<String>();
	MyStack<String> tempRecipe = new MyStack<String>();
	
	String pattyCount;
	String myPattyType;

	Burger(boolean theWorks)
	{
		myPattyType="Beef";
		pattyCount="Single";

		recipe.push("Bun");
		recipe.push("Ketchup");
		recipe.push("Mustard");
		recipe.push("Mushrooms");
		recipe.push(myPattyType);
		recipe.push("Cheddar");
		recipe.push("Mozzarella");
		recipe.push("Pepperjack");
		recipe.push("Onions");
		recipe.push("Tomato");
		recipe.push("Lettuce");
		recipe.push("Baron-Sauce");
		recipe.push("Mayonnaise");
		recipe.push("Bun");
		recipe.push("Pickle");

		if (theWorks)
		{
			burger.push("Bun");
			burger.push("Ketchup");
			burger.push("Mustard");
			burger.push("Mushrooms");
			burger.push("Beef");
			burger.push("Cheddar");
			burger.push("Mozzarella");
			burger.push("Pepperjack");
			burger.push("Onions");
			burger.push("Tomato");
			burger.push("Lettuce");
			burger.push("Baron-Sauce");
			burger.push("Mayonnaise");
			burger.push("Bun");
			burger.push("Pickle");
		}
		else
		{
			burger.push("Bun");
			burger.push(myPattyType);
			burger.push("Bun");
		}
	}

	// changes all patties in the Burger to the pattyType
	public void changePatties(String pattyType)
	{
		while (!burger.isEmpty())
		{
			if (burger.peek().equals(myPattyType))
			{
				burger.pop();
				burger.push(pattyType);
			}
				tempBurger.push(burger.pop());
		}
		while (!tempBurger.isEmpty())
		{
			burger.push(tempBurger.pop());
		}
		myPattyType = pattyType;
	}

	// adds one patty to the Burger, up to a max of 3
	public void addPatty()
	{
		boolean cheeseDiscovered = false;
		boolean addedPatty = false;// did we already add the patty?
		String pattyTypeCatcher = "Beef";
		final int maxPatty = 3;
		int pattyCount = 0;
		int cheeseCount = 0;
		

		while (!burger.isEmpty())
		{
			if (burger.peek() == "Chicken" || burger.peek() == "Veggie"
			        || burger.peek() == "Beef")
			{
				pattyTypeCatcher = burger.peek();
				pattyCount++;
				tempBurger.push(burger.pop());
			}
			else if (burger.peek() == "Pepperjack"
			        || burger.peek() == "Cheddar"
			        || burger.peek() == "Mozzarella")
			{
				cheeseDiscovered = true;
				cheeseCount++;
				tempBurger.push(burger.pop());
			}
			else
				tempBurger.push(burger.pop());
		}
		while (!tempBurger.isEmpty())
		{
			if (tempBurger.peek() == "Pepperjack"
			        || tempBurger.peek() == "Cheddar"
			        || tempBurger.peek() == "Mozzarella")
			{
				cheeseCount--;
				if (cheeseCount == 0 && !addedPatty && pattyCount < maxPatty)
				{
					burger.push(tempBurger.pop());
					addedPatty = true;
					pattyCount++;
					burger.push(pattyTypeCatcher);
				}
				else
					burger.push(tempBurger.pop());
			}
			else if (!cheeseDiscovered)
			{
				if (tempBurger.peek() == "Chicken"
				        || tempBurger.peek() == "Veggie"
				        || tempBurger.peek() == "Beef")
				{
					if (!addedPatty && pattyCount < maxPatty)
					{
						burger.push(tempBurger.pop());
						addedPatty = true;
						pattyCount++;
						burger.push(pattyTypeCatcher);
					}
					else
						burger.push(tempBurger.pop());
				}
				else
					burger.push(tempBurger.pop());
			}
			else
				burger.push(tempBurger.pop());

		}
	}

	// removes one patty from Burger, down to a minimum of 1
	public void removePatty()
	{
		String pattyTypeCatcher = null;
		
		boolean taskComplete = false;
		int pattyCount = 0;

		while (!burger.isEmpty())
		{
			if (burger.peek() == "Beef" || burger.peek() == "Chicken"
			        || burger.peek() == "Veggie")
			{
				pattyCount++;
				pattyTypeCatcher = burger.peek();
			}
			tempBurger.push(burger.pop());
		}
		while (!tempBurger.isEmpty())
		{
			if (tempBurger.peek() == pattyTypeCatcher && pattyCount > 1
			        && !taskComplete)
			{
				tempBurger.pop();
				taskComplete = true;
			}
			else
			{
				burger.push(tempBurger.pop());
			}
		}
	}

	// adds all items of the type to the Burger in the proper locations
	public void addCategory(String type)
	{
		if(type.equals("Cheese"))
		{
			addIngredient("Cheddar");
			addIngredient("Mozzarella");
			addIngredient("Pepperjack");
		}
		if(type.equals("Sauce"))
		{
			addIngredient("Baron-Sauce");
			addIngredient("Ketchup");
			addIngredient("Mustard");
			addIngredient("Mayonnaise");
		}
		if(type.equals("Veggies"))
		{
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Pickle");
			addIngredient("Mushrooms");
		}
	}

	// removes all items of the type from the Burger
	public void removeCategory(String type)
	{
		if (type.equals("Cheese"))
		{
			removeIngredient("Cheddar");
			removeIngredient("Mozzarella");
			removeIngredient("Pepperjack");
		}
		else if (type.equals("Sauce"))
		{
			removeIngredient("Baron-Sauce");
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
		}
		else if (type.equals("Veggies"))
		{
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		}
	}

	// adds the ingredient type to the Burger in proper location
	public void addIngredient(String type)
	{
		while (!recipe.isEmpty())
		{
			if (recipe.peek().equals(burger.peek()))
			{
				tempBurger.push(burger.pop());
				//tempRecipe.push(recipe.pop());
			}
			if (recipe.peek().equals(type))
			{
				burger.push(type);
				//tempRecipe.push(recipe.pop());
			}
			tempRecipe.push(recipe.pop());
		}
		
		while (!tempRecipe.isEmpty())
		{
			recipe.push(tempRecipe.pop());
		}
		while (!tempBurger.isEmpty())
		{
			burger.push(tempBurger.pop());
		}
	}

	// removes the ingredient type from the Burger
	public void removeIngredient(String type)
	{
		while (!burger.isEmpty())
		{
			if (burger.peek().equals(type))
			{
				burger.pop();
			}
			tempBurger.push(burger.pop());
		}
		while (!tempBurger.isEmpty())
		{
			burger.push(tempBurger.pop());
		}
	}

	public void clear()
	{
		while (!burger.isEmpty())
		{
			burger.pop();
		}
	}

	// converts the Burger to a String for display
	// really just calls MyStack toString
	public String toString()
	{
		return burger.toString();
	}
}

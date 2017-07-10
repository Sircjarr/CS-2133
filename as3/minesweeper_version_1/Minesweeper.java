public class Minesweeper 
{
	public static void main(String[] args)
	{
		new MFrame(); 
	}
}

/*
Rails is built around the model-view-controller pattern. It’s a simple concept: separate the data, logic,
 and display layers of your program. This lets you split functionality cleanly, just like having 
 separate HTML, CSS and JavaScript files prevents your code from mushing together. Here’s the MVC breakdown:

Models [which, in MVC, are typically the program's data, state, and logic] are classes that talk to the 
	database. You find, create and save models, so you don’t (usually) have to write SQL. Rails has a class 
	to handle the magic of saving to a database when a model is updated.
Controllers take user input (like a URL) and decide what to do (show a page, order an item, post a comment). 
	They may initially have business logic, like finding the right models or changing data. As your Rails 
	ninjitsu improves, constantly refactor and move business logic into the model (fat model, skinny controller). 
	**Ideally, controllers just take inputs, call model methods, and pass outputs to the view (including
	error messages).
Views display the output, usually HTML. They use ERB and this part of Rails is like PHP – you use HTML
	templates with some Ruby variables thrown in. Rails also makes it easy to create views as XML(for web 
	services/RSS feeds) or JSON (for AJAX calls). The MVC pattern is key to building a readable, maintainable 
	and easily-updateable web app.*/
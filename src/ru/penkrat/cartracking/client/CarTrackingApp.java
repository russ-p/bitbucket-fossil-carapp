package ru.penkrat.cartracking.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CarTrackingApp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		final SimplePanel simplePanel = new SimplePanel();
		
		MenuBar menuBar = new MenuBar(false);
		verticalPanel.add(menuBar);
		menuBar.setAutoOpen(true);
		MenuBar menuBar_1 = new MenuBar(true);
		menuBar_1.setAutoOpen(true);
		
		MenuItem mntmNewOrEdit = new MenuItem("New / Edit", false, menuBar_1);
		
		MenuItem mntmNewCar = new MenuItem("Car", false, new Command() {
			public void execute() {
				simplePanel.clear();
				simplePanel.add(new CarEditForm());
			}
		});
		menuBar_1.addItem(mntmNewCar);
		
		MenuItem mntmNewPerson = new MenuItem("Person", false, new Command() {
			public void execute() {
				simplePanel.clear();
				simplePanel.add(new PersonEditForm());
			}
		});
		menuBar_1.addItem(mntmNewPerson);
		
		MenuItem mntmNewViolation = new MenuItem("Traffic violation", false, new Command() {
			public void execute() {
				simplePanel.clear();
				simplePanel.add(new ViolationEditForm());
			}
		});
		menuBar_1.addItem(mntmNewViolation);
		menuBar.addItem(mntmNewOrEdit);
		
		MenuItem mntmSearch = new MenuItem("Search", false, new Command() {
			public void execute() {
				simplePanel.clear();
				simplePanel.add(new SearchPage());
			}
		});
		menuBar.addItem(mntmSearch);
		
		MenuItem mntmImport = new MenuItem("Import", false, (Command) null);
		menuBar.addItem(mntmImport);
		

		verticalPanel.add(simplePanel);
		simplePanel.setSize("100%", "100%");
		
		Label lblNewLabel_1 = new Label("Welcome");
		lblNewLabel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		simplePanel.setWidget(lblNewLabel_1);
		lblNewLabel_1.setSize("100%", "100%");

		
	}
}

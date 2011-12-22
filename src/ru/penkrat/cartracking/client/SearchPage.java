package ru.penkrat.cartracking.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.Column;
import java.util.Date;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class SearchPage extends Composite {
	
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	
	public SearchPage() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("595px", "100%");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setSize("100%", "25px");
		
		Label lblNewLabel = new Label("New label");
		horizontalPanel.add(lblNewLabel);
		
		TextBox textBox = new TextBox();
		horizontalPanel.add(textBox);
		
		Label lblNewLabel_1 = new Label("New label");
		horizontalPanel.add(lblNewLabel_1);
		
		final TextBox textBox_1 = new TextBox();
		horizontalPanel.add(textBox_1);
		
		final Button btnSearchButton = new Button("New button");
		btnSearchButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btnSearchButton.setEnabled(false);
				greetingService.greetServer("ololo",
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
								textBox_1.setText(result);
								btnSearchButton.setEnabled(true);
							}
						});
			}
		});
		btnSearchButton.setText("Search");
		horizontalPanel.add(btnSearchButton);
		
		CellTable<String> cellTable = new CellTable<String>();
		verticalPanel.add(cellTable);
		cellTable.setSize("100%", "329px");
		
		TextColumn<String> textColumn_1 = new TextColumn<String>() {
			@Override
			public String getValue(String object) {
				return object.toString();
			}
		};
		cellTable.addColumn(textColumn_1, "Person");
		
		TextColumn<String> textColumn = new TextColumn<String>() {
			@Override
			public String getValue(String object) {
				return object.toString();
			}
		};
		cellTable.addColumn(textColumn, "Car");
		
		Column<String, Date> column = new Column<String, Date>(new DateCell()) {
			@Override
			public Date getValue(String object) {
				return (Date) null;
			}
		};
		cellTable.addColumn(column, "Date from");
		
		Column<String, Date> column_1 = new Column<String, Date>(new DateCell()) {
			@Override
			public Date getValue(String object) {
				return (Date) null;
			}
		};
		cellTable.addColumn(column_1, "to");
	}

}

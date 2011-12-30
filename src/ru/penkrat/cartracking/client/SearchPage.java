package ru.penkrat.cartracking.client;

import com.google.gwt.user.client.Window;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.client.dto.PersonDTO;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class SearchPage extends Composite {

	private static class SearchResult {
		private final PersonDTO person;
		private final CarDTO car;

		public SearchResult(PersonDTO person, CarDTO car) {
			this.person = person;
			this.car = car;
		}
	}

	/**
	 * Get a cell value from a record.
	 * 
	 * @param <C>
	 *            the cell type
	 */
	private static interface GetValue<C> {
		C getValue(SearchResult contact);
	}

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private CellTable<SearchResult> cellTable;

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
				greetingService
						.getPersons(new AsyncCallback<List<PersonDTO>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(List<PersonDTO> result) {
								// TODO Auto-generated method stub
								List<SearchResult> s_result = new ArrayList<SearchResult>();
								CarDTO dummy_car = new CarDTO(1L, "model",
										"2011", "black", 1.1, 2.5, "123",
										"1234ht-7");
								for (int i = 0; i < result.size(); i++) {
									s_result.add(new SearchResult(
											result.get(i), dummy_car));
								}
								cellTable.setRowCount(s_result.size(), true);
								cellTable.setRowData(0, s_result);
							}
						});
			}
		});
		btnSearchButton.setText("Search");
		horizontalPanel.add(btnSearchButton);

		cellTable = new CellTable<SearchResult>();
		verticalPanel.add(cellTable);
		cellTable.setSize("100%", "329px");
	
		Column<SearchResult, String> ct = new Column<SearchResult, String>(
				new ClickableTextCell()) {
			@Override
			public String getValue(SearchResult object) {
				return object.person.toString();
			}
		};
		
		ct.setFieldUpdater(new FieldUpdater<SearchResult, String>() {
			@Override
			public void update(int index, SearchResult object, String value) {
				PersonEditForm ped = new PersonEditForm();
				ped.setPerson(object.person);
				ped.setReadOnly(true);
				CarTrackingApp.getInstance().setForm(ped);
			}
		});
		cellTable.addColumn(ct, "Person");

		Column<SearchResult, String> ct2 = new Column<SearchResult, String>(
				new ClickableTextCell()) {
			@Override
			public String getValue(SearchResult object) {
				return object.car.toString();
			}
		};
		
		ct2.setFieldUpdater(new FieldUpdater<SearchResult, String>() {
			@Override
			public void update(int index, SearchResult object, String value) {
				CarEditForm ped = new CarEditForm();
				ped.setCar(object.car);
				ped.setReadOnly(true);
				CarTrackingApp.getInstance().setForm(ped);
			}
		});
		cellTable.addColumn(ct2, "Car");
		// Column<String, Date> column = new Column<String, Date>(new
		// DateCell()) {
		// @Override
		// public Date getValue(String object) {
		// return (Date) null;
		// }
		// };
		// cellTable.addColumn(column, "Date from");
		//
		// Column<String, Date> column_1 = new Column<String, Date>(new
		// DateCell()) {
		// @Override
		// public Date getValue(String object) {
		// return (Date) null;
		// }
		// };
		// cellTable.addColumn(column_1, "to");
	}

}

package ru.penkrat.cartracking.client;

import ru.penkrat.cartracking.client.dto.CarDTO;
import ru.penkrat.cartracking.shared.FieldVerifier;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;

public class CarEditForm extends DockPanel implements ClickHandler {

	private CarDTO car;
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private Boolean readOnly;
	private TextBox tbColor;
	private IntegerBox tbYear;
	private TextBox tbModel;
	private DoubleBox tbDistance;
	private TextBox tbVinNumber;
	private DoubleBox tbEngineLiters;
	private TextBox tbRegistrationPlate;
	private Label lblNewOrEdit;
	private Button submitButton;

	public CarEditForm() {
		lblNewOrEdit = new Label("New label");
		lblNewOrEdit
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(lblNewOrEdit, DockPanel.NORTH);

		HorizontalPanel form = new HorizontalPanel();
		add(form, DockPanel.CENTER);
		form.setWidth("406px");
		form.addStyleName("table-center");
		form.addStyleName("demo-FormPanel");

		VerticalPanel holder = new VerticalPanel();

		holder.add(new Label("Model"));

		tbModel = new TextBox();
		holder.add(tbModel);

		holder.add(new Label("Year"));

		tbYear = new IntegerBox();
		holder.add(tbYear);

		Label lblColor = new Label("Color");
		holder.add(lblColor);

		tbColor = new TextBox();
		holder.add(tbColor);

		Label lblDistancekm = new Label("Distance (km)");
		holder.add(lblDistancekm);

		tbDistance = new DoubleBox();
		holder.add(tbDistance);

		Label lblVinNumber = new Label("VIN number");
		holder.add(lblVinNumber);

		tbVinNumber = new TextBox();
		holder.add(tbVinNumber);

		Label lblEngineLiters = new Label("Engine liters");
		holder.add(lblEngineLiters);

		tbEngineLiters = new DoubleBox();
		holder.add(tbEngineLiters);

		Label lblRegistarationPlate = new Label("Registaration plate");
		holder.add(lblRegistarationPlate);

		tbRegistrationPlate = new TextBox();
		holder.add(tbRegistrationPlate);

		submitButton = new Button("Submit", this);
		Button btnNewButton = new Button("New button", this);

		holder.add(submitButton);

		form.add(holder);

		VerticalPanel verticalPanel = new VerticalPanel();
		form.add(verticalPanel);

		CellTable<Object> cellTable = new CellTable<Object>();
		verticalPanel.add(cellTable);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.add(btnNewButton);
	}

	public void setCar(CarDTO car) {
		this.car = car;
		tbColor.setText(car.getColor());
		tbDistance.setValue(car.getDistance());
		tbEngineLiters.setValue(car.getEngineLiters());
		tbModel.setText(car.getModel());
		tbRegistrationPlate.setText(car.getRegistrationPlate());
		tbVinNumber.setText(car.getVinNumber());
		tbYear.setText(car.getYear());

		lblNewOrEdit.setText("Edit Car");
	}

	private void saveCar() {
		if (car == null) {
			car = new CarDTO();
		}
		if (tbModel.getText().isEmpty()) {
			Window.alert("'" + tbModel.getText() + "' is not a valid name.");
			return;
		}
		;
		if (tbColor.getText().isEmpty()) {
			Window.alert("'" + tbColor.getText() + "' is not a valid name.");
			return;
		}
		;
		if (tbDistance.getText().isEmpty()) {
			Window.alert("'" + tbDistance.getText() + "' is not a valid name.");
			return;
		}
		;
		if (tbEngineLiters.getText().isEmpty()) {
			Window.alert("'" + tbEngineLiters.getText()
					+ "' is not a valid name.");
			return;
		}
		;
		if (tbRegistrationPlate.getText().isEmpty()) {
			Window.alert("'" + tbRegistrationPlate.getText()
					+ "' is not a valid name.");
			return;
		}
		;
		if (!FieldVerifier.isValidVinNumber(tbVinNumber.getText())) {
			Window.alert("'" + tbVinNumber.getText()
					+ "' is not a valid VIN Number.");
			return;
		}
		;
		if (tbYear.getText().isEmpty()) {
			Window.alert("'" + tbYear.getText() + "' is not a valid name.");
			return;
		}
		;
		car.setColor(tbColor.getText());
		car.setDistance(tbDistance.getValue());
		car.setEngineLiters(tbEngineLiters.getValue());
		car.setModel(tbModel.getText());
		car.setRegistrationPlate(tbRegistrationPlate.getText());
		car.setVinNumber(tbVinNumber.getText());
		car.setYear(tbYear.getText());
		greetingService.saveCar(car, new AsyncCallback<Long>() {

			public void onSuccess(Long result) {
				car.setId(result);
				lblNewOrEdit.setText("Edit Car: " + car.toString());
			}

			public void onFailure(Throwable caught) {
				lblNewOrEdit.setText("Save car failed!");
			}
		});
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == submitButton) {
			if (readOnly) {
				setReadOnly(false);
			} else {
				saveCar();
			}
		}
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		tbColor.setEnabled(!readOnly);
		tbYear.setEnabled(!readOnly);
		tbModel.setEnabled(!readOnly);
		tbDistance.setEnabled(!readOnly);
		tbVinNumber.setEnabled(!readOnly);
		tbEngineLiters.setEnabled(!readOnly);
		tbRegistrationPlate.setEnabled(!readOnly);
		if (readOnly) {
			lblNewOrEdit.setText("View");
			submitButton.setHTML("Edit");
		}else{
			submitButton.setHTML("Save");
		}
	}
	
	public Boolean getReadOnly(){
		return readOnly;
	}
}

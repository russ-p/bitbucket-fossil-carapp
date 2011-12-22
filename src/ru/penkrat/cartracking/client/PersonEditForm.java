package ru.penkrat.cartracking.client;

import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

public class PersonEditForm extends DockPanel implements ClickHandler {

	private PersonDTO person;

	private TextBox textBoxName;
	private TextBox textBoxSurname;
	private TextBox textBoxMiddleName;
	private ListBox comboBox;
	private DateBox dateBox;
	private Label lblNewOrEdit;

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
		textBoxName.setText(person.getName());
		textBoxName.setText(person.getName());
		textBoxMiddleName.setText(person.getMiddlename());
		textBoxSurname.setText(person.getSurname());
		comboBox.setSelectedIndex(person.getSex());
		lblNewOrEdit.setText("Edit Person");
	}

	@SuppressWarnings("deprecation")
	public PersonEditForm() {

		lblNewOrEdit = new Label("New Person");
		add(lblNewOrEdit, DockPanel.NORTH);

		FormPanel form = new FormPanel();
		add(form, DockPanel.CENTER);
		form.setWidth("248px");

		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.addStyleName("table-center");
		form.addStyleName("demo-FormPanel");

		VerticalPanel holder = new VerticalPanel();

		holder.add(new Label("Surname"));
		textBoxSurname = new TextBox();
		textBoxSurname.setName("userid");
		holder.add(textBoxSurname);

		Label lblNewLabel_1 = new Label("Name");
		holder.add(lblNewLabel_1);

		textBoxName = new TextBox();
		holder.add(textBoxName);

		Label lblNewLabel_2 = new Label("Middle Name");
		holder.add(lblNewLabel_2);

		textBoxMiddleName = new TextBox();
		holder.add(textBoxMiddleName);

		Label lblNewLabel_3 = new Label("Year of Birth");
		holder.add(lblNewLabel_3);

		IntegerBox integerBox = new IntegerBox();
		holder.add(integerBox);

		Label lblNewLabel_4 = new Label("Gender");
		holder.add(lblNewLabel_4);

		comboBox = new ListBox();
		comboBox.addItem("Male");
		comboBox.addItem("Female");
		holder.add(comboBox);
		comboBox.setWidth("90px");

		Label lblNewLabel_5 = new Label("Drive license recive date");
		holder.add(lblNewLabel_5);

		dateBox = new DateBox();
		dateBox.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		holder.add(dateBox);

		Hidden hidden = new Hidden("Hidden name");
		holder.add(hidden);

		Button button = new Button("Submit", this);
		holder.add(button);
		button.setWidth("151px");

		form.add(holder);
		holder.setWidth("204px");

		VerticalPanel verticalPanel = new VerticalPanel();
		add(verticalPanel, DockPanel.EAST);

		CellTable<Object> cellTable = new CellTable<Object>();
		verticalPanel.add(cellTable);
	}

	public void setReadOnly(Boolean readOnly) {
		textBoxName.setEnabled(readOnly);
		textBoxSurname.setEnabled(readOnly);
		textBoxMiddleName.setEnabled(readOnly);
		comboBox.setEnabled(readOnly);
		dateBox.setEnabled(readOnly);
		if (readOnly) {
			lblNewOrEdit.setText("View");
		}
	}

	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		savePerson();
	}

	private void savePerson() {
		if (person == null) {
			person = new PersonDTO();
		}
		if (!FieldVerifier.isValidName(textBoxName.getText())) {
			Window.alert("'" + textBoxName.getText() + "' is not a valid Name.");
			return;
		}
		if (!FieldVerifier.isValidName(textBoxSurname.getText())) {
			Window.alert("'" + textBoxSurname.getText()
					+ "' is not a valid name.");
			return;
		}
		person.setName(textBoxName.getText());
		person.setMiddlename(textBoxMiddleName.getText());
		person.setSurname(textBoxSurname.getText());
		person.setSex(comboBox.getSelectedIndex());
		greetingService.savePerson(person, new AsyncCallback<Long>() {

			public void onSuccess(Long result) {
				person.setId(result);
				lblNewOrEdit.setText("Edit Person: " + person.toString());
			}

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				lblNewOrEdit.setText("Save person failed!");
			}
		});
	}
}

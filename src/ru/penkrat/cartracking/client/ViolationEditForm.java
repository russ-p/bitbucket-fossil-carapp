package ru.penkrat.cartracking.client;

import java.util.Iterator;
import java.util.List;

import ru.penkrat.cartracking.client.dto.PersonDTO;
import ru.penkrat.cartracking.client.dto.ViolationDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.datepicker.client.DateBox;

public class ViolationEditForm extends Composite implements ClickHandler {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	private ViolationDTO violationDTO;
	private TextBox textBox;
	private DateBox dateBox;
	private  ListBox comboBox;

	public ViolationEditForm() {

		DockPanel dockPanel = new DockPanel();
		initWidget(dockPanel);

		Label lblNewLabel = new Label("Edit violation");
		dockPanel.add(lblNewLabel, DockPanel.NORTH);

		FormPanel formPanel = new FormPanel();
		dockPanel.add(formPanel, DockPanel.WEST);

		VerticalPanel verticalPanel = new VerticalPanel();
		formPanel.setWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");

		Label lblNewLabel_1 = new Label("Person");
		verticalPanel.add(lblNewLabel_1);

		comboBox = new ListBox();
		verticalPanel.add(comboBox);

		Label lblNewLabel_2 = new Label("Violation");
		verticalPanel.add(lblNewLabel_2);

		textBox = new TextBox();
		verticalPanel.add(textBox);

		Label lblNewLabel_3 = new Label("Date of violation");
		verticalPanel.add(lblNewLabel_3);

		dateBox = new DateBox();
		verticalPanel.add(dateBox);

		Label lblNewLabel_4 = new Label("New label");
		verticalPanel.add(lblNewLabel_4);

		Button btnNewButton = new Button("New button");
		verticalPanel.add(btnNewButton);

		greetingService.getPersons(new AsyncCallback<List<PersonDTO>>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			public void onSuccess(List<PersonDTO> result) {
				// TODO Auto-generated method stub
				Iterator<PersonDTO> iter = result.iterator();
				while (iter.hasNext()) {
					PersonDTO p = (PersonDTO) iter.next();
					comboBox.addItem(p.getFullName(), String.valueOf(p.getId()));
				}
			}
		});
	}

	private void saveViolation() {
		if (violationDTO == null) {
			violationDTO = new ViolationDTO();
		}
		violationDTO.setViolationDate(dateBox.getValue());
		violationDTO.setViolationText(textBox.getText());
		//comboBox.get;
		greetingService.saveViolation(violationDTO, new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Long result) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		saveViolation();
	};
}

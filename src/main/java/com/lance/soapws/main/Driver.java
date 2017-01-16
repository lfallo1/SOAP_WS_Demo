package com.lance.soapws.main;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.bycwebsolutions.patient.Gender;
import org.bycwebsolutions.patient.Insurance;
import org.bycwebsolutions.patient.Patient;
import org.bycwebsolutions.patient.PaymentType;

public class Driver {

	public static void main(String[] args) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = context.createMarshaller();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		//create patient
		Patient patient = createPatient("Lance");
		
		//marshal
		StringWriter writer = new StringWriter();
		marshaller.marshal(patient, writer);
		String output = writer.toString();
		System.out.println(output);
		
		//unmarshal
		Patient patientUnmarshal = (Patient) unmarshaller.unmarshal(new StringReader(writer.toString()));
		System.out.println(patientUnmarshal.toString());
	}

	private static Patient createPatient(String name) {
		Insurance insurance = new Insurance();
		insurance.setLimit(2500);
		insurance.setProvider("Blue Cross");
		
		PaymentType paymentType = new PaymentType();
		paymentType.setInsurance(insurance);
		
		Patient patient = new Patient();
		patient.setAge(30);
		patient.setGender(Gender.M);
		patient.setId(1);
		patient.setName(name);
		patient.getEmail().addAll(Arrays.asList("fallon.lance@gmail.com", "lance1086@hotmail.com"));
		patient.getPhones().add("4108425526");
		patient.getPhones().add("4109948847");
		patient.getPhones().add("4438827728");
		patient.setPayment(paymentType);
		return patient;
	}

}

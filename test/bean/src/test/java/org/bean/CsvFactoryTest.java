package org.bean;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Test;


public class CsvFactoryTest {

	public static class Model1 {
		public Model1() {
		}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public ArrayList<Model2> getModelB() {
			return modelB;
		}
		public void setModelB(ArrayList<Model2> modelB) {
			this.modelB = modelB;
		}
		private String id;
		private ArrayList<Model2> modelB;
		
		private int intTest;
		
		public int getIntTest() {
			return intTest;
		}
		public void setIntTest(int intTest) {
			this.intTest = intTest;
		}
		public double getDoubleTest() {
			return doubleTest;
		}
		public void setDoubleTest(double doubleTest) {
			this.doubleTest = doubleTest;
		}
		private double doubleTest;
		
		private boolean booleanTest;
		
		public boolean isBooleanTest() {
			return booleanTest;
		}
		public void setBooleanTest(boolean booleanTest) {
			this.booleanTest = booleanTest;
		}
		@Override
		public String toString() {
			if(modelB == null) {
				return "id =" + id + "intTest=" + intTest + " doubleTest=" + doubleTest + " booleanTest=" + booleanTest ;
			} else {
				return "id =" + id  + "intTest=" + intTest + " doubleTest=" + doubleTest + " booleanTest=" + booleanTest+ " model B=" + modelB.toString();
			}
		}
	}
	
	public static class Model2 {
		public Model2() {
		}
		
		public String getParma1() {
			return parma1;
		}
		public void setParma1(String parma1) {
			this.parma1 = parma1;
		}
		public String getParma2() {
			return parma2;
		}
		public void setParma2(String parma2) {
			this.parma2 = parma2;
		}
		public HashMap<String, String> getOther() {
			return other;
		}
		public void setOther(HashMap<String, String> other) {
			this.other = other;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		private String parma1;
		private String parma2;
		private HashMap<String, String> other;
		private Date date;
		
		public String toString() {
			if(other == null) {
				return "parma1 =" + parma1 + " parma2=" + parma2 + " date=" + date;
			} else {
				return "parma1 =" + parma1 + " parma2=" + parma2 + " date=" + date + " other=" + other;
			}
		}
		
	}
	
	@Test
	public void testBean() {
		Properties convertProp = new Properties();
		try {
			convertProp.load(new FileInputStream("I:\\code\\Test\\test\\bean\\src\\test\\resources\\TestCsvFactory.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File csvFile = new File("I:\\code\\Test\\test\\bean\\src\\test\\resources\\TestCsvFactory.csv");
		List<Model1> modelArray = CsvFactory.mapCsvToModel(convertProp, csvFile, Model1.class);
		for(Model1 m: modelArray) {
			System.out.println(m);
		}
		assertEquals(modelArray.size(), 2);
	}

}

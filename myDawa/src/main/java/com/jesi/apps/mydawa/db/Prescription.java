package com.jesi.apps.mydawa.db;

public class Prescription {
	int id;
	int drug_id;
	String full_adult;
	String caution_pregnant;
	String caution_all;
	
	public Prescription(){
		
	}

	public Prescription(int id, int drug_id, String full_adult,
			String caution_pregnant, String caution_all) {
		super();
		this.id = id;
		this.drug_id = drug_id;
		this.full_adult = full_adult;
		this.caution_pregnant = caution_pregnant;
		this.caution_all = caution_all;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(int drug_id) {
		this.drug_id = drug_id;
	}

	public String getFull_adult() {
		return full_adult;
	}

	public void setFull_adult(String full_adult) {
		this.full_adult = full_adult;
	}

	public String getCaution_pregnant() {
		return caution_pregnant;
	}

	public void setCaution_pregnant(String caution_pregnant) {
		this.caution_pregnant = caution_pregnant;
	}

	public String getCaution_all() {
		return caution_all;
	}

	public void setCaution_all(String caution_all) {
		this.caution_all = caution_all;
	}
	
	@Override
	public String toString(){
		return "Dosage Full (Adult): " + this.full_adult + ", Caution For Expectants: " + this.caution_pregnant + ", Caution For All: " + this.caution_all;
	}
}
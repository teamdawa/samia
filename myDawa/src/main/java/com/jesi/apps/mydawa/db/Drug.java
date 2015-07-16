package com.jesi.apps.mydawa.db;

public class Drug {
	int id;
	String brand_name;
	String scientific_name;
	String full_dose;
	String avoid;
	String take_time;
	String not_for;
	String side_effects;
	String uses;

	public Drug() {
		super();
	}

	public Drug(int id, String brand_name, String scientific_name, String full_dose, String avoid, String take_time, String not_for, String side_effects, String uses) {
		super();
		this.id = id;
		this.brand_name = brand_name;
		this.scientific_name = scientific_name;
		this.full_dose = full_dose;
		this.avoid = avoid;
		this.take_time = take_time;
		this.not_for = not_for;
		this.side_effects = side_effects;
		this.uses = uses;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public String getSide_effects() {
		return side_effects;
	}

	public void setSide_effects(String side_effects) {
		this.side_effects = side_effects;
	}

	public String getFull_dose() {
		return full_dose;
	}

	public void setFull_dose(String full_dose) {
		this.full_dose = full_dose;
	}

	public String getAvoid() {
		return avoid;
	}

	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}

	public String getTake_time() {
		return take_time;
	}

	public void setTake_time(String take_time) {
		this.take_time = take_time;
	}

	public String getNot_for() {
		return not_for;
	}

	public void setNot_for(String not_for) {
		this.not_for = not_for;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getScientific_name() {
		return scientific_name;
	}

	public void setScientific_name(String scientific_name) {
		this.scientific_name = scientific_name;
	}

	@Override
	public String toString() {
		return this.brand_name;
	}
}
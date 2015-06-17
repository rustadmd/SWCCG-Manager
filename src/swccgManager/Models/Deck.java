package swccgManager.Models;

import java.sql.Date;

public class Deck {
	private String name, description, strategy, matchups, notes;
	private Date createDate, modifyDate;
	
	/**
	 * Creates a deck object
	 * @param n Deck Name
	 * @param d Description
	 * @param s Strategy
	 * @param m Matchups
	 * @param notes Notes
	 * @param cd CreateDate
	 * @param md ModifyDate
	 */
	public Deck(String n, String d, String s, String m, String notes, Date cd, Date md){
		name = n;
		description = d;
		strategy = s;
		matchups = m;
		this.notes = notes;
		createDate = cd;
		modifyDate = md;
	}
	
	public String toString(){ return name; }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getMatchups() {
		return matchups;
	}

	public void setMatchups(String matchups) {
		this.matchups = matchups;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}

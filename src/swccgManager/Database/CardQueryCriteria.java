/**
 * This stores the criteria for sorting cards
 */
package swccgManager.Database;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jun 7, 2014
 *
 */
public class CardQueryCriteria {
	
	public enum Attribute
	{
		NAME, SIDE, TYPE, SUBTYPE, EXPANSION, RARITY, CHARACTERISTICS, LORE, GAMETEXT
	}
	private String[] criteriaList;

	public CardQueryCriteria()
	{
		int numCriteria = 9;
		criteriaList = new String[numCriteria];
		
		//criteria should be all blanks initially
		for(int i=0; i<numCriteria; i++)
		{
			criteriaList[i] = "%";
		}
	}
	
	/**
	 * Sets the criteria for sorting. Functions in a SQL WHERE Clause.
	 * @param attribute the attribute which will be used to sort
	 * @param criteria write as a where clause, ex. WHERE column = 'criteria'
	 */
	public void setCriteria(Attribute attribute, String criteria)
	{
		criteriaList[attribute.ordinal()] = criteria;
	}
	
	public String getCriteria(Attribute attribute)
	{
		return criteriaList[attribute.ordinal()];
	}
}

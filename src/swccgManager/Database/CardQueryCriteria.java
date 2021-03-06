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
		NAME, SIDE, TYPE, SUBTYPE, EXPANSION, RARITY, CHARACTERISTICS, LORE, GAMETEXT,
		ICON, REALM, COL_CONTAINS, COL_NAME, DECK_CONTAINS, DECK_NAME
	}
	private String[] criteriaList;

	public CardQueryCriteria()
	{
		int numCriteria = 15;
		criteriaList = new String[numCriteria];

		//criteria should be all blanks initially
		int numInClauses = 2;//realm, col_contains
		for(int i=0; i<numCriteria - numInClauses; i++)
		{
			criteriaList[i] = "%";
		}
		setCriteria(Attribute.REALM, "'A New Hope', 'Cloud City', 'Dagobah', 'Death Star II', 'Endor', 'Enhanced Cloud City', 'Enhanced Jabba''s Palace', 'Enhanced Premiere Pack', 'Hoth', 'Hoth 2 Player', 'Jabba''s Palace', 'Jabba''s Palace Sealed Deck', 'Premiere', 'Premiere 2 Player', 'Reflections II', 'Reflections III', 'Special Edition', 'Third Anthology', 'Jedi Pack', 'Official Tournament Sealed Deck', 'Rebel Leader Cards', 'Coruscant', 'Tatooine', 'Theed Palace'" );
		setCriteria(Attribute.COL_CONTAINS, null); //set filter to blank initially
		setCriteria(Attribute.COL_NAME, null);
        setCriteria(Attribute.DECK_CONTAINS, null);
        setCriteria(Attribute.DECK_NAME, null);
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

/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package npc.model;

import lineage2.gameserver.model.Creature;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.instances.RaidBossInstance;
import lineage2.gameserver.templates.npc.NpcTemplate;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public class YehanBrotherInstance extends RaidBossInstance
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for YehanBrotherInstance.
	 * @param objectId int
	 * @param template NpcTemplate
	 */
	public YehanBrotherInstance(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}
	
	/**
	 * Method onReduceCurrentHp.
	 * @param damage double
	 * @param attacker Creature
	 * @param skill Skill
	 * @param awake boolean
	 * @param standUp boolean
	 * @param directHp boolean
	 */
	@Override
	protected void onReduceCurrentHp(double damage, Creature attacker, Skill skill, boolean awake, boolean standUp, boolean directHp)
	{
		if ((getBrother().getCurrentHp() > 500) && (damage > getCurrentHp()))
		{
			damage = getCurrentHp() - 1;
		}
		super.onReduceCurrentHp(damage, attacker, skill, awake, standUp, directHp);
	}
	
	/**
	 * Method onDeath.
	 * @param killer Creature
	 */
	@Override
	protected void onDeath(Creature killer)
	{
		super.onDeath(killer);
		if (!getBrother().isDead())
		{
			getBrother().doDie(killer);
		}
	}
	
	/**
	 * Method getBrother.
	 * @return NpcInstance
	 */
	private NpcInstance getBrother()
	{
		int brotherId = 0;
		if (getNpcId() == 25665)
		{
			brotherId = 25666;
		}
		else if (getNpcId() == 25666)
		{
			brotherId = 25665;
		}
		for (NpcInstance npc : getReflection().getNpcs())
		{
			if (npc.getNpcId() == brotherId)
			{
				return npc;
			}
		}
		return null;
	}
}

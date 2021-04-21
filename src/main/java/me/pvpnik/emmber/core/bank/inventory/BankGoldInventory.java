package me.pvpnik.emmber.core.bank.inventory;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BankGoldInventory {

	private BankGoldInventory() {}

	public static final String invTitle = Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.gold");

	public static Inventory getInv() {
		Inventory inv = Bukkit.createInventory(null, 27, invTitle);

		int i;

		for (i = 0; i <= 2; i++)
			inv.setItem(i, BankItems.from(BankItems.credits((int) Math.pow(10, i + 1))));

		for (i = 4; i <= 6; i++)
			inv.setItem(i, BankItems.from(BankItems.crystal((int) Math.pow(10, i - 3))));

		for (i = 18; i <= 20; i++)
			inv.setItem(i, BankItems.to(BankItems.gold((int) (3 * Math.pow(10, i - 18)))));

		for (i = 22; i <= 24; i++)
			inv.setItem(i, BankItems.to(BankItems.gold((int) (2 * Math.pow(10, i - 21)))));

		for (i = 9; i <= 15; i++)
			if (i != 12)
				inv.setItem(i, BankItems.arrow());
		
		//inv.setItem(8, BankItems.help());
		inv.setItem(26, BankItems.exit());
		
		return inv;
	}

}

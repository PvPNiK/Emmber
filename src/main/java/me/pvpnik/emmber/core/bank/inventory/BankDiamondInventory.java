package me.pvpnik.emmber.core.bank.inventory;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class BankDiamondInventory {

	private BankDiamondInventory() {}

	public static final String invTitle = Emmber.getInstance().localFileManager.messages.getMessage("world.bank.conversion.diamond");

	public static Inventory getInv() {
		Inventory inv = Bukkit.createInventory(null, 27, invTitle);

		int i;

		for (i = 2; i <= 4; i++)
			inv.setItem(i, BankItems.from(BankItems.crystal((int) Math.pow(10, i - 1))));

		for (i = 20; i <= 22; i++)
			inv.setItem(i, BankItems.to(BankItems.diamond((int) Math.pow(10, i - 20))));

		for (i = 11; i <= 13; i++)
			inv.setItem(i, BankItems.arrow());

		//inv.setItem(8, BankItems.help());
		inv.setItem(26, BankItems.exit());

		return inv;
	}

}

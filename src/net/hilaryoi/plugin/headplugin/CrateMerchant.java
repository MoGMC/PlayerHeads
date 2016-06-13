package net.hilaryoi.plugin.headplugin;

import net.minecraft.server.v1_10_R1.ChatMessage;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.IMerchant;
import net.minecraft.server.v1_10_R1.ItemStack;
import net.minecraft.server.v1_10_R1.MerchantRecipe;
import net.minecraft.server.v1_10_R1.MerchantRecipeList;

public class CrateMerchant implements IMerchant {

		Crate crate;

		MerchantRecipeList recipes;

		EntityHuman ePlayer;

		// take in the nms itemstack so don't have to convert it twice
		public CrateMerchant(EntityHuman ePlayer, Crate crate, ItemStack keyItem) {

			this.ePlayer = ePlayer;

			this.crate = crate;

			recipes = new MerchantRecipeList();

			MerchantRecipe recipe = new MerchantRecipe(crate.getNmsItem(), keyItem);

			recipes.add(recipe);

		}

		@Override
		public void a(MerchantRecipe arg0) {
			System.out.println("`a_underscroe` method");

		}

		@Override
		public void a(ItemStack arg0) {
			System.out.println("`a` method");

		}

		@Override
		public MerchantRecipeList getOffers(EntityHuman player) {
			return recipes;

		}

		@Override
		public IChatBaseComponent getScoreboardDisplayName() {
			return new ChatMessage("hellowe worl");

		}

		@Override
		public EntityHuman getTrader() {
			System.out.println("get trader");
			return ePlayer;

		}

		@Override
		public void setTradingPlayer(EntityHuman player) {
			System.out.println("set trading player");

		}

}

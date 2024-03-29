package bobe.modulet;

import bobe.modulet.Food.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Modulet implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("modulet");
	public static final Mamaliga mamaliga = new Mamaliga(new FabricItemSettings());
	public static final Block mamaliga_block = new Mamaligablock(FabricBlockSettings.create().strength(1f));
	//TODO:adaugat retete pentru toate itemele :D(blocul de mamaliga se face din 4 mamaliga)
	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier("modulet", "mamaliga"), mamaliga);
		Registry.register(Registries.BLOCK, new Identifier("modulet", "mamaliga_block"), mamaliga_block);
		Registry.register(Registries.ITEM, new Identifier("modulet", "mamaliga_block"), new BlockItem(mamaliga_block, new FabricItemSettings()));

		LOGGER.info("Hello Fabric world!");
	}
}


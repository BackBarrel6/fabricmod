package bobe.modulet;

import bobe.modulet.Food.Mamaliga;
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
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modulet");
	public static final Mamaliga mamaliga = new Mamaliga(new FabricItemSettings());
	public static final Block mamaligablock= new Block(FabricBlockSettings.create().strength(1f));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registries.ITEM, new Identifier("modulet", "mamaliga"), mamaliga);
		Registry.register(Registries.BLOCK, new Identifier("modulet","mamaligablock"), mamaligablock);
		Registry.register(Registries.ITEM, new Identifier("modulet","mamaligablock"), new BlockItem(mamaligablock, new FabricItemSettings()));
		LOGGER.info("Hello Fabric world!");
	}
}

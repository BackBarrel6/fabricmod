package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.item.ItemGroups.COMBAT;

public class TemplateMod implements ModInitializer {
	// Use camelCase for variable names.
	public static final Logger logger = LoggerFactory.getLogger("mod-id");
	public static final Bolt bolt = new Bolt(new FabricItemSettings());
	public static final BowItemPowerful powerfulBow = new BowItemPowerful(new FabricItemSettings());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ItemGroupEvents.modifyEntriesEvent(COMBAT).register(content -> {
					content.add(powerfulBow);
					content.add(bolt);
				}
		);

		logger.info("Hello Fabric world!");
	}
}

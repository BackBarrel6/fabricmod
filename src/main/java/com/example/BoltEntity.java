package com.example;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;

public class BoltEntity extends ArrowEntity
{

    public BoltEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
    }
}
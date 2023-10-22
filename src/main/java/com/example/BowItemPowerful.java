package com.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * A custom bow item that allows for powerful shots with variable damage based on charge time.
 */
public class BowItemPowerful extends BowItem {
    private static final int MAX_CHARGE_TIME = 72000; // 72000 is roughly 3 seconds (20 ticks per second)

    public BowItemPowerful(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_CHARGE_TIME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        boolean isCreative = playerEntity.getAbilities().creativeMode || playerEntity.isSpectator();

        if (playerEntity instanceof ServerPlayerEntity serverPlayer && containsItem(serverPlayer)) {
            int chargeTime = itemStack.getMaxUseTime() - playerEntity.getItemUseTimeLeft();
            float chargePercent = Math.min((float) chargeTime / MAX_CHARGE_TIME, 1.0f);
            int damage = (int) (chargePercent * 3.0f);

            if (chargeTime >= MAX_CHARGE_TIME - 10) {
                PersistentProjectileEntity projectile = createProjectile(world, playerEntity, itemStack, damage);
                if (!world.isClient) {
                    world.spawnEntity(projectile);
                }

                if (!isCreative) {
                    itemStack.damage(1, playerEntity, entity -> entity.sendToolBreakStatus(hand));
                }

                return TypedActionResult.success(itemStack, world.isClient);
            }
        }

        return TypedActionResult.fail(itemStack);
    }

    private boolean containsItem(ServerPlayerEntity player) {
        return player.getInventory().main.stream().anyMatch(stack -> stack.getItem() == TemplateMod.bolt);
    }

    private PersistentProjectileEntity createProjectile(World world, PlayerEntity player, ItemStack itemStack, int damage) {
        ArrowEntity arrow = new ArrowEntity(world, player);
        arrow.initFromStack(itemStack);
        float Pitch=player.getPitch();
        float Yaw=player.getYaw();
        world.spawnEntity(arrow);
        arrow.setVelocity(player, Pitch, Yaw , 0.0F, 20F, 0F);

        if (damage > 0) {
            arrow.setDamage(arrow.getDamage() + damage);
        }

        return arrow;
    }
}

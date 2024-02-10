package bobe.modulet.Food;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mamaligablock extends Block {
	public static final Logger LOGGER = LoggerFactory.getLogger("modulet");
	public static final IntProperty QATE = IntProperty.of("qate", 0, 6); // quantity ate

	public Mamaligablock(AbstractBlock.Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(QATE, 6));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(QATE);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hit) {
		if (!world.isClient()) {
			int newQateValue = state.get(QATE);
			if (newQateValue == 0) {
				world.removeBlock(pos, true);
				player.heal(6F);
			} else {
				world.setBlockState(pos, state.with(QATE, newQateValue - 1));
			}
			if (newQateValue > 1) {
				player.heal(6F);
				LOGGER.info("Used mamaliga");
				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.SUCCESS;
	}
}

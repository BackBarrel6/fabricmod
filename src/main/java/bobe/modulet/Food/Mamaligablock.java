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

public class Mamaligablock extends Block {
public static final IntProperty QATE= IntProperty.of("qate",1,4);//quantity ate
	public Mamaligablock(AbstractBlock.Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(QATE, 4));
	}
protected void appendProperties(StateManager.Builder<Block,BlockState> builder){
		builder.add(QATE);
}
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(state.get(QATE)==0)
		{
			world.removeBlock(pos,true);
			return ActionResult.SUCCESS;
		}
		player.heal(6F);
		world.setBlockState(pos, state.with(QATE,state.get(QATE)-1));
		return ActionResult.SUCCESS;
	}
}

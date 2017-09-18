/*
 * Copyright (c) 2017 Adrian Siekierka
 *
 * This file is part of Unlimited Chisel Works.
 *
 * Unlimited Chisel Works is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Unlimited Chisel Works is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Unlimited Chisel Works.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.asie.ucw;

import com.bioxx.tfc2.TFCBlocks;
import com.bioxx.tfc2.blocks.BlockStoneBrick;
import com.bioxx.tfc2.blocks.terrain.BlockRubble;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import pl.asie.ucw.UCWBlockRule;
import pl.asie.ucw.UCWObjectBroker;
import pl.asie.ucw.UCWUtils;

import java.util.Collection;
import java.util.Iterator;

public class BlockUCWTFC2StoneBrick extends BlockStoneBrick implements IUCWBlock {
	private UCWBlockRule rule = UCWObjectBroker.get().getRule();
	private IBlockState base = UCWObjectBroker.get().getBase();

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return UCWUtils.applyProperties(this, this.rule.throughBlock.getStateFromMeta(meta));
	}

	@Override
	public IBlockState getFallBlockType(IBlockState myState) {
		return super.getFallBlockType(getBaseState());
	}

	@Override
	public void createFallingEntity(World world, BlockPos pos, IBlockState state) {
		super.createFallingEntity(world, pos, getBaseState());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return this.rule.throughBlock.getMetaFromState(UCWUtils.applyProperties(this.rule.throughBlock, state));
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList items) {
		Item.getItemFromBlock(this).getSubItems(Item.getItemFromBlock(this), tab, items);
	}

	protected BlockStateContainer createBlockState() {
		this.rule = UCWObjectBroker.get().getRule();
		return UCWObjectBroker.get().createBlockState(this);
	}

	@Override
	public IBlockState getBaseState() {
		return base;
	}
}
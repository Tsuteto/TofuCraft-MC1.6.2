package tsuteto.tofu.village;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class TofuCookHouseHandler implements IVillageCreationHandler
{

    @Override
    public StructureVillagePieceWeight getVillagePieceWeight(Random random, int i)
    {
        return new StructureVillagePieceWeight(ComponentVillageHouseTofu.class, 20, MathHelper.getRandomIntegerInRange(random, 0 + i, 1 + i));
    }

    @Override
    public Class<?> getComponentClass()
    {
        return ComponentVillageHouseTofu.class;
    }

    @Override
    public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece,
            List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, -4, 0, 7, 6, 7, p4);
        return StructureComponent.findIntersecting(pieces, structureboundingbox) != null ? null : new ComponentVillageHouseTofu(startPiece, p5, random, structureboundingbox, p4);
    }
}

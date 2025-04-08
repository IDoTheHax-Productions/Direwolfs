package net.idothehax.direwolfs.client;

import net.idothehax.direwolfs.entity.DirewolfEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;

public class DirewolfRenderer extends WolfEntityRenderer {
    public DirewolfRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected void scale(WolfEntity entity, MatrixStack matrices, float amount) {
        // Apply scaling to make the Direwolf larger
        matrices.scale(2.0F, 2.0F, 2.0F); // Scale in X, Y, and Z directions
        super.scale(entity, matrices, amount);
    }

}

package net.idothehax.direwolfs.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.idothehax.direwolfs.Direwolfs;
import net.minecraft.client.render.entity.WolfEntityRenderer;

public class DirewolfsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Direwolfs.DIREWOLF, (context) -> new DirewolfRenderer(context));
    }
}

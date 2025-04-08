package net.idothehax.direwolfs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DirewolfEntity extends WolfEntity {
    public DirewolfEntity(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.add(1, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // Ride the big ass dog
        if (!this.getWorld().isClient && !player.hasVehicle()) {
            player.startRiding(this);
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void tick() {
        super.tick();

        // Check if there is a rider
        if (this.hasPassengers() && this.getFirstPassenger() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) this.getFirstPassenger();

            // Handle forward/backward movement
            float forward = player.forwardSpeed;   // W/S keys
            float sideways = player.sidewaysSpeed; // A/D keys

            // Apply movement based on player's input
            Vec3d direction = this.getRotationVector().multiply(forward * 0.5); // Adjust speed multiplier as needed
            this.setVelocity(direction.x, this.getVelocity().y, direction.z);

            // Add strafing (sideways movement)
            if (sideways != 0) {
                Vec3d strafeVector = new Vec3d(
                        -Math.sin(Math.toRadians(this.getYaw())) * sideways,
                        0,
                        Math.cos(Math.toRadians(this.getYaw())) * sideways
                );
                this.addVelocity(strafeVector.x * 0.2, 0, strafeVector.z * 0.2); // Adjust strafing speed as needed
            }

            // Rotate the Direwolf with the player's view
            this.setYaw(player.getYaw());
        }
    }


    public static DefaultAttributeContainer.Builder createDirewolfAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.5);

    }
}
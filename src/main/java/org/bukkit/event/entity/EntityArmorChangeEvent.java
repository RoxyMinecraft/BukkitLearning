package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an entity equips, removes, switches or breaks armor, or when an
 * entity is spawned. For example:
 * <ul>
 * <li>A player equips a chestplate by right clicking</li>
 * <li>An entity is naturally spawned (an event called for each piece of
 * armor)</li>
 * <li>A zombie's helmet is broken due to sunlight</li>
 * </ul>
 *
 * Note that this does NOT get called for things such as
 * {@link PlayerDeathEvent}, where the entity loses armor due to dying.
 */
public class EntityArmorChangeEvent extends EntityEvent {

    private static final HandlerList handlers = new HandlerList();
    //
    private final ItemStack oldArmor;
    private final ItemStack newArmor;
    private final EquipmentSlot equipmentSlot;
    private final ChangeReason changeReason;

    public EntityArmorChangeEvent(@NotNull LivingEntity entity, @NotNull ItemStack oldArmor, @NotNull ItemStack newArmor, @NotNull EquipmentSlot equipmentSlot, @NotNull ChangeReason changeReason) {
        super(entity);
        this.oldArmor = oldArmor;
        this.newArmor = newArmor;
        this.equipmentSlot = equipmentSlot;
        this.changeReason = changeReason;
    }

    /**
     * Gets the itemstack that was the old armor equipped.
     *
     * The material will be AIR if there wasn't an old armor piece
     *
     * @return The old armor that was equipped
     */
    @NotNull
    public ItemStack getOldArmor() {
        return oldArmor.clone();
    }

    /**
     * Gets the itemstack that is the new armor equipped.
     *
     * The material will be AIR if there wasn't a new armor piece
     *
     * @return The new armor that is equipped
     */
    @NotNull
    public ItemStack getNewArmor() {
        return newArmor.clone();
    }

    /**
     * Gets the slot that had armor modified.
     *
     * @return The EquipmentSlot that was modified by this event
     */
    @NotNull
    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    /**
     * Gets the reason this event was fired.
     *
     * @return An enum value representing the cause of the event
     * @see ChangeReason
     */
    @NotNull
    public ChangeReason getChangeReason() {
        return changeReason;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Represents the various causes of the event.
     */
    public enum ChangeReason {

        /**
         * Represented the event being called due to armor breaking.
         */
        BREAK,
        /**
         * Represented the event being called due to armor being equipped.
         */
        EQUIP,
        /**
         * Represented the event being called due to two armor pieces being
         * swapped.
         */
        SWITCH,
        /**
         * Represented the event being called due to armor being removed.
         */
        UNEQUIP
    }
}

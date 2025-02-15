package net.berserker_rpg.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_engine.api.effect.*;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
import static net.berserker_rpg.BerserkerClassMod.effectsConfig;

public class Effects {
    //RAGE
    public static StatusEffect RAGE = new RageStatusEffect(StatusEffectCategory.BENEFICIAL, 0xf70000);
    //SOUL DEVOURER
    public static StatusEffect SOUL_DEVOURER = new SoulDevourerEffect(StatusEffectCategory.BENEFICIAL, 0x01d9cf);

    public static void register(){
        RAGE.addAttributeModifier(MRPGCEntityAttributes.RAGE_MODIFIER, "4ff7e39a-22d1-4b65-b87a-815883237180",
                        effectsConfig.value.rage_rage_attribute_increase_per_stack, EntityAttributeModifier.Operation.MULTIPLY_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "3098b421-2316-4b40-9fcf-71c84fd85fc3",
                        effectsConfig.value.rage_attack_speed_increase_per_stack, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        Synchronized.configure(RAGE,true);
        Synchronized.configure(SOUL_DEVOURER,true);

        HealthImpacting.configureDamageTaken(RAGE,effectsConfig.value.rage_increased_incoming_damage_per_stack);

        int berserker_effect_id = 100;
        Registry.register(Registries.STATUS_EFFECT, berserker_effect_id++, new Identifier(MOD_ID, "rage").toString(), RAGE);
        Registry.register(Registries.STATUS_EFFECT, berserker_effect_id++, new Identifier(MOD_ID, "soul_devourer").toString(), SOUL_DEVOURER);
    }
}

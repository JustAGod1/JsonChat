package ru.justagod.jchat.example;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import ru.justagod.jchat.JsonMessageBuilder;
import ru.justagod.jchat.PartColor;

/**
 * Created by JustAGod on 17.03.2018.
 */
@SuppressWarnings("unused")
@Mod(modid = "json", name = "JSON Mod", version = "0.1")
public class JsonMod {

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEnter(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
            ItemStack stack = new ItemStack(Blocks.coal_ore);
            stack.addEnchantment(Enchantment.flame, 5);
            stack.setStackDisplayName("Супер уголь");
            ((EntityPlayer) event.entity).addChatComponentMessage(
                    new JsonMessageBuilder()
                            .newPart()
                            .setText("Hello, ")
                            .setBold(true)
                            .end()
                            .newPart()
                            .setHoverStack(stack)
                            .setColor(PartColor.DARK_AQUA)
                            .setText("do you want some coal?")
                            .end()

                            .build()
                            .serialize());
        }
    }
}

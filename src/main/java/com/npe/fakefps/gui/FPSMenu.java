package com.npe.fakefps.gui;

import com.npe.fakefps.Mod;
import com.npe.fakefps.gson.ConfigLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;

public class FPSMenu extends Screen {
    private Screen prev;
    public FPSMenu(Screen prev) {
        super(new TranslatableText("fakefps.fpsmenu"));
        this.prev = prev;
    }

    ButtonWidget done;
    ButtonWidget fpslock;

    @Override
    public void init() {
        done = new ButtonWidget((width / 2) - (100 / 2), ((height / 2) + 70) - (20 / 2), 100, 20, "Done", w -> {
            ConfigLoader.save(Mod.INSTANCE.config);
            MinecraftClient.getInstance().openScreen(prev);
        });
        fpslock = new ButtonWidget(
                (width / 2) - (100 / 2),
                ((height / 2) - font.fontHeight - 4 - 20) - (20 / 2),
                100, 20,
                "FPS Lock: " + (Mod.INSTANCE.config.isFPSLocked() ? "ON" : "OFF"),w ->
        {
            Mod.INSTANCE.config.setLockfps(!Mod.INSTANCE.config.isFPSLocked());
            fpslock.setMessage("FPS Lock: " + (Mod.INSTANCE.config.isFPSLocked() ? "ON" : "OFF"));
        });
        addButton(done);
        addButton(fpslock);
    }

    @Override
    public void render(int mouseX, int mouseY, float particalTicks) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.asFormattedString(), this.width / 2, 40, 16777215);
        //font.drawWithShadow("FPS Menu", (width / 2) - (font.getStringWidth("FPS Menu") / 2), ((height / 2) - 70) - (font.fontHeight / 2), 0xffffff);
        done.render(mouseX, mouseY, particalTicks);
        fpslock.render(mouseX, mouseY, particalTicks);
        super.render(mouseX, mouseY, particalTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}

package com.npe.fakefps.gui;

import com.mojang.datafixers.types.templates.Check;
import com.npe.fakefps.Mod;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.TranslatableText;

public class FPSMenu extends Screen {
    public FPSMenu() {
        super(new TranslatableText("fakefps.fpsmenu"));
    }

    CheckboxWidget lock;

    @Override
    public void init() {
        lock = new CheckboxWidget(1000, 100, 100, 100, "Heelo", Mod.INSTANCE.config.isFPSLocked());
        this.addButton(lock);
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        this.renderBackground();
        TextRenderer r = this.minecraft.textRenderer;
        r.drawWithShadow("FPS Menu", (this.width / 2) - (r.getStringWidth("FPS Menu") / 2), (this.height / 4) - (r.fontHeight / 2), 0xffffffff);
        lock.render(int_1, int_2, float_1);
        super.render(int_1, int_2, float_1);
    }

    @Override
    public boolean charTyped(char char_1, int int_1) {
        lock.charTyped(char_1, int_1);
        return false;
    }
}

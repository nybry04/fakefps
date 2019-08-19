package com.npe.fakefps.gui;

import com.npe.fakefps.Mod;
import com.npe.fakefps.gson.ConfigLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.TranslatableText;

public class FPSMenu extends Screen {
    private Screen prev;
    private boolean locked;
    public FPSMenu(Screen prev, boolean locked) {
        super(new TranslatableText("fakefps.fpsmenu"));
        this.prev = prev;
        this.locked = locked;
    }

    ButtonWidget done;
    ButtonWidget fpslock;

    TextFieldWidget targetfps;

    TextFieldWidget minfps;
    TextFieldWidget maxfps;

    @Override
    public void init() {
        done = new ButtonWidget((width / 2) - (100 / 2), ((height / 2) + 90) - (20 / 2), 100, 20, "Done", w -> {
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
            MinecraftClient.getInstance().openScreen(new FPSMenu(prev, Mod.INSTANCE.config.isFPSLocked()));
        });
        addButton(done);
        addButton(fpslock);
        if(locked){
            targetfps = new TextFieldWidget(
                    font,
                    width / 2 - 100 / 2,
                    height / 2 + 7,
                    100, 20, String.valueOf(Mod.INSTANCE.config.getTarget())
            );
            targetfps.setText(String.valueOf(Mod.INSTANCE.config.getTarget()));
            addButton(targetfps);
        }else{
            minfps = new TextFieldWidget(
                    font,
                    width / 2 - 100 / 2,
                    height / 2 + 7,
                    100, 20, String.valueOf(Mod.INSTANCE.config.getMin())
            );
            minfps.setText(String.valueOf(Mod.INSTANCE.config.getMin()));
            addButton(minfps);

            maxfps = new TextFieldWidget(
                    font,
                    width / 2 - 100 / 2,
                    height / 2 + 7 + 20 + 4 + 20,
                    100, 20, String.valueOf(Mod.INSTANCE.config.getMax())
            );
            maxfps.setText(String.valueOf(Mod.INSTANCE.config.getMax()));
            addButton(maxfps);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float particalTicks) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.asFormattedString(), this.width / 2, 40, 0xffffff);
        done.render(mouseX, mouseY, particalTicks);
        fpslock.render(mouseX, mouseY, particalTicks);
        if(locked){
            font.drawWithShadow("FPS",
                    width / 2 - font.getStringWidth("FPS") / 2,
                    targetfps.y - 12,
                    0xffffff);
            targetfps.render(mouseX, mouseY, particalTicks);
        }else{
            font.drawWithShadow("Min FPS",
                    width / 2 - font.getStringWidth("Min FPS") / 2,
                    minfps.y - 12,
                    0xffffff);
            minfps.render(mouseX, mouseY, particalTicks);

            font.drawWithShadow("Max FPS",
                    width / 2 - font.getStringWidth("Max FPS") / 2,
                    maxfps.y - 12,
                    0xffffff);
            maxfps.render(mouseX, mouseY, particalTicks);
        }
        super.render(mouseX, mouseY, particalTicks);
    }

    @Override
    public boolean charTyped(char char_1, int int_1) {
        if(char_1 == '1' || char_1 == '2' || char_1 == '3' || char_1 == '4' || char_1 == '5' || char_1 == '6'
                || char_1 == '7' || char_1 == '8' || char_1 == '9' || char_1 == '0'){
            if(locked){
                String pre = targetfps.getText();
                targetfps.charTyped(char_1, int_1);
                try{
                    Mod.INSTANCE.config.setTarget(Integer.parseInt(targetfps.getText()));
                }catch (Exception e){
                    targetfps.setText(pre);
                }
            }else{
                String premin = minfps.getText();
                String premax = maxfps.getText();
                if(minfps.isFocused()){
                    minfps.charTyped(char_1, int_1);
                }else{
                    maxfps.charTyped(char_1, int_1);
                }
                try{
                    int cminfps = Integer.parseInt(minfps.getText());
                    int cmaxfps = Integer.parseInt(maxfps.getText());
                    if(cminfps > cmaxfps){
                        throw new Exception();
                    }else{
                        Mod.INSTANCE.config.setMin(cminfps);
                        Mod.INSTANCE.config.setMax(cmaxfps);
                    }
                }catch (Exception e){
                    minfps.setText(premin);
                    maxfps.setText(premax);
                }

            }

        }
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}

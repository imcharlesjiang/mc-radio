//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import net.minecraft.client.gui.Radio;
import java.io.IOException;

public class bla extends blk {
    private final String a;
    private final String f;

    public bla(String var1, String var2) {
        this.a = var1;
        this.f = var2;
    }

    public void b() {
        super.b();
        this.n.add(new bja(0, this.l / 2 - 100, 140, cey.a("gui.cancel", new Object[0])));
    }

    public void a(int var1, int var2, float var3) {
        this.a(0, 0, this.l, this.m, -12574688, -11530224);
        this.a(this.q, this.a, this.l / 2, 90, 16777215);
        this.a(this.q, this.f, this.l / 2, 110, 16777215);
        super.a(var1, var2, var3);
    }

    protected void a(char var1, int var2) {
    }

    protected void a(bja var1) {
        this.j.a((blk)null);
        Radio.disable();
    }
}

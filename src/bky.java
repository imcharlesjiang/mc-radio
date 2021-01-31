//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import net.minecraft.client.gui.Radio;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class bky extends blk {
    private final String a;
    private final hh f;
    private List<String> g;
    private final blk h;
    private int i;
    private static final String __OBFID = "CL_00000693";
    public bky(blk var1, String var2, hh var3) {
        this.h = var1;
        this.a = cey.a(var2, new Object[0]);
        this.f = var3;
    }

    protected void a(char var1, int var2) {
    }

    public void b() {
        this.n.clear();
        this.g = this.q.c(this.f.d(), this.l - 50);
        this.i = this.g.size() * this.q.a;
        this.n.add(new bja(0, this.l / 2 - 100, Math.min(this.m / 2 + this.i / 2 + this.q.a, this.m - 30), cey.a("gui.toMenu", new Object[0])));
    }

    protected void a(bja var1) {
        if (var1.k == 0) {
            this.j.a(this.h);
            Radio.disable();
        }

    }

    public void a(int var1, int var2, float var3) {
        this.c();
        this.a(this.q, this.a, this.l / 2, this.m / 2 - this.i / 2 - this.q.a * 2, 11184810);
        int var4 = this.m / 2 - this.i / 2;
        if (this.g != null) {
            for(Iterator var5 = this.g.iterator(); var5.hasNext(); var4 += this.q.a) {
                String var6 = (String)var5.next();
                this.a(this.q, var6, this.l / 2, var4, 16777215);
            }
        }

        super.a(var1, var2, var3);
    }
}

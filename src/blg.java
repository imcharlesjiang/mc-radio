//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import net.minecraft.client.gui.Radio;
import net.minecraft.realms.RealmsBridge;

public class blg extends blk {
    private int a;
    private int f;

    public blg() {
    }

    public void b() {
        this.a = 0;
        this.n.clear();
        boolean var1 = true;
        boolean var2 = true;
        this.n.add(new bja(1, this.l / 2 - 100, this.m / 4 + 120 + -16, cey.a("menu.returnToMenu", new Object[0])));
        if (!this.j.D()) {
            ((bja)this.n.get(0)).j = cey.a("menu.disconnect", new Object[0]);
        }

        this.n.add(new bja(4, this.l / 2 - 100, this.m / 4 + 24 + -16, cey.a("menu.returnToGame", new Object[0])));
        this.n.add(new bja(0, this.l / 2 - 100, this.m / 4 + 96 + -16, 98, 20, cey.a("menu.options", new Object[0])));
        bja var3 = this.b(new bja(7, this.l / 2 + 2, this.m / 4 + 96 + -16, 98, 20, cey.a("menu.shareToLan", new Object[0])));
        var3.l = this.j.E() && !this.j.F().a();
        this.n.add(new bja(5, this.l / 2 - 100, this.m / 4 + 48 + -16, 98, 20, cey.a("gui.advancements", new Object[0])));
        this.n.add(new bja(6, this.l / 2 + 2, this.m / 4 + 48 + -16, 98, 20, cey.a("gui.stats", new Object[0])));
        this.n.add(new bja(8, this.l / 2 + 104, this.m / 4 + 48 + -16, 98, 20, "星空电台"));
    }

    protected void a(bja var1) {
        switch(var1.k) {
            case 0:
                this.j.a(new ble(this, this.j.t));
                break;
            case 1:
                boolean var2 = this.j.D();
                boolean var3 = this.j.ah();
                var1.l = false;
                this.j.f.O();
                this.j.a((bsb)null);
                if (var2) {
                    this.j.a(new blr());
                } else if (var3) {
                    RealmsBridge var4 = new RealmsBridge();
                    var4.switchToRealms(new blr());
                } else {
                    this.j.a(new bnf(new blr()));
                }
            case 2:
            case 3:
            default:
                break;
            case 4:
                this.j.a((blk)null);
                this.j.o();
                break;
            case 5:
                this.j.a(new bmb(this.j.h.d.f()));
                break;
            case 6:
                this.j.a(new blu(this, this.j.h.D()));
                break;
            case 7:
                this.j.a(new bll(this));
            case 8:
                Radio.toggle();
        }

    }

    public void e() {
        super.e();
        ++this.f;
    }

    public void a(int var1, int var2, float var3) {
        this.c();
        this.a(this.q, cey.a("menu.game", new Object[0]), this.l / 2, 40, 16777215);
        super.a(var1, var2, var3);
    }
}

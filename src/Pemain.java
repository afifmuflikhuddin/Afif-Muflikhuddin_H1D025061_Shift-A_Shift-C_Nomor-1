public class Pemain extends Karakter implements BisaBertarung, BisaMenggunakanItem {
    public static final int MAX_STAMINA = 50;
    public static final int EXP_LEVEL_UP = 100;
    private int level;
    private int exp;
    private int gold;
    private int stamina;
    public Pemain(String id, String nama) {
        this(id, nama, 100, 20);
    }
    public Pemain(String id, String nama, int hp, int attack) {
        super(id, nama, hp, attack);
        this.level = 1;
        this.exp = 0;
        this.gold = 50;
        this.stamina = MAX_STAMINA;
    }
    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }
    public void setLevel(int level) {
        this.level = Math.max(1, level);
    }
    public void setExp(int exp) {
        this.exp = Math.max(0, exp);
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = Math.max(0, gold);
    }
    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        if (stamina < 0) {
            this.stamina = 0;
        } else if (stamina > MAX_STAMINA) {
            this.stamina = MAX_STAMINA;
        } else {
            this.stamina = stamina;
        }
    }
    @Override
    public void bertarung(Karakter target) {
        serang(target);
    }
    @Override
    public boolean gunakanItem(Item item) {
        if (item != null && isHidup()) {
            return item.gunakan(this);
        } else {
            System.out.println("Item tidak dapat digunakan!");
            return false;
        }
    }
    @Override
    public void serang(Karakter target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + "!");
        super.serang(target);
        System.out.println("Damage: " + getAttack());
    }
    @Override
    public void aksiKhusus(Karakter target) {
        if (stamina >= 20 && isHidup() && target.isHidup()) {
            stamina -= 20;
            int damage = getAttack() + 10;
            System.out.println(getNama() + " menggunakan serangan spesial!");
            target.terimaDamage(damage);
            System.out.println("Damage spesial: " + damage);
        } else {
            System.out.println("Stamina tidak cukup!");
            setStamina(stamina + 10);
            System.out.println("Stamina dipulihkan 10 poin.");
        }
    }
    public void dapatHadiah(int exp, int gold) {
        this.exp += exp;
        this.gold += gold;
        System.out.println("Mendapatkan " + exp + " EXP");
        System.out.println("Mendapatkan " + gold + " Gold");
        while (this.exp >= EXP_LEVEL_UP) {
            this.exp -= EXP_LEVEL_UP;
            level++;
            setAttack(getAttack() + 5);
            setHp(MAX_HP);
            System.out.println("LEVEL UP!");
            System.out.println("Level sekarang: " + level);
        }
    }
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Level   : " + level);
        System.out.println("EXP     : " + exp);
        System.out.println("Gold    : " + gold);
        System.out.println("Stamina : " + stamina);
    }
}
public abstract class Karakter {
    public static final int MAX_HP = 100;
    private final String idKarakter;
    private String nama;
    private int hp;
    private int attack;
    public Karakter(String idKarakter, String nama, int hp) {
        this(idKarakter, nama, hp, 10);
    }
    public Karakter(String idKarakter, String nama, int hp, int attack) {
        this.idKarakter = idKarakter;
        this.nama = nama;
        setHp(hp);
        setAttack(attack);
    }
    public String getIdKarakter() {
        return idKarakter;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else if (hp > MAX_HP) {
            this.hp = MAX_HP;
        } else {
            this.hp = hp;
        }
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = Math.max(0, attack);
    }
    public boolean isHidup() {
        return hp > 0;
    }
    public void serang(Karakter target) {
        if (isHidup() && target.isHidup()) {
            target.terimaDamage(attack);
        }
    }
    public void terimaDamage(int damage) {
        setHp(hp - damage);
    }
    public void pulihkanHp(int jumlah) {
        setHp(hp + jumlah);
    }
    public void tampilkanInfo() {
        System.out.println("ID     : " + idKarakter);
        System.out.println("Nama   : " + nama);
        System.out.println("HP     : " + hp);
        System.out.println("Attack : " + attack);
    }
    public abstract void aksiKhusus(Karakter target);
}
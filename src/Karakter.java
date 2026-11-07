public abstract class Karakter {

    // Konstanta global
    public static final int MAX_HP = 100;

    // Atribut
    private final String idKarakter;
    private String nama;
    private int hp;
    private int attack;

    // Constructor pertama
    public Karakter(String idKarakter, String nama, int hp) {
        this(idKarakter, nama, hp, 10);
    }

    // Constructor kedua (overloading)
    public Karakter(String idKarakter, String nama, int hp, int attack) {
        this.idKarakter = idKarakter;
        this.nama = nama;
        setHp(hp);
        setAttack(attack);
    }

    // Getter dan setter
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

    // Mengecek kondisi hidup
    public boolean isHidup() {
        return hp > 0;
    }

    // Method untuk menyerang
    public void serang(Karakter target) {
        if (isHidup() && target.isHidup()) {
            target.terimaDamage(attack);
        }
    }

    // Method menerima damage
    public void terimaDamage(int damage) {
        setHp(hp - damage);
    }

    // Method memulihkan HP
    public void pulihkanHp(int jumlah) {
        setHp(hp + jumlah);
    }

    // Menampilkan informasi karakter
    public void tampilkanInfo() {
        System.out.println("ID     : " + idKarakter);
        System.out.println("Nama   : " + nama);
        System.out.println("HP     : " + hp);
        System.out.println("Attack : " + attack);
    }

    // Abstract method
    public abstract void aksiKhusus(Karakter target);
}
public class Monster extends Karakter implements BisaBertarung {
    private String jenisMonster;
    private boolean mengamuk;
    public Monster(String id, String nama, String jenis, int hp, int attack) {
        super(id, nama, hp, attack);
        this.jenisMonster = jenis;
        this.mengamuk = false;
    }

    // Constructor kedua (overloading): attack memakai nilai bawaan Karakter
    public Monster(String id, String nama, String jenis, int hp) {
        super(id, nama, hp);
        this.jenisMonster = jenis;
        this.mengamuk = false;
    }

    // Getter dan setter
    public String getJenisMonster() {
        return jenisMonster;
    }
    public void setJenisMonster(String jenisMonster) {
        this.jenisMonster = jenisMonster;
    }
    public boolean isMengamuk() {
        return mengamuk;
    }
    public void setMengamuk(boolean mengamuk) {
        this.mengamuk = mengamuk;
    }

    // Implementasi interface BisaBertarung
    @Override
    public void bertarung(Karakter target) {
        serang(target);
    }

    // Overriding method serang
    @Override
    public void serang(Karakter target) {
        System.out.println(getNama()
                + " menyerang " + target.getNama() + "!");
        super.serang(target);
        System.out.println("Damage: " + getAttack());
    }

    // Implementasi abstract method
    @Override
    public void aksiKhusus(Karakter target) {
        if (getHp() <= 30 && !mengamuk && isHidup()) {
            mengamuk = true;
            setAttack(getAttack() + 5);
            System.out.println(getNama() + " memasuki MODE MENGAMUK!");
            System.out.println("Attack monster meningkat!");
        } else if (isHidup()) {
            System.out.println(getNama() + " melakukan serangan biasa.");
        }
        if (isHidup() && target.isHidup()) {
            serang(target);
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis Monster : " + jenisMonster);
        System.out.println("Mengamuk      : " + mengamuk);
    }
}
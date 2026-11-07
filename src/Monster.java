public class Monster extends Karakter implements BisaBertarung {
    private String jenisMonster;
    private boolean mengamuk;
    public Monster(String id, String nama, String jenis, int hp, int attack) {
        super(id, nama, hp, attack);
        this.jenisMonster = jenis;
        this.mengamuk = false;
    }
    public Monster(String id, String nama, String jenis, int hp) {
        super(id, nama, hp);
        this.jenisMonster = jenis;
        this.mengamuk = false;
    }
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
    @Override
    public void bertarung(Karakter target) {
        serang(target);
    }
    @Override
    public void serang(Karakter target) {
        System.out.println(getNama()
                + " menyerang " + target.getNama() + "!");
        super.serang(target);
        System.out.println("Damage: " + getAttack());
    }
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
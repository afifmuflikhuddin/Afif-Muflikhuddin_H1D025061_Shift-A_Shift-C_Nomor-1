public class Boss extends Monster {
    private int damageApi;
    private int giliran;

    public Boss(String id, String nama, String jenis, int hp, int attack) {
        super(id, nama, jenis, hp, attack);
        this.damageApi = 15;
        this.giliran = 0;
    }

    // Getter dan setter
    public int getDamageApi() {
        return damageApi;
    }
    public void setDamageApi(int damageApi) {
        this.damageApi = Math.max(0, damageApi);
    }
    public int getGiliran() {
        return giliran;
    }
    public void setGiliran(int giliran) {
        this.giliran = Math.max(0, giliran);
    }

    // Overriding aksiKhusus: perilaku Monster + semburan api setiap 3 giliran
    @Override
    public void aksiKhusus(Karakter target) {
        giliran++;
        super.aksiKhusus(target);
        if (giliran % 3 == 0 && isHidup() && target.isHidup()) {
            System.out.println(getNama() + " menyemburkan api!");
            target.terimaDamage(damageApi);
            System.out.println("Damage api: " + damageApi);
        }
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Damage Api    : " + damageApi);
    }
}
public class Item {
    private String namaItem;
    private int pemulihan;
    private int jumlah;
    public Item(String namaItem, int pemulihan, int jumlah) {
        this.namaItem = namaItem;
        this.pemulihan = pemulihan;
        setJumlah(jumlah);
    }
    public String getNamaItem() {
        return namaItem;
    }
    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
    public int getPemulihan() {
        return pemulihan;
    }
    public void setPemulihan(int pemulihan) {
        this.pemulihan = Math.max(0, pemulihan);
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = Math.max(0, jumlah);
    }
    public boolean gunakan(Pemain pemain) {
        if (jumlah <= 0) {
            System.out.println(namaItem + " sudah habis!");
            return false;
        } else if (pemain.getHp() < Karakter.MAX_HP) {
            System.out.println("Menggunakan " + namaItem);
            jumlah--;
            pemain.pulihkanHp(pemulihan);
            System.out.println("HP berhasil dipulihkan!");
            System.out.println("HP sekarang: " + pemain.getHp());
            System.out.println("Sisa " + namaItem + ": " + jumlah);
            return true;
        } else {
            System.out.println("HP masih penuh!");
            return false;
        }
    }
}
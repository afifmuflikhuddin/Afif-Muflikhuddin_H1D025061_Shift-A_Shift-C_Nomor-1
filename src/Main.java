import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Membuat objek pemain
        System.out.println("================================");
        System.out.println("       PERBURUAN MONSTER");
        System.out.println("================================");
        System.out.print("Masukkan nama pemain: ");
        String nama = input.nextLine();
        Pemain pemain = new Pemain("P001", nama);

        // Membuat daftar monster (memakai dua konstruktor Monster yang berbeda)
        Monster[] daftarMonster = {
                new Monster("M001", "Goblin", "Monster Hutan", 80, 15),
                new Monster("M002", "Slime", "Monster Lendir", 60),
                new Boss("M003", "Naga Api", "Boss Gua", 100, 18)
        };

        // Membuat item
        Item potion = new Item("Health Potion", 30, 3);

        // Polimorfisme melalui interface
        BisaBertarung petarung = pemain;
        System.out.println("\nSelamat datang, " + pemain.getNama() + "!");
        System.out.println("\n===== STATUS PEMAIN =====");
        pemain.tampilkanInfo();
        boolean bermain = true;

        // Perulangan monster (satu per satu)
        for (int i = 0; i < daftarMonster.length
                && bermain && pemain.isHidup(); i++) {

            Monster monster = daftarMonster[i];
            System.out.println("\n===== MONSTER " + (i + 1) + " MUNCUL =====");
            monster.tampilkanInfo();

            // Serangan pembuka: Pemain dan Monster sama-sama BisaBertarung
            // (polimorfisme interface melalui array)
            BisaBertarung[] peserta = { pemain, monster };
            Karakter[] lawan = { monster, pemain };
            System.out.println("\n--- SERANGAN PEMBUKA ---");
            for (int k = 0; k < peserta.length; k++) {
                peserta[k].bertarung(lawan[k]);
            }

            // Perulangan pertarungan
            while (bermain && pemain.isHidup() && monster.isHidup()) {
                System.out.println("\n================================");
                System.out.println("             MENU");
                System.out.println("================================");
                System.out.println("1. Serang Monster");
                System.out.println("2. Serangan Spesial");
                System.out.println("3. Gunakan Potion (sisa: " + potion.getJumlah() + ")");
                System.out.println("4. Lihat Status");
                System.out.println("5. Kabur");
                System.out.println("================================");
                System.out.print("Pilih aksi: ");
                int pilihan;
                try {
                    pilihan = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Masukkan angka yang benar!");
                    continue;
                }
                boolean giliranMonster = false;
                switch (pilihan) {
                    case 1:
                        petarung.bertarung(monster);
                        giliranMonster = true;
                        break;
                    case 2:
                        pemain.aksiKhusus(monster);
                        giliranMonster = true;
                        break;
                    case 3:
                        // Giliran hanya terpakai jika potion benar-benar dipakai
                        giliranMonster = pemain.gunakanItem(potion);
                        break;
                    case 4:
                        System.out.println("\n===== STATUS PEMAIN =====");
                        pemain.tampilkanInfo();
                        System.out.println("\n===== STATUS MONSTER =====");
                        monster.tampilkanInfo();
                        break;
                    case 5:
                        System.out.println("Kamu berhasil kabur!");
                        bermain = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia!");
                        break;
                }
                // Giliran monster
                if (giliranMonster && monster.isHidup() && pemain.isHidup()) {
                    System.out.println("\n--- GILIRAN MONSTER ---");
                    monster.aksiKhusus(pemain);
                    System.out.println("HP Pemain: " + pemain.getHp());
                }
                // Kondisi monster dikalahkan
                if (!monster.isHidup()) {
                    System.out.println("\n================================");
                    System.out.println("   " + monster.getNama().toUpperCase() + " DIKALAHKAN!");
                    System.out.println("================================");
                    pemain.dapatHadiah(60, 30);
                    if (i == daftarMonster.length - 1) {
                        System.out.println("\nSEMUA MONSTER DIKALAHKAN!");
                        bermain = false;
                    }
                }
                // Kondisi kekalahan
                if (!pemain.isHidup()) {
                    System.out.println("\n================================");
                    System.out.println("       GAME OVER!");
                    System.out.println("================================");
                    System.out.println("Pemain telah dikalahkan.");
                    bermain = false;
                }
            }
        }
        System.out.println("\n================================");
        System.out.println("       PERMAINAN SELESAI");
        System.out.println("================================");
        input.close();
    }
}
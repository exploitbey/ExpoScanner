package src;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          ExpoScanner            #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println();

        System.out.print("Hedef IP veya site: ");
        String hedef = scan.nextLine();

        System.out.print("Baslangic portu (örn 1): ");
        int baslangic = 1;
        try {
            baslangic = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {}

        System.out.print("Bitis portu (örn 1000): ");
        int bitis = 1000;
        try {
            bitis = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {}

        // Thread sayisini kullaniciya secelim
        System.out.print("Thread (Hiz - Önerilen 50-100 arasi): ");
        int threadSayisi = 50;
        try {
            threadSayisi = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {}

        System.out.println("\n>> Tarama baslatiliyor: " + hedef);
        System.out.println(">> Lutfen bekleyin, sonuclar hazirlaniyor...\n");

        ArrayList<Integer> acikPortlar = new ArrayList<>();
        ExecutorService havuz = Executors.newFixedThreadPool(threadSayisi); 

        long sureBaslangic = System.currentTimeMillis();

        for (int i = baslangic; i <= bitis; i++) {
            final int portNumarasi = i;
            havuz.submit(() -> {
                try {
                    Socket soket = new Socket();
                    // Baglanti 2000 ms icinde olmazsa zaman asimi at
                    soket.connect(new InetSocketAddress(hedef, portNumarasi), 2000); 
                    soket.close();
                    
                    System.out.println("[+] Acik port -> " + portNumarasi);
                    synchronized(acikPortlar) {
                        acikPortlar.add(portNumarasi);
                    }
                } catch (Exception e) {
                    // kapaliysa veya timeout yediyse buralara duser bir sey yapmaya gerek yok
                }
            });
        }

        havuz.shutdown();
        try {
            havuz.awaitTermination(10, TimeUnit.MINUTES);
        } catch (Exception e) {}

        long sureBitis = System.currentTimeMillis();
        long gecenZaman = sureBitis - sureBaslangic;

        System.out.println("\n-----------------------------------");
        System.out.println("Tarama bitti!");
        System.out.println("Toplam gecen sure: " + gecenZaman + " ms");
        System.out.println("Toplam buldugum acik port: " + acikPortlar.size());
        System.out.println("-----------------------------------");
        
        scan.close();
    }
}

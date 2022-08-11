package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //localhost IP adresini alır, sunucu başka bir IP üzerinde çalışıyorsa, bunu kullanmanız gerekir.
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
            //sunucuya soket bağlantısı kurar.
            socket = new Socket(host.getHostName(), 3000);
            //ObjectOutputStream kullanarak sokete yazdırır.
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Soket sunucusuna istek gonderiliyor...");

                String kullaniciAdi;
                System.out.print("Lutfen kullanici adi giriniz:");
                kullaniciAdi=scanner.nextLine();
                oos.writeObject(""+kullaniciAdi);

            //sunucu yanıt mesajını oku
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //kaynakları kapat
            ois.close();
            oos.close();
            Thread.sleep(100);

        //sunucuya soket bağlantısı kurar.
        socket = new Socket(host.getHostName(), 3000);
        //ObjectOutputStream kullanarak sokete yazdırır.
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Dosya icin Soket sunucusuna istek gonderiliyor...");
        //sunucu yanıt mesajını oku
        ois = new ObjectInputStream(socket.getInputStream());
        List getmesaj = (List) ois.readObject();
        System.out.println("File Message: ");//Dosya mesajı
        //list ile getmesaj nesnesi üzerinden içindeki veriler üzerinden gezinme yaparak basar.
        for (Object list:getmesaj) {
            System.out.println(list);
        }
        //kaynakları kapat
        ois.close();
        oos.close();
        Thread.sleep(100);

        }
}
package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer implements Runnable{
    //statik ServerSocket değişkeni
    private static ServerSocket server;
    //dinlenilecek soket sunucusunun bağlantı portu
    private static int port = 3000;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Thread thread = new Thread();
        boolean b = true;
        //soket sunucusu nesnesini oluşturur.
        server = new ServerSocket(port);

        System.out.println("Sunucu istegi bekleniyor...");
        //soket oluşturma ve istemci bağlantısı için bekleniyor.
        Socket socket = server.accept();
        //soketten ObjectInputStream nesnesine oku.
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        //ObjectInputStream nesnesini String'e dönüştürür.
        String message = (String) ois.readObject();
        //ObjectOutputStream nesnesi oluşturur.
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        String hostName = socket.getInetAddress().getHostName();

        //nesneyi Socket'e yazdırır.
        thread.start();
        System.out.println("Thread calisti");
        oos.writeObject("Sunucuya hosgeldiniz, " + message);
        System.out.println("Istemci Adi: " + hostName);
        Socket socket1 = server.accept();
        System.out.println("Dosya icin istek bekleniyor...");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
        List list=new ArrayList();
        try {
            //istediğimiz herhangi bir metin dosyasını cliente gönderir.
            FileReader fileReader = new FileReader("src/main/LocalHostKill.txt");
            //File file=new File("src/main/LocalHostKill.txt");
            String line;
            BufferedReader br = new BufferedReader(fileReader);
            for (int i = 0; (line = br.readLine()) != null; i++) {
                list.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        objectOutputStream.writeObject(list);
        //kaynakları kapatır.(ois,oos,socket)
        System.out.println("Soket sunucusunu kapat !");
        //ServerSocket nesnesini kapatır.
        server.close();
        //istemci ayrıldı
        System.out.println(hostName + " Ayrildi...");
        ois.close();
        oos.close();
        socket.close();
    }

@Override
    public void run() {

    }
}
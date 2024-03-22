package examen2_lab2;

import java.io.IOException;
import java.io.RandomAccessFile;

public class PSNUsers {

    private RandomAccessFile raf;
    private HashTable users;

    public PSNUsers() {
        try {
            raf = new RandomAccessFile("psn", "rw");
            users = new HashTable();
            reloadHashTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadHashTable() throws IOException {
        raf.seek(0); 
        while (raf.getFilePointer() < raf.length()) {
            String username = raf.readUTF();
            long position = raf.readLong();
            users.add(username, position);
        }
    }

    void addUser(String username) throws IOException {
        if (users.search(username) != -1) {
            System.out.println("El usuario ya existe.");
            return;
        }

      
        long pos = raf.length();
        raf.writeUTF(username);
        raf.writeLong(pos);

     
        users.add(username, pos);
    }

    void deactivateUser(String username) throws IOException {
        long pos = users.search(username);
        if (pos != -1) {
           
            raf.seek(pos);
            raf.writeBoolean(false);

        
            users.remove(username);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    void addTrophyTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        long pos = users.search(username);
        if (pos != -1) {

            raf.seek(raf.length());
            raf.writeUTF(username);
            raf.writeUTF(trophyGame);
            raf.writeUTF(trophyName);
            raf.writeUTF(type.name());
            raf.seek(pos);
            int trophyCount = raf.readInt();
            raf.seek(pos);
            raf.writeInt(trophyCount + 1);
            int trophyPoints = type.getPoints();
            raf.seek(pos + 4);
            int currentPoints = raf.readInt();
            raf.seek(pos + 4);
            raf.writeInt(currentPoints + trophyPoints);
            System.out.println("Trofeo agregado con éxito.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void playerInfo(String username) throws IOException {
        long pos = users.search(username);
        if (pos != -1) {
           
            raf.seek(pos);

         
            int trophyCount = raf.readInt();
            int points = raf.readInt();

         
            System.out.println("Username: " + username);
            System.out.println("Total Trophies: " + trophyCount);
            System.out.println("Total Points: " + points);

            // Imprimir los trofeos del usuario
            System.out.println("Trophies:");
            for (int i = 0; i < trophyCount; i++) {
                String trophyGame = raf.readUTF();
                String trophyName = raf.readUTF();
                String trophyType = raf.readUTF();
             
                System.out.println("  - Game: " + trophyGame + ", Trophy: " + trophyName + ", Type: " + trophyType);
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

}

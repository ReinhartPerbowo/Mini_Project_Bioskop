package com.miniProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main extends Menu{

    public void connection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void main(String[] args) throws ClassNotFoundException{
        Main main = new Main();
        Menu menu = new Menu();

        // panggil connection sql
        main.connection();
        String url = "jdbc:mysql://localhost:3306/bioskop2";
        String user = "root";
        String password = "reinhart06";

        Scanner scan = new Scanner(System.in);
        int choice;

        do{
            menu.menu1();
            choice = scan.nextInt();
            main.clearScreen();
            
            if(choice == 1){
                do {
                    menu.menu2();
                    choice = scan.nextInt();
                    scan.nextLine();

                    if(choice == 1){
                        main.clearScreen();
                        String input = "";
                        do {
                            main.clearScreen();
                            try {
                                // nama film
                                System.out.print("Nama Film : ");
                                String namaFilm = scan.nextLine();
        
                                // genre film
                                System.out.print("Genre : ");
                                String genre = scan.nextLine();
        
                                // casting
                                System.out.print("Casting : ");
                                String casting = scan.nextLine();
        
                                // tanggal tayang
                                System.out.print("Tanggal Tayang (yyyy-mm-dd) : ");
                                String tglTayang = scan.nextLine();
       
                                // jam tayang
                                System.out.print("Jam Tayang (hh:mm:ss) : ");
                                String jamTayang = scan.nextLine();
        
                                // harga tiket
                                System.out.print("Harga Tiket : ");
                                Integer hargaTiket = scan.nextInt();
                                scan.nextLine();
        
                                //
                                System.out.print("ketik \"SELESAI\" untuk berhenti, ketik \"APA SAJA\" untuk lanjut : ");
                                input = scan.nextLine();
        
                                Connection con = DriverManager.getConnection(url, user, password);
                                
                                String sql = "insert into film_detail(nama_film, genre_film, cast_film, tanggal_tayang, jam_tayang, harga_tiket) values(?,?,?,?,?,?)";
        
                                PreparedStatement myState = con.prepareStatement(sql);
        
                                myState.setString(1, namaFilm);
                                myState.setString(2, genre);
                                myState.setString(3, casting);
                                myState.setString(4, tglTayang);
                                myState.setString(5, jamTayang);
                                myState.setInt(6, hargaTiket);
        
                                myState.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                        } while (!input.toLowerCase().equals("selesai"));
                    }
                    
                    else if(choice == 2){
                        main.clearScreen();
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement st = con.createStatement();
                            String sql = ("select * from film_detail");
                            ResultSet rs = st.executeQuery(sql);
                            int i = 0;
                            System.out.println("DAFTAR SEMUA FILM");
                            System.out.println();
                            while (rs.next()) {
                                System.out.print(i+1+". ");
                                System.out.print(rs.getString("nama_film"));
                                System.out.print(";     "+rs.getString("genre_film"));
                                System.out.print(";     "+rs.getString("cast_film"));
                                System.out.print(";     "+rs.getString("tanggal_tayang"));
                                System.out.print(";     "+rs.getString("jam_tayang"));
                                System.out.println(";   "+rs.getInt("harga_tiket"));
                                System.out.println("----------------------------------------------------------------------------------------------------------------");
                                i++;
                            }
                            
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    
                    else if(choice == 3){
                        main.clearScreen();
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement st = con.createStatement();
                            String sql = ("select * from film_detail fd order by fd.tanggal_tayang desc");
                            ResultSet rs = st.executeQuery(sql);
                            int i = 0;
                            System.out.println("TANGGAL TAYANG PALING AKHIR S/D PALING AWAL");
                            System.out.println();
                            while (rs.next()) {
                                System.out.print(i+1+". ");
                                System.out.print(rs.getString("nama_film"));
                                System.out.print(";     "+rs.getString("genre_film"));
                                System.out.print(";     "+rs.getString("cast_film"));
                                System.out.print(";     "+rs.getString("tanggal_tayang"));
                                System.out.print(";     "+rs.getString("jam_tayang"));
                                System.out.println(";   "+rs.getInt("harga_tiket"));
                                System.out.println("----------------------------------------------------------------------------------------------------------------");
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    
                    else if(choice == 4){
                        main.clearScreen();
                        try {
                            Connection con = DriverManager.getConnection(url, user, password);
                            Statement st = con.createStatement();
                            String sql = ("select * from film_detail fd order by fd.harga_tiket asc LIMIT 3;");
                            ResultSet rs = st.executeQuery(sql);
                            int i = 0;
                            System.out.println("HARGA TIKET FILM PALING MURAH (3 FILM)");
                            System.out.println();
                            while (rs.next()) {
                                System.out.print(i+1+". ");
                                System.out.print(rs.getString("nama_film"));
                                System.out.print(";     "+rs.getString("genre_film"));
                                System.out.print(";     "+rs.getString("cast_film"));
                                System.out.print(";     "+rs.getString("tanggal_tayang"));
                                System.out.print(";     "+rs.getString("jam_tayang"));
                                System.out.println(";   "+rs.getInt("harga_tiket"));
                                System.out.println("----------------------------------------------------------------------------------------------------------------");
                                i++;
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }

                    else if(choice == 5){
                        main.clearScreen();
                    }

                    else{
                        main.clearScreen();
                        System.out.println("Maaf pilihan anda tidak terdaftar, silahkan pilih lagi");   
                    }
                } while (choice != 5);
            }
            else if(choice == 2){
                main.clearScreen();
                System.out.println("Terima kasih telah menggunakan aplikasi \"Bioskop\" Kodehive");
                System.exit(0);
            }
            else{
                main.clearScreen();
                System.out.println("Maaf pilihan anda tidak terdaftar, silahkan pilih lagi");
            }
        }while(choice != 2);   
    }
}
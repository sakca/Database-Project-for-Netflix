package projekod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Baglantilar {

    static String user = "root";
    static String password = "admin";
    static String url = "jdbc:mysql://localhost:3306/netflixproje";
    static String dbName = "netflixproje";
    static String driver = "com.mysql.jdbc.Driver";
    public static Connection conn = null;

    public static void main(String[] args) {
    }

    public static Connection java_baglama() { //return conn
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.print("Baglanti basarili.");
            } else {
                System.out.print("Baglanti basarisiz.");
            }

            return conn;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    static void ekle(String sql_sorgu) throws SQLException { //statement work

        Statement st;
        PreparedStatement ps;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            st = conn.createStatement();
            st.executeUpdate(sql_sorgu);
            st.close();
            System.out.println("Kayit basarili");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResultSet getVeri(String sql_veri) { // return resultset

        Statement st;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            rs = st.executeQuery(sql_veri);
            return rs;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void programtur_olustur() { //programtur tablosunun veri tablosuna bagli olarak olusturulmasi

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password); //baglanti saglandi
            Statement st1 = conn.createStatement();

            String sql_veri = "SELECT idveri, tur FROM veri";
            ResultSet rs = st1.executeQuery(sql_veri);
            while (rs.next()) {     // veri tablosundaki programlarin, tur ve program id leri alinir
                Statement statement1;
                statement1 = conn.createStatement();
                String ID = rs.getString("idveri");
                String idKayit = "INSERT INTO programtur (id_program ) VALUES ('" + ID + "')";
                statement1.executeUpdate(idKayit);

                String tur = rs.getString("tur");
                String splitt[] = tur.split(",");   //Bir program birden fazla ture sahip olabilir
                String tur_Sorgu, dbkayitTur;

                int sayac = 1;
                for (String splittTur : splitt) {
                    tur_Sorgu = "SELECT idtur FROM tur where adtur=" + "\"" + splittTur + "\"";
                    ResultSet rs2 = statement1.executeQuery(tur_Sorgu);
                    if (rs2.next()) {
                        String tid = rs2.getString("idtur");
                        dbkayitTur = "UPDATE programtur SET id_tur" + sayac + "= '" + tid + "' WHERE id_program='" + ID + "'";
                        //max 3 farkli ture sahip oldugundan 3 kolon farkli farkli doldurulur

                        Statement statement2;
                        try {
                            statement2 = conn.createStatement();
                            statement2.executeUpdate(dbkayitTur);
                            statement2.close();
                            System.out.print("Kayit basairili. " + sayac);
                        } catch (SQLException ex) {
                            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("FOR sayac: " + sayac);
                    sayac++;
                }
                System.out.println("sayac: " + sayac);
                //sayac 2 ise hem 2 hem 3
                //sayac 3 ise sadece 3
                //sayac 4 ise hicbiri
                while (sayac != 4) { //3ten az sayida turu olan programlara 13 ile null deger atanirr
                    String dbkayitNull = "UPDATE programtur SET id_tur" + sayac + "= '" + 13 + "' WHERE id_program='" + ID + "'";
                    // String dbkayitNull = "INSERT INTO programtur (id_tur" + sayac + ") VALUES ('" + 11 + "')";

                    Statement statement2;
                    try {
                        statement2 = conn.createStatement();
                        statement2.executeUpdate(dbkayitNull);
                        statement2.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sayac++;
                }

                statement1.close();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void programTablosuOlustur() { //excel verisi database'e aktarılır

        FileInputStream f = null;
        try {
            // if (tempIntID != 1) {
            File dosya = new File("D:\\Di�eer\\eclipse-workspace\\NetflixProje\\veriler\\Netflix DB.xlsx");  // verilerin tutuldugu excel
            Statement st;
            st = conn.createStatement();
            System.out.println("................");

            f = new FileInputStream(dosya);
            XSSFWorkbook workbook = new XSSFWorkbook(f);
            XSSFSheet excelSheet = workbook.getSheetAt(0);
            int veriID = 1;

            for (int row = 0; row < excelSheet.getLastRowNum() + 1; row++) {
                XSSFRow excelRow = excelSheet.getRow(row);

                int column = 0;
                Cell cellIsim = excelRow.getCell(column);
                String isim = String.valueOf(cellIsim);  

                Cell cellTur = excelRow.getCell(column + 1);
                String tur = String.valueOf(cellTur);   

                Cell cellTip = excelRow.getCell(column + 2);
                String tip = String.valueOf(cellTip);   

                Cell cellBolum = excelRow.getCell(column + 3);
                String bolumSayisi = String.valueOf((int) Math.round(Double.valueOf(String.valueOf(cellBolum))));

                Cell cellPuan = excelRow.getCell(column + 4);
                String puan = String.valueOf((int) Math.round(Double.valueOf(String.valueOf(cellPuan)))); 

                String dbkayitVeri = "INSERT INTO veri (idveri, isimveri, tip, tur, bolumSayi, puan)"
                        + "VALUES('" + veriID + "','" + isim + "', '" + tip + "', '" + tur + "','" + bolumSayisi + "','" + puan + "')";

                st.executeUpdate(dbkayitVeri);

                veriID++;  
                f.close();
                workbook.close();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void tabloSifirlama(String tabloIsmi, int firstId, int lastId) {

        String tabloSil;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            for (int i = firstId; i <= lastId; i++) {
                tabloSil = "DELETE FROM " + tabloIsmi;
                Statement st;
                st = conn.createStatement();
                st.executeUpdate(tabloSil);
            }
            
            System.out.println("Silme islemi basarili.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Baglantilar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

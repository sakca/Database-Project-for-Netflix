/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Sare
 */
public class Projekod {

    static Connection conn;
    static ResultSet rs;

    public static void main(String[] args) throws SQLException {
        
        Giris basla = new Giris();
        basla.setVisible(true); //kayitli kullanici girise yonlendirilir
        basla.pack();
        basla.setLocationRelativeTo(null);
        basla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

      /*  conn = Baglantilar.java_baglama();
       
        int lastId = 0;
        String getId = "Select idveri from veri WHERE idveri=(SELECT max(idveri) FROM veri)";//en büyük id değerini al
        rs = Baglantilar.getVeri(getId);
        if (rs.next()) {
            lastId = rs.getInt("idveri");
        }
        System.out.println("En büyük id bilgisini alma başarılı. id bilgisi: " + lastId);

        Baglantilar.tabloSifirlama("veri", 1, lastId);//diğerlerini sil */
        
        //Baglantilar.programTablosuOlustur();
        //baglama.programtur_olustur();
        //db verilerini ayarla
        //giris sekmesini ac
    }

}

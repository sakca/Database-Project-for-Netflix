package projekod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Menu extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel table_model = new DefaultTableModel();
    static int userID = 0;
    int bolumCounter = 0, programID = 0, toplamBolum = 0;
    Date izlenmeTarih;
    boolean flag = false;
    boolean flagIzlendi = false;

    public Menu(int id) {
        initComponents();
        userID = id; //giriş yapan kullanici id bilgisini tut
        lbl_izlendi.setVisible(false);
        btn_yenibolum.setVisible(false);
        lbl_bolumBilgisi.setVisible(false);
        btn_puan.setEnabled(false); //hic izlenmediyse kullanilamaz

        Object[] columns = {"Film Adi", "Film Turu", "Cesit", "Bolum", "Puan"}; //tablo ve başlıkları belirt
        table_model.setColumnIdentifiers(columns);
        tbl.setModel(table_model);
        baglan_listele();
    }

    public void baglan_listele() { //tum filmleri listele
        conn = Baglantilar.java_baglama();
        if (conn != null) {
            String sql = "Select isimveri, tip, tur, bolumSayi, puan from veri";

            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                Object[] data = new Object[6];

                while (rs.next()) {
                    data[0] = rs.getString("isimveri");
                    data[1] = rs.getString("tip");
                    data[2] = rs.getString("tur");
                    data[3] = rs.getString("bolumSayi");
                    data[4] = rs.getString("puan");
                    table_model.addRow(data);
                }
                tbl.setModel(table_model);
                tbl.getColumnModel().getColumn(0).setPreferredWidth(210);
                tbl.getColumnModel().getColumn(1).setPreferredWidth(70);
                tbl.getColumnModel().getColumn(2).setPreferredWidth(230);
                tbl.getColumnModel().getColumn(3).setPreferredWidth(50);
                tbl.getColumnModel().getColumn(4).setPreferredWidth(50);
                tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        gelen_ad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        btn_izle = new javax.swing.JButton();
        btn_puan = new javax.swing.JButton();
        btn_yenibolum = new javax.swing.JButton();
        lbl_izlendi = new javax.swing.JLabel();
        lbl_bolumBilgisi = new javax.swing.JLabel();
        text_puanGir = new javax.swing.JTextField();
        btn_filmler = new javax.swing.JButton();
        lbl_puanBilgisi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Film adi");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));
        getContentPane().add(gelen_ad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 230, -1));

        jButton1.setText("ARA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 90, -1));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbl.getColumnModel().getColumn(2).setPreferredWidth(200);
            tbl.getColumnModel().getColumn(3).setPreferredWidth(25);
            tbl.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 640, 210));

        btn_izle.setText("İZLE");
        btn_izle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_izleActionPerformed(evt);
            }
        });
        getContentPane().add(btn_izle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 90, -1));

        btn_puan.setText("PUAN VER");
        btn_puan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_puanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_puan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 90, -1));

        btn_yenibolum.setText("Yeni Bölüme Geç");
        btn_yenibolum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_yenibolumActionPerformed(evt);
            }
        });
        getContentPane().add(btn_yenibolum, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        lbl_izlendi.setText("BU PROGRAM İZLENDİ.");
        getContentPane().add(lbl_izlendi, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 150, 40));

        lbl_bolumBilgisi.setText("... bölümde kaldınız.");
        getContentPane().add(lbl_bolumBilgisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 240, 60));

        text_puanGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_puanGirActionPerformed(evt);
            }
        });
        getContentPane().add(text_puanGir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 130, -1));

        btn_filmler.setText("Film Listesi");
        btn_filmler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filmlerActionPerformed(evt);
            }
        });
        getContentPane().add(btn_filmler, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 130, -1));
        getContentPane().add(lbl_puanBilgisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projekod/back2.jpeg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Arama butonuna yazilan filmi bul ve tabloda goster
        String ad = gelen_ad.getText();
        String tip = null;
        int programid = 0, bolumAdet = 0;

        String tipBelirle = "SELECT tip, bolumSayi,idveri FROM veri WHERE isimveri='" + ad + "'"; //Secilen program id belirlemek icin 

        ResultSet rsTip = Baglantilar.getVeri(tipBelirle);
        try {
            if (rsTip.next()) {
                programid = rsTip.getInt("idveri");
                bolumAdet = rsTip.getInt("bolumSayi");
            }
            programID = programid;
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        String izlenme = "SELECT izlenmeBolum, izlenmeDurum, puan from kullaniciprogram where (kullaniciID=" + userID + " and programID=" + programID + ")"; //kullanicinin aradigi film
        ResultSet rsDurum = Baglantilar.getVeri(izlenme);
        String durum = null;
        int bolumDurumu = 0, verilenPuan = 0;
        try {
            if (rsDurum.next()) {
                durum = rsDurum.getString("izlenmeDurum");
                bolumDurumu = rsDurum.getInt("izlenmeBolum");
                verilenPuan = rsDurum.getInt("puan");
            }
            if (durum != null) { //daha once izlendiyse ya da izleniyorsa
                if (durum.equals("İzlendi")) {
                    lbl_bolumBilgisi.setVisible(false);//program bitti bolum bilgisi yok
                    lbl_izlendi.setVisible(true);//izlendi bilgisi
                    lbl_puanBilgisi.setText(verilenPuan + " puan verdiniz.");
                    lbl_puanBilgisi.setVisible(true);
                    btn_izle.setEnabled(false);
                    btn_yenibolum.setVisible(false);
                    btn_puan.setEnabled(true); //hiç izlenmediyse kullanilamaz
                } else if (durum.equals("İzleniyor")) {
                    lbl_bolumBilgisi.setVisible(true);
                    lbl_bolumBilgisi.setText(bolumDurumu + " bölüm izlendi. Toplam bolum sayisi " + bolumAdet + "."); //son izlenen bolum bilgisi gorunecek
                    lbl_izlendi.setVisible(false);//izlendi bilgisi
                    lbl_puanBilgisi.setText(verilenPuan + " puan verdiniz.");
                    lbl_puanBilgisi.setVisible(true);
                    btn_izle.setEnabled(false);
                    btn_yenibolum.setVisible(true);
                    btn_puan.setEnabled(true);
                }

            } else { //daha once hic izlenmediyse
                btn_izle.setEnabled(true); //hic izlenmemis icerikler izlenebilir
                lbl_bolumBilgisi.setVisible(false); //hiç izlenmemis icerikte bolum bilgisi yok
                lbl_izlendi.setVisible(false);//hiç izlenmemis icerikte izlendi bilgisi yok
                lbl_puanBilgisi.setVisible(false);//hiç izlenmemis icerikte puan bilgisi yok           
                btn_yenibolum.setVisible(false);         
            }

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        table_model.setRowCount(0);

        String temp = null;
        ResultSet myRs = null;
        try {
            String sql_sorgu = "select * from veri where isimveri ='" + ad + "'";
            Object[] data = new Object[6];
            myRs = Baglantilar.getVeri(sql_sorgu);

            if (myRs.next()) {
                temp = myRs.getString("isimveri");
                data[0] = myRs.getString("isimveri");
                data[1] = myRs.getString("tip");
                data[2] = myRs.getString("tur");
                data[3] = myRs.getString("bolumSayi");
                data[4] = myRs.getString("puan");
                table_model.addRow(data);
                System.out.println("Arama basarili");
            } else {
                JOptionPane.showMessageDialog(null, "Aradiginiz program uygulamada bulunmamakta.");
                baglan_listele();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl.setModel(table_model);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_izleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_izleActionPerformed
        //aranan film bilgisinin id'sini kullanıcıprogram tablosuna kaydedilir
        //bu id'de içerik bölüm sayısına bakilir
        //film ise izleye 1 kere basınca daha tıklanmaz -1bölüm-
        //izlendi bilgisi label olarak görünür
        //izleme tarihini ve izlenme durumu db'ye atilir
        //dizi ise izlenme butonuna her basış 1 bölüm ilerletir
        //bölüm bilgisi label olarak görünür 
        //bölüm bilgisi bu id için her tiklamada güncellenir
        //bölümler bitince izle butonuna daha fazla tiklanmaz
        //"UPDATE programtur SET id_tur" + sayac + "= '" + tid + "' WHERE id_program='" + ID + "'";
        //String filmGuncele = "INSERT INTO kullaniciprogram (izlenmeTarih, izlenmeBolum, izlenmeDurum) VALUES ('" + izlenmeTarih + "','" + 1 + "', '" + izlenmeDurum + "')";

        btn_puan.setEnabled(true); //izlenen film puanlanabilir

        Object veri = table_model.getValueAt(0, 0); //bu isimdeki veri tablosundan idyi kullanici veri ile ilişkile
        System.out.println("tablo veri: " + veri);

        String filmIDkayit = "INSERT INTO kullaniciprogram (programID, kullaniciID) VALUES ((SELECT idveri FROM veri WHERE isimveri='" + veri + "')," + userID + ")";//kullanicinin sectigi programi kaydetmek icin   
        String tipBelirle = "SELECT tip, bolumSayi,idveri FROM veri WHERE isimveri='" + veri + "'"; //Secilen film mi dizi mi belirlemek icin 

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //mysql'e uygun date formati
        String dateTime = sdf.format(dt);
        System.out.println("Timeee: " + dateTime);

        String tip = null;
        int bolumler = 0, programid = 0;
        Statement stTip;
        Connection connconn = Baglantilar.java_baglama();
        try {
            Baglantilar.ekle(filmIDkayit); //once kullanici ve program id kayit sonra diger 3 veriyi guncelle
            //-------------------------------------------------------------
            stTip = connconn.createStatement();
            ResultSet rsTip = stTip.executeQuery(tipBelirle);
            if (rsTip.next()) {
                tip = rsTip.getString("tip");
                bolumler = rsTip.getInt("bolumSayi");
                programid = rsTip.getInt("idveri");

            }
            //-------------------------------------------------------------program tipine gore bolum sayisi izlenebilirligini ayarla
            if (tip.equals("Film")) {//izlenme durumunu ve bolum bilgisini ve tarihi guncelle
                lbl_izlendi.setVisible(true);
                String izlenmeDurum = "İzlendi";
                programID = programid;

                String filmGuncelle2 = "UPDATE kullaniciprogram SET izlenmeTarih='" + dateTime + "', izlenmeBolum='" + 1 + "', izlenmeDurum='" + izlenmeDurum + "' WHERE (kullaniciID=" + userID + " and programID=" + programid + ")";
                Baglantilar.ekle(filmGuncelle2);
                flag = true; //izlendi bilgisi 

            } else {//if (tip.equals("Dizi")) {
                btn_yenibolum.setVisible(true);
                String izlenmeDurum = "İzleniyor";
                programID = programid;
                toplamBolum = bolumler;

                String filmGuncelle2 = "UPDATE kullaniciprogram SET izlenmeTarih='" + dateTime + "', izlenmeBolum='" + 1 + "', izlenmeDurum='" + izlenmeDurum + "' WHERE (kullaniciID=" + userID + " and programID=" + programid + ")";
                Baglantilar.ekle(filmGuncelle2); //izle butonu ile 1 bolum izlenir
                lbl_bolumBilgisi.setVisible(true);
                lbl_bolumBilgisi.setText("1 . bölüm izlendi. Toplam bölüm sayisi: " + toplamBolum);
                System.out.println("tttt: " + toplamBolum);
                flag = true; //izlenmeye baslandi bilgisi 

            }/* else if (tip.equals("Tv Show")) {
                btn_yenibolum.setVisible(true);

                if (bolumCounter - 1 != bolumler) {
                    lbl_bolumBilgisi.setVisible(true);
                    lbl_bolumBilgisi.setText(bolumCounter + ". bölüm izlendi. Toplam bölüm sayisi: " + bolumler);
                    //bolum bilgisini ve tarihi dbde güncelle
                } else { //tum bolumler izlendiyse
                    lbl_izlendi.setVisible(true);
                    //izlenme durumunu ve bolum bilgisini ve tarihi guncelle
                }
            } */

        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        btn_izle.setEnabled(false);
    }//GEN-LAST:event_btn_izleActionPerformed

    private void btn_yenibolumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_yenibolumActionPerformed
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(dt);

        int tempBolum = 0;

        String sonBolum = "SELECT izlenmeBolum from kullaniciprogram where (kullaniciID=" + userID + " and programID=" + programID + ")";//kullanicinin kaldigi son bolumu cek
        ResultSet rsBolum = Baglantilar.getVeri(sonBolum);
        try {
            if (rsBolum.next()) {
                bolumCounter = rsBolum.getInt("izlenmeBolum");
            }
            tempBolum = bolumCounter + 1; //butona her tiklayis bolum sayisinde 1 ilerletir

            if (tempBolum <= toplamBolum) {//programın tum bolumleri izlenmediyse bolum bilgisini ve tarihi dbde guncelle
                String bolumGuncelle = "UPDATE kullaniciprogram SET izlenmeTarih='" + dateTime + "', izlenmeBolum='" + tempBolum + "' WHERE (kullaniciID=" + userID + " and programID=" + programID + ")";
                Baglantilar.ekle(bolumGuncelle); //izle butonu ile 1 bolum izlenir
                lbl_bolumBilgisi.setVisible(true);
                lbl_bolumBilgisi.setText(tempBolum + ". bölüm izlendi. Toplam bölüm sayisi: " + toplamBolum);
            } else { //tum bolumler izlendiyse
                System.out.println(tempBolum + "    " + bolumCounter + "    " + toplamBolum);
                lbl_izlendi.setVisible(true);
                String durumGuncelle = "UPDATE kullaniciprogram SET izlenmeTarih='" + dateTime + "', izlenmeDurum='İzlendi' WHERE (kullaniciID=" + userID + " and programID=" + programID + ")";
                Baglantilar.ekle(durumGuncelle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_yenibolumActionPerformed

    private void btn_puanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_puanActionPerformed
        int puan;
        String textIndex = text_puanGir.getText();

        if (textIndex.equals("")) {
            JOptionPane.showMessageDialog(null, "Puan değeri girmediniz.");
        } else {         //puan girildiyse
            puan = Integer.parseInt(textIndex.trim());
            if (puan <= 10 && puan >= 1) {
                String puanEkle = "UPDATE kullaniciprogram SET puan='" + puan + "' WHERE (kullaniciID=" + userID + " and programID=" + programID + ")"; //puan bilgisini kullanici ve programa göre kaydet
                System.out.println(puanEkle);
                try {
                    Baglantilar.ekle(puanEkle);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Puan değeri 1-10 aralığında olmalı.");
            }

            lbl_puanBilgisi.setVisible(true); //her puan degisiminde labelda gorunur
            lbl_puanBilgisi.setText(textIndex + " puan verdiniz.");
        }


    }//GEN-LAST:event_btn_puanActionPerformed

    private void text_puanGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_puanGirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_puanGirActionPerformed

    private void btn_filmlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filmlerActionPerformed
        table_model.setRowCount(0);
        lbl_bolumBilgisi.setVisible(false);
        lbl_izlendi.setVisible(false);
        lbl_puanBilgisi.setVisible(false);
        btn_yenibolum.setVisible(false);  //tum listelemeye geri donulerek, menu default hale gelir
        btn_izle.setEnabled(false);
        btn_puan.setEnabled(false);
        gelen_ad.setText("");
        text_puanGir.setText("");
        baglan_listele();
    }//GEN-LAST:event_btn_filmlerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(userID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_filmler;
    private javax.swing.JButton btn_izle;
    private javax.swing.JButton btn_puan;
    private javax.swing.JButton btn_yenibolum;
    private javax.swing.JTextField gelen_ad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_bolumBilgisi;
    private javax.swing.JLabel lbl_izlendi;
    private javax.swing.JLabel lbl_puanBilgisi;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField text_puanGir;
    // End of variables declaration//GEN-END:variables
}

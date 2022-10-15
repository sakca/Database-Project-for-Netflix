package projekod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Kayit extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel table_model = new DefaultTableModel();

    String turS1 = null, turS2 = null, turS3 = null;
    boolean cb1 = false, cb2 = false, cb3 = false;

    public Kayit() {
        initComponents();

        Object[] columns = {"Film Turleri", "Film Adı", "Puan"}; //tablo kolonları belirlenir
        table_model.setColumnIdentifiers(columns);
        table.setModel(table_model);
        btn_giris.setVisible(false);
        kayit_kayit.setEnabled(false);

        //veri_yukle();
    }

    public void veri_yukle() { //tum film listesi
        conn = Baglantilar.java_baglama();
        if (conn != null) {
            String sql = "Select tur from veri";

            try {
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                Object[] data = new Object[2];

                while (rs.next()) {
                    data[0] = rs.getString("tur");
                    table_model.addRow(data);
                }
                table.setModel(table_model);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    public void tur_secimi(String tur1, String tur2, String tur3) {  //kullanicinin yaptigi tur seçimlerine göre 6 adet yüksek puanli filmin listelenmesi

        System.out.println("tur1: " + tur1);
        System.out.println("tur2: " + tur2);
        System.out.println("tur3: " + tur3);

        String sorgu1, sorgu2, sorgu3;
        int id1 = 0, id2 = 0, id3 = 0;
        String adID1 = "(SELECT idtur from tur WHERE adtur='" + tur1 + "')";
        String adID2 = "(SELECT idtur from tur WHERE adtur='" + tur2 + "')"; //turlerin idleri belirlenir
        String adID3 = "(SELECT idtur from tur WHERE adtur='" + tur3 + "')";
        String order = "ORDER BY puan DESC LIMIT 0,2";

        ResultSet rs1 = Baglantilar.getVeri(adID1);
        ResultSet rs2 = Baglantilar.getVeri(adID2);
        ResultSet rs3 = Baglantilar.getVeri(adID3);
        Object[] data1 = new Object[7];
        Object[] data2 = new Object[3];
        Object[] data3 = new Object[3];
        try {
            if (rs1.next()) {
                id1 = rs1.getInt("idtur");
                System.out.println("ID 1: " + id1);
            }
            if (rs2.next()) {
                id2 = rs2.getInt("idtur");
                System.out.println("ID 2: " + id2);
            }
            if (rs3.next()) {
                id3 = rs3.getInt("idtur");
                System.out.println("ID 3: " + id3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kayit.class.getName()).log(Level.SEVERE, null, ex);
        }

        //idsi bilinen turlerde yuksek puanli 2ser film listelenecektir
        sorgu1 = "SELECT * FROM veri where idveri IN(SELECT id_program FROM programtur where id_tur1='" + id1 + "' OR id_tur2='" + id1 + "' OR id_tur3='" + id1 + "')" + order + "";
        sorgu2 = "SELECT * FROM veri where idveri IN(SELECT id_program FROM programtur where id_tur1='" + id2 + "' OR id_tur2='" + id2 + "' OR id_tur3='" + id2 + "')" + order + "";
        sorgu3 = "SELECT * FROM veri where idveri IN(SELECT id_program FROM programtur where id_tur1='" + id3 + "' OR id_tur2='" + id3 + "' OR id_tur3='" + id3 + "')" + order + "";

        System.out.println(sorgu1);
        String program1, program2, program3, ad1, ad2, ad3;
        int puan1, puan2, puan3;

        ResultSet rs11 = Baglantilar.getVeri(sorgu1);
        ResultSet rs21 = Baglantilar.getVeri(sorgu2);
        ResultSet rs31 = Baglantilar.getVeri(sorgu3);

        try { //Her tur icin ayri ayri tabloya ekleme islemi yapilir
            while (rs11.next()) {
                program1 = rs11.getString("tur");
                ad1 = rs11.getString("isimveri");
                puan1 = rs11.getInt("puan");
                data1[0] = program1;
                data1[1] = ad1;
                data1[2] = puan1;
                table_model.addRow(data1);
                table.setModel(table_model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kayit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs21.next()) {
                program2 = rs21.getString("tur");
                ad2 = rs21.getString("isimveri");
                puan2 = rs21.getInt("puan");
                data2[0] = program2;
                data2[1] = ad2;
                data2[2] = puan2;

                table_model.addRow(data2);
                table.setModel(table_model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kayit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs31.next()) {
                program3 = rs31.getString("tur");
                ad3 = rs31.getString("isimveri");
                puan3 = rs31.getInt("puan");
                data3[0] = program3;
                data3[1] = ad3;
                data3[2] = puan3;
                table_model.addRow(data3);
                table.setModel(table_model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kayit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kayit_kayit = new javax.swing.JButton();
        kayit_sifre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        kayit_dogum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        kayit_mail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        kayit_isim = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_giris = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Film Turleri"
            }
        ));
        table.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 410, 190));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Aksiyon ve Macera", "Belgesel", "Bilim Kurgu ve Fantastik Yapimlar", "Bilim ve Doga", "Cocuk ve Aile", "Drama", "Gerilim", "Komedi", "Korku", "Romantik" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 220, -1));

        jLabel8.setText("3.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel7.setText("2.");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "","Aksiyon ve Macera", "Belgesel", "Bilim Kurgu ve Fantastik Yapimlar", "Bilim ve Doga", "Cocuk ve Aile", "Drama", "Gerilim", "Komedi", "Korku", "Romantik" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 220, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Aksiyon ve Macera", "Belgesel", "Bilim Kurgu ve Fantastik Yapimlar", "Bilim ve Doga", "Cocuk ve Aile", "Drama", "Gerilim", "Komedi", "Korku", "Romantik" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 220, -1));

        jLabel6.setText("1.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel5.setText("Lütfen kaydolmadan önce 3 film türü seciniz.");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        kayit_kayit.setText("Kaydol");
        kayit_kayit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kayit_kayitActionPerformed(evt);
            }
        });
        getContentPane().add(kayit_kayit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 80, -1));

        kayit_sifre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kayit_sifreActionPerformed(evt);
            }
        });
        getContentPane().add(kayit_sifre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 82, -1));

        jLabel4.setText("Sifre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        getContentPane().add(kayit_dogum, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 82, -1));

        jLabel3.setText("Dogum tarihi");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        getContentPane().add(kayit_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 82, -1));

        jLabel2.setText("Mail");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));
        getContentPane().add(kayit_isim, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 82, -1));

        jLabel1.setText("Isim");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 52, -1));

        btn_giris.setText("GİRİŞ");
        btn_giris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_girisActionPerformed(evt);
            }
        });
        getContentPane().add(btn_giris, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 120, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projekod/back2.jpeg"))); // NOI18N
        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kayit_sifreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kayit_sifreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kayit_sifreActionPerformed

    private void kayit_kayitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kayit_kayitActionPerformed
        String yeni_mail, yeni_sifre, yeni_isim, yeni_dogum, sql_sorgu, tempIDs = null, mail;
        ResultSet idRs = null, mailRs = null;
        boolean kayitDurum = false;

        yeni_mail = kayit_mail.getText();
        yeni_isim = kayit_isim.getText();         //kayit için girilen bilgiler alinir
        yeni_dogum = kayit_dogum.getText();
        yeni_sifre = kayit_sifre.getText();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");  //dogum günü bilgisinin formati belirlenir
        LocalDate d1 = LocalDate.parse(yeni_dogum, df);

        String mailKontrol = "SELECT mail FROM kullanici";
        String getveriID = "SELECT * FROM kullanici WHERE id=(SELECT max(id) FROM kullanici);"; //db'ye kaydedilmis son kullanici id'si

        idRs = Baglantilar.getVeri(getveriID);
        mailRs = Baglantilar.getVeri(mailKontrol);
        try {
            while (mailRs.next()) {
                mail = mailRs.getString("mail");   //mail kontrolu yapilir
                if (mail.equals(yeni_mail)) {
                    kayitDurum = true;  //kayitDurum true oldugunda, daha once kayitli bir kullanici oldugu belirtilir
                    System.out.println("Daha onceden kayit olunmus.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kayit.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (kayitDurum == false) { //daha onceden kaydolunmamissa 
            try {
                while (idRs.next()) {
                    tempIDs = idRs.getString("id");  //son kayitli kullanici idsi alinir
                }
            } catch (SQLException ex) {
                Logger.getLogger(Kayit.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            int tempIDi = Integer.parseInt(tempIDs); //id as integer
            int idNew = (tempIDi + 1); //yeni kullanici id

            sql_sorgu = "INSERT INTO kullanici (id, isim, mail, sifre, dtarih)" + "VALUES ('" + idNew + "', '" + yeni_isim + "', '" + yeni_mail + "', '" + yeni_sifre + "', '" + d1 + "')";
            System.out.println(sql_sorgu);

            try {
                Baglantilar.ekle(sql_sorgu);  //kullanici tablosuna kayit gerçeklesir
            } catch (SQLException ex) {
                Logger.getLogger(Kayit.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            tur_secimi(turS1, turS2, turS3); //secilen turler ile listeleme icin metoda yonlendirilir
            Giris g1 = new Giris(); //giris sayfasina yonlendirilir
            g1.setVisible(true);
            g1.pack();
            g1.setLocationRelativeTo(null);
            g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Kayitli kullanici maili kullandiniz.");
            btn_giris.setVisible(true);
        }
    }//GEN-LAST:event_kayit_kayitActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        turS1 = jComboBox1.getSelectedItem().toString();
        //tur secilmediyse kayit olunmaya izin verilmez
        if (!turS1.equals("")) {
            cb1 = true;
            if (cb2 == true && cb3 == true) {
                kayit_kayit.setEnabled(true);
            } else {
                kayit_kayit.setEnabled(false);
            }
        } else {
            cb1 = false;
            kayit_kayit.setEnabled(false);
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        turS2 = jComboBox2.getSelectedItem().toString();
        //tur secilmediyse kayit olunmaya izin verilmez

        if (!turS2.equals("")) {
            cb2 = true;
            if (cb1 == true && cb3 == true) {
                kayit_kayit.setEnabled(true);
            } else {
                kayit_kayit.setEnabled(false);
            }
        } else {
            cb2 = false;
            kayit_kayit.setEnabled(false);
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        turS3 = jComboBox3.getSelectedItem().toString();
        //tur secilmediyse kayit olunmaya izin verilmez

        if (!turS3.equals("")) {
            cb3 = true;
            if (cb1 == true && cb2 == true) {
                kayit_kayit.setEnabled(true);
            } else {
                kayit_kayit.setEnabled(false);
            }
        } else {
            cb3 = false;
            kayit_kayit.setEnabled(false);
        }

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void btn_girisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_girisActionPerformed
        Giris g2 = new Giris();
        g2.setVisible(true); //kayitli kullanici girise yonlendirilir
        g2.pack();
        g2.setLocationRelativeTo(null);
        g2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_btn_girisActionPerformed

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
            java.util.logging.Logger.getLogger(Kayit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kayit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kayit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kayit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kayit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_giris;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kayit_dogum;
    private javax.swing.JTextField kayit_isim;
    private javax.swing.JButton kayit_kayit;
    private javax.swing.JTextField kayit_mail;
    private javax.swing.JTextField kayit_sifre;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}

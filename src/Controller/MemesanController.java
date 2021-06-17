/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.MemesanDao;
import Dao.PelangganDao;
import java.lang.Double;
import Form.Form_Input;
import Koneksi.Koneksi;
import Model.Memesan;
import Model.Pelanggan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.sql.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RessM
 */

public class MemesanController {


    Form_Input view;
    
    Memesan memesan;
    Pelanggan pelanggan;
    MemesanDao memesandao;
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public MemesanController(Form_Input view) {
        try {
            this.view = view;
            Koneksi k= new Koneksi();
            con = k.getKoneksi();
            clearForm();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        view.getTxt_idpelanggan().setText("");
        view.getTxt_namapelanggan().setText("");
        view.getTxt_alamat().setText("");
        view.getTxt_nohppelanggan().setText("");
        //view.getTxt_pesanan().setInteger.("");
        //view.getTxt_biaya().setDouble("");
        view.getTxt_namagalonman().setText("");
        
    }
    public void Tes(){
        System.out.print("tes");
    }
    public void isiComboK(){
        view.getCbo_idgalonman().removeAllItems();
        view.getCbo_idgalonman().addItem("");
        view.getCbo_idgalonman().addItem("");
    }
    
    public boolean NohpCheck(){
        try{
            String nohp = view.getTxt_nohppelanggan().getText();
            memesan = memesandao.getMemesan(con,nohp);
            if(memesan == null) return true;
        }catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void Kirim(){
        System.out.print("Kirim");
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        memesan = new Memesan();
        pelanggan = new Pelanggan();
        memesan.setId_p(view.getTxt_idpelanggan().getText());
        pelanggan.setNama_p(view.getTxt_namapelanggan().getText());
        pelanggan.setAlamat_p(view.getTxt_alamat().getText());
        pelanggan.setNohp_p(view.getTxt_nohppelanggan().getText());
        memesan.setJmlpesanan(Integer.parseInt(view.getTxt_pesanan().getText()));
        memesan.setBiaya(Double.parseDouble(view.getTxt_biaya().getText()));
        memesan.setNama_k(view.getTxt_namagalonman().getText());
        memesan.setId_k(view.getCbo_idgalonman().getSelectedItem().toString());
        memesan.setId_air("1");
        memesan.setTglpesanan(sqlDate);
        System.out.print(view.getTxt_namagalonman().getText());
        try {
            MemesanDao.insert(con, memesan);
            PelangganDao.insert(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Entri Data Ok");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
    }
}
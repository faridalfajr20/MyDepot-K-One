/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author GF63-622
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Form.Form_Input;
import Model.Karyawan;
import Model.Pelanggan;
import Koneksi.Koneksi;

public class InputController {
    
    Form_Input view;
    Karyawan karyawan;   //model
    Pelanggan pelanggan;   //model
    Connection con;
    public InputController(Form_Input view) {
        try {
            this.view = view;
            Koneksi k = new Koneksi();
            con = k.getKoneksi();
            isiCboKodeAnggota();
            isiCboKodeBuku();
            isiCboStatus();
            clearForm();
            viewTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InputController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InputController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        view.Form_Input().setText("");
        view.getTxtTglKembali().setText("");
    }
    
    public void insert(){
        karyawan = new Karyawan();
        karyawan.setId_p(view.getCboKodeAnggota().getSelectedItem().toString().split("-")[0]);
        karyawan.setNama_p(view.getCboKodeBuku().getSelectedItem().toString().split("-")[0]);
        karyawan.setAlamat_p(view.getTxtTglPinjam().getText());
        karyawan.setnohppelanggan(view.getTxtTglKembali().getText());
        if(view.getCboStatus().getSelectedItem().toString() == "Dikembalikan"){
            karyawan.setStatus(view.getCboStatus().getSelectedIndex());
        }else{
             karyawan.setStatus(view.getCboStatus().getSelectedIndex());
        }
//        peminjaman.setStatus(Integer.parseInt(view.getCboStatus().getSelectedItem().toString()));
        try {
            KaryawanDao.insert(con, karyawan);
            JOptionPane.showMessageDialog(view, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
        
        pelanggan = new Pelanggan();
        pelanggan.setKodeanggota(view.getCboKodeAnggota().getSelectedItem().toString().split("-")[0]);
        pelanggan.setKodebuku(view.getCboKodeBuku().getSelectedItem().toString().split("-")[0]);
        pelanggan.setTglpinjam(view.getTxtTglPinjam().getText());
        pelanggan.setTglkembali(view.getTxtTglKembali().getText());
        if(view.getCboStatus().getSelectedItem().toString() == "Dikembalikan"){
            pelanggan.setStatus(view.getCboStatus().getSelectedIndex());
        }else{
             pelanggan.setStatus(view.getCboStatus().getSelectedIndex());
        }
//        peminjaman.setStatus(Integer.parseInt(view.getCboStatus().getSelectedItem().toString()));
        try {
            PelangganDao.insert(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Entri Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
    }
    
    public void update(){
        karyawan = new Karyawan();
        karyawan.setKodeanggota(view.getCboKodeAnggota().getSelectedItem().toString());
        karyawan.setKodebuku(view.getCboKodeBuku().getSelectedItem().toString());
        karyawan.setTglpinjam(view.getTxtTglPinjam().getText());
        karyawan.setTglkembali(view.getTxtTglKembali().getText());
        karyawan.setStatus(Integer.parseInt((String) view.getCboStatus().getSelectedItem()));
        try {
            KaryawanDao.update(con, karyawan);
            JOptionPane.showMessageDialog(view, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
        
        pelanggan = new Pelanggan();
        pelanggan.setKodeanggota(view.getCboKodeAnggota().getSelectedItem().toString());
        pelanggan.setKodebuku(view.getCboKodeBuku().getSelectedItem().toString());
        pelanggan.setTglpinjam(view.getTxtTglPinjam().getText());
        pelanggan.setTglkembali(view.getTxtTglKembali().getText());
        pelanggan.setStatus(Integer.parseInt((String) view.getCboStatus().getSelectedItem()));
        try {
            PelangganDao.update(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Update Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error "+ex.getMessage()); 
        }
    }
    
    public void delete(){
        try {
            KaryawanDao.delete(con, karyawan);
            JOptionPane.showMessageDialog(view, "Delete Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error " + ex.getMessage());
        }
        
        try {
            PelangganDao.delete(con, pelanggan);
            JOptionPane.showMessageDialog(view, "Delete Data Ok");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error " + ex.getMessage());
        }
    }
    
    public void cancel(){
        int pesan=JOptionPane.showConfirmDialog(null, "Are you sure to cancel?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
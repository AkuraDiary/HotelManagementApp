package ViewModel.MasterViewModel;

import Data.AppEnums.AppEnums;
import Data.Model.Transaksi;
import Domain.Master.MasterTransaksi;
import Domain.Transaksi.TransaksiUseCase;
import Util.Formatting;

import java.util.ArrayList;

public class MasterTransaksiViewModel {
    MasterTransaksi masterTransaksi;
    TransaksiUseCase transaksiUseCase;

    public MasterTransaksiViewModel(MasterTransaksi masterTransaksi, TransaksiUseCase transaksiUseCase) {
        this.transaksiUseCase = transaksiUseCase;
        this.masterTransaksi = masterTransaksi;
    }
    public Transaksi selectedTransaksi;

    // EDITS REGION
//    public void addKamar(String noKamar) {
//        transaksiUseCase.addKamar(noKamar);
//        commitTransaksi();
//        updateStateSelectedTransaksi();
//    }
//
//    public void removeKamar(String noKamar) {
//        transaksiUseCase.removeKamar(noKamar);
//        commitTransaksi();
//        updateStateSelectedTransaksi();
//    }
//
//    public void addCustomer(String NIK) {
//        transaksiUseCase.addCustomer(NIK);
//        commitTransaksi();
//        updateStateSelectedTransaksi();
//    }
//
//    public void removeCustomer(String NIK) {
//        transaksiUseCase.removeCustomer(NIK);
//        commitTransaksi();
//        updateStateSelectedTransaksi();
//    }
//
//    public void checkOut() {
//        transaksiUseCase.checkOut();
//        updateStateSelectedTransaksi();
//        commitTransaksi();
//    }
//
//    public void checkIn() {
//        transaksiUseCase.checkIn();
//        commitTransaksi();
//    }

//    public void bayar(AppEnums.Pembayaran metodeBayar, double amountBayar) {
//        transaksiUseCase.bayar(metodeBayar, amountBayar);
//        commitTransaksi();
//    }
    // EDITS REGION

//    public void commitTransaksi() {
//        if (this.selectedTransaksi != null) {
//            transaksiUseCase.commitTransaksi();
//        } else {
//            Formatting.formatMessageOutput("Tidak ada transaksi yang dipilih / data sudah tidak ada");
//        }
//    }

    public void selectTranasksi(String noTransaksi) {
        masterTransaksi.selectTransaksi(noTransaksi);
        if (masterTransaksi.getSelectedTransaksi() != null) {
            this.selectedTransaksi  = masterTransaksi.getSelectedTransaksi();
//            transaksiUseCase.selectTransaksi(noTransaksi);
//            updateStateSelectedTransaksi();
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di edit");
        }
    }

//    public void updateStateSelectedTransaksi() {
//        this.selectedTransaksi = transaksiUseCase.getCurrentActiveTransaksi();
//    }

    public void batalkanTransaksi(){
        if (selectedTransaksi != null) {
            masterTransaksi.batalkanTransaksi();
            this.selectedTransaksi = null;
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di delete");
        }
    }
    public void deleteTransaksi() {
        if (selectedTransaksi != null) {
            masterTransaksi.deleteTransaksi(this.selectedTransaksi.getNoTransaksi());
            this.selectedTransaksi = null;
        } else {
            Formatting.formatMessageOutput("Pilih transaksi yang akan di delete");
        }

    }

    public ArrayList<Transaksi> getAllTransaksi() {
        return transaksiUseCase.getAllTransaksi();
    }

}



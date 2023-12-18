package ViewModel.MasterViewModel;

import Data.AppEnums.AppEnums;
import Data.Model.Kamar;
import Domain.Master.MasterKamar;
import Util.Formatting;

import static Util.Formatting.formatMessageOutput;

public class MasterKamarViewModel {
    MasterKamar masterKamar;

    public MasterKamarViewModel(MasterKamar masterKamar) {
        this.masterKamar = masterKamar;
    }
    Kamar currentSelectedKamar;
    public void selectKamar(String noKamar){
        this.currentSelectedKamar = masterKamar.getKamar(noKamar);
        if(currentSelectedKamar == null){
            formatMessageOutput("Data Tidak Ditemukan");
        }
    }

    public Kamar getSelectedKamar(){
        return  this.currentSelectedKamar;
    }
    public void addNewKamar(
            String noKamar,
            int kapasitas,
            AppEnums.JenisKamar jenis,
            double harga,
            AppEnums.StatusKamar statusKamar
    ){
        Kamar kamar = new Kamar(
                noKamar,
                jenis,
                harga,
                kapasitas,
                statusKamar
        );
        masterKamar.addKamar(kamar);
    }
    public void deleteKamar(String noKamar) {
        masterKamar.deleteKamar(noKamar);
    }
    public void editKamar(
            String noKamar,
            int kapasitas,
            AppEnums.JenisKamar jenis,
            double harga,
            AppEnums.StatusKamar statusKamar
    ){

        if(currentSelectedKamar != null){
            Kamar kamar = new Kamar(
                    noKamar.isBlank() ? currentSelectedKamar.getNoKamar() : noKamar,
                    jenis == null ? currentSelectedKamar.getJenisKamar() : jenis,
                    harga == 0 ? currentSelectedKamar.getHarga() : harga,
                    kapasitas == 0 ? currentSelectedKamar.getKapasitas() : kapasitas,
                    statusKamar == null ? currentSelectedKamar.getStatusKamar() : statusKamar
            );
            masterKamar.editDataKamar(currentSelectedKamar, kamar);
        }
    }


}

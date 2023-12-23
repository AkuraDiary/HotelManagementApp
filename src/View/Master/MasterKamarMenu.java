package View.Master;

import static Util.Formatting.formatMessageOutput;
import static Util.Formatting.invalidChoice;
import static View.AppRouter.AppRoute.MASTER_KAMAR;
import static View.AppRouter.AppRoute.MASTER_MAIN_MENU;
import static View.Components.KamarView.*;

import java.io.IOException;

import Data.AppEnums.AppEnums;
import Util.InputUtilities;
import View.AppRouter;
import ViewModel.MasterViewModel.MasterKamarViewModel;

public class MasterKamarMenu {
    private final MasterKamarViewModel masterKamarVM;

    public MasterKamarMenu(MasterKamarViewModel masterKamarVM) {
        this.masterKamarVM = masterKamarVM;
    }

    String inputUser;

    public void showMasterKamarMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.MASTER_KAMAR) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         MASTER KAMAR         ");
            System.out.println("==============================");
            System.out.println("1. Show all kamar");
            System.out.println("2. Choose kamar");
            System.out.println("3. Add kamar");
            System.out.println("0. Back");
            System.out.println();
            try {
                System.out.print("Masukkan Pilihan : ");
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showAllKamar();
                        break;
                    case "2":
                        chosingKamar();
                        break;
                    case "3":
                        addKamar();
                        break;
                    case "0":
                        AppRouter.navigateTo(MASTER_MAIN_MENU);
                        break;
                    default:
                        invalidChoice();
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showAllKamar() {
        InputUtilities.cls();
        System.out.println("SHOW ALL KAMAR");
        System.out.println("================================================================================");
        System.out.println(" NO. KAMAR \tKAPASITAS \tHARGA \t\t JENIS \t\tSTATUS");
        System.out.println("================================================================================");

        viewAllDataKamar(masterKamarVM.getListKamar());
        System.out.println("================================================================================");

        InputUtilities.pressEnter();
    }

    private void chosingKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         CHOOSE KAMAR         ");
            System.out.println("==============================");
            System.out.print("No. kamar\t: ");
            String noKamar = InputUtilities.input.readLine();
            masterKamarVM.selectKamar(noKamar);
            System.out.println("==============================");
            InputUtilities.pressEnter();

            if (masterKamarVM.getSelectedKamar() != null) {
                AppRouter.navigateTo(AppRouter.AppRoute.SUB_MASTER_DETAIL_KAMAR);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addKamar() {
        // kalau di ENTER (kosong) semua munculnya aneh
        // kapasitas dan harga error
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         ADD NEW KAMAR        ");
            System.out.println("==============================");

            System.out.print("No. Kamar\t: ");
            String noKamar = InputUtilities.input.readLine();

            System.out.print("Kapasitas\t: ");
            int kapasitas = InputUtilities.input.read();
            InputUtilities.input.readLine(); // biar bawahnya kebaca

            System.out.print("Jenis\t\t: ");
            AppEnums.JenisKamar jenis = jenisKamar();

            System.out.print("Harga\t\t: ");
            double harga = InputUtilities.input.read();
            InputUtilities.input.readLine(); // biar bawahnya kebaca

            System.out.print("Status\t\t: ");
            AppEnums.StatusKamar status = statusKamar();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterKamarVM.addNewKamar(noKamar, kapasitas, jenis, harga, status);
                formatMessageOutput("Kamar added");
            } else {
                formatMessageOutput("Process cancelled");
            }

            System.out.println("==============================");
            InputUtilities.pressEnter();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDetailKamarMenu() {
        while (AppRouter.activeRoute == AppRouter.AppRoute.SUB_MASTER_DETAIL_KAMAR) {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("       SHOW DETAIL KAMAR      ");
            System.out.println("==============================");
            System.out.println("Selected kamar : " + masterKamarVM.getSelectedKamar().getNoKamar());
            System.out.println();
            System.out.println("1. Show detail");
            System.out.println("2. Edit kamar");
            System.out.println("3. Delete kamar");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        detailMasterKamar();
                        break;
                    case "2":
                        editMasterKamar();
                        break;
                    case "3":
                        System.out.println("DELETE KAMAR");
                        deleteMasterKamar();
                        break;
                    case "0":
                        AppRouter.navigateTo(MASTER_KAMAR); // Langsung kembali ke "menu manager", bukan ke "master kamar"
                        break;
                    default:
                        invalidChoice();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_MAIN_MENU);
    }

    private void detailMasterKamar() {
        InputUtilities.cls();
        System.out.println("SHOW DETAIL");
        System.out.println("================================================================================");
        System.out.println(" NO. KAMAR \tKAPASITAS \tHARGA \t\t JENIS \t\tSTATUS");
        System.out.println("================================================================================");

        viewDataSelectedKamar(masterKamarVM.getSelectedKamar());
        System.out.println("================================================================================");

        InputUtilities.pressEnter();
    }

    private void editMasterKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("          EDIT KAMAR          ");
            System.out.println("==============================");
            System.out.print("Kapasitas\t: ");
            int kapasitas = InputUtilities.input.read();
            InputUtilities.input.readLine(); // Biar bawahnya kebaca

            System.out.print("Jenis\t\t: ");
            AppEnums.JenisKamar jenisKamar = jenisKamar();

            System.out.print("Harga\t\t: ");
            double harga = InputUtilities.input.read();
            InputUtilities.input.readLine(); // Biar bawahnya kebaca

            System.out.print("Status kamar\t: ");
            AppEnums.StatusKamar statusKamar = statusKamar();

            System.out.println("==============================");
            System.out.println();
            System.out.print("Apa anda yakin?(y/n): ");
            inputUser = InputUtilities.input.readLine();

            if (inputUser.equalsIgnoreCase("y")) {
                masterKamarVM.editKamar(masterKamarVM.getSelectedKamar().getNoKamar(), kapasitas, jenisKamar, harga, statusKamar);
                formatMessageOutput("Perubahan disimpan");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_KAMAR);
            } else {
                formatMessageOutput("Perubahan dibatalkan");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void deleteMasterKamar() {
        try {
            InputUtilities.cls();
            System.out.println("==============================");
            System.out.println("         DELETE KAMAR         ");
            System.out.println("==============================");
            System.out.println("Selected kamar : " + masterKamarVM.getSelectedKamar().getNoKamar());
            System.out.println();
            System.out.print("Anda yakin ingin menghapus kamar ini?(y/n): ");
            inputUser = InputUtilities.input.readLine();
            if (inputUser.equalsIgnoreCase("y")) {
                masterKamarVM.deleteKamar(masterKamarVM.getSelectedKamar().getNoKamar());
                formatMessageOutput("Kamar dihapus");
                System.out.println("==============================");
                InputUtilities.pressEnter();
                AppRouter.navigateTo(MASTER_KAMAR);
            } else {
                System.out.println();
                formatMessageOutput("Perubahan dibatalkan");
                System.out.println("==============================");
                InputUtilities.pressEnter();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private AppEnums.JenisKamar jenisKamar() {
        try {
            String jenisString = InputUtilities.input.readLine();
            return switch (jenisString.toLowerCase()) {
                case "single" -> AppEnums.JenisKamar.SINGLE;
                case "double" -> AppEnums.JenisKamar.DOUBLE;
                case "family" -> AppEnums.JenisKamar.FAMILY;
                case "vip" -> AppEnums.JenisKamar.VIP;
                case "business" -> AppEnums.JenisKamar.BUSINESS;
                default -> null;
            };
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private AppEnums.StatusKamar statusKamar() {
        try {
            String statusString = InputUtilities.input.readLine();
            return switch (statusString.toLowerCase()) {
                case "available" -> AppEnums.StatusKamar.AVAILABLE;
                case "booked" -> AppEnums.StatusKamar.BOOKED;
                case "cleaning" -> AppEnums.StatusKamar.CLEANING;
                case "occupied" -> AppEnums.StatusKamar.OCCUPIED;
                default -> null;
            };
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}

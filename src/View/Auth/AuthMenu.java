package View.Auth;

import Util.InputUtilities;
import ViewModel.AuthViewModel.AuthViewModel;
import View.AppRouter;

import java.io.IOException;

import static View.AppRouter.AppRoute.LOGIN;

public class AuthMenu {
    private final AuthViewModel authViewModel;

    public AuthMenu(AuthViewModel authViewModel) {
        this.authViewModel = authViewModel;
    }

    String inputUser;

    public void showLogin() {
        while(AppRouter.activeRoute == LOGIN){
            System.out.println("==============================");
            System.out.println("     HOTEL MANAGEMENT APP     ");
            System.out.println("==============================");
            System.out.println("1. Login");
            System.out.println("0. EXIT");
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            try {
                inputUser = InputUtilities.input.readLine();
                switch (inputUser) {
                    case "1":
                        showLoginInputs();
                        break;
                    case "0":
                        AppRouter.navigateTo(AppRouter.AppRoute.EXIT);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showLoginInputs() {
        InputUtilities.cls(); // clear layar
        System.out.println("==============================");
        System.out.println("          LOGIN MENU          ");
        System.out.println("==============================");
        try {
            System.out.print("Masukkan ID atau Email\t: ");
            String userIDEmail = InputUtilities.input.readLine();
            System.out.print("Masukkan Password\t: ");
            String userPass= InputUtilities.input.readLine();

            this.authViewModel.doLogin(userIDEmail, userPass);
            if(this.authViewModel.loggedUser != null){
                switch (this.authViewModel.loggedUser.getRole()){
                    case MANAGER :
                        AppRouter.navigateTo(AppRouter.AppRoute.MASTER_MAIN_MENU);
                        break;
                    case PEGAWAI:
                        AppRouter.navigateTo(AppRouter.AppRoute.MAIN_MENU);
                        break;
                    default:
                    System.out.println("HAH!?");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import com.formdev.flatlaf.FlatDarkLaf;
import ui.LoginFrame;

import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch(UnsupportedLookAndFeelException e){
            throw new RuntimeException();
        }
        new LoginFrame();
    }
}
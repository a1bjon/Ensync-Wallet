package utils;

import javax.swing.*;

public final class UITheme{

    // PRIVATE ACCESS CONSTRUCTOR
    private UITheme(){}

    /**
     * Sets default theme of components.
     */
    public static void setTheme(){
        // JPANEL THEME
        UIManager.put("Panel.background", Constants.GREY12);

        // JBUTTON THEME
        UIManager.put("Button.background", Constants.GREY);
        UIManager.put("Button.foreground", Constants.WHITE);

        // JTEXTFIELD THEME
        UIManager.put("TextField.background", Constants.DARK_GREY);
        UIManager.put("TextField.foreground", Constants.WHITE);
        UIManager.put("TextField.font", Constants.DEFAULT_TEXT_FIELD_FONT);
        UIManager.put("TextField.selectionBackground", Constants.ENSYNC_BLUE);
        UIManager.put("TextField.caretForeground", Constants.ENSYNC_BLUE);
        UIManager.put("TextField.caretBlinkRate", 500);

        // JPASSWORDFIELD THEME
        UIManager.put("PasswordField.background", Constants.DARK_GREY);
        UIManager.put("PasswordField.foreground", Constants.WHITE);
        UIManager.put("PasswordField.font", Constants.DEFAULT_TEXT_FIELD_FONT);
        UIManager.put("PasswordField.selectionBackground", Constants.ENSYNC_BLUE);
        UIManager.put("PasswordField.caretForeground", Constants.ENSYNC_BLUE);
        UIManager.put("PasswordField.caretBlinkRate", 500);

        // JLABEL THEME
        UIManager.put("Label.background", Constants.GREY12);
        UIManager.put("Label.foreground", Constants.DARK_GREY);
        UIManager.put("Label.font", Constants.DEFAULT_LABEL_FONT);

        // COMPONENT TOOLTIP THEME
        UIManager.put("ToolTip.background", Constants.GREY);
        UIManager.put("ToolTip.foreground", Constants.ENSYNC_BLUE);
        UIManager.put("ToolTip.border", Constants.GREY);

        // OPTIONPANE THEME
        UIManager.put("OptionPane.background", Constants.GREY12);
        UIManager.put("OptionPane.messageFont", Constants.DEFAULT_OPTION_PANE_FONT);
        UIManager.put("OptionPane.buttonFont", Constants.DEFAULT_OPTION_PANE_FONT);
        UIManager.put("OptionPane.messageForeground", Constants.ENSYNC_BLUE);
        UIManager.put("OptionPane.informationIcon", Constants.OPTION_PANE_MESSAGE_ICON);
        UIManager.put("OptionPane.questionIcon", Constants.OPTION_PANE_CONFIRM_ICON);
        UIManager.put("OptionPane.errorIcon", Constants.OPTION_PANE_ERROR_ICON);
    }
}

package utils;

import javax.swing.*;

import java.awt.*;

public final class Constants{

    // PRIVATE ACCESS CONSTRUCTOR
    private Constants(){}

    // OS CONSTANTS
    public static final String OS_VERSION = System.getProperty("os.name");

    // WINDOW CONSTANTS
    public static final String WINDOW_TITLE = "Ensync Wallet";

    public static final Image WINDOW_ICON = new ImageIcon("src/main/resources/images/logos/ensync_logo.png").getImage();

    public static final Dimension APP_WINDOW_DIMENSION = new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8), // 80% of monitor width
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8)); // 80% of monitor height

    public static final Dimension MINIMUM_APP_WINDOW_DIMENSION = new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.7), // 70% of monitor width
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7)); // 70% of monitor height

    // LOGIN FORM CONSTANTS
    public static final Dimension LOGIN_WINDOW_DIMENSION = new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5729), // 57.29% of monitor width
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5555)); // 55.55% of monitor height

    public static final Dimension MINIMUM_LOGIN_WINDOW_SIZE = new Dimension(
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3177), // 31.77% of monitor width
            (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.3703)); // 37.03% of monitor height

    // TEXT FIELD CONSTANTS
    public static final Font DEFAULT_TEXT_FIELD_FONT = new Font("consolas", Font.BOLD, 19);

    // LABEL CONSTANTS
    public static final Font DEFAULT_LABEL_FONT = new Font("consolas", Font.BOLD, 19);

    // OPTION PANE CONSTANTS
    public static final Font DEFAULT_OPTION_PANE_FONT = new Font("consolas", Font.BOLD, 15);

    public static final ImageIcon OPTION_PANE_MESSAGE_ICON = new ImageIcon("src/main/resources/images/other/tick_icon.png");

    public static final ImageIcon OPTION_PANE_CONFIRM_ICON = new ImageIcon("src/main/resources/images/other/question_mark_icon.png");
    
    public static final ImageIcon OPTION_PANE_ERROR_ICON = new ImageIcon("src/main/resources/images/other/error_icon.png");

    // UI IMAGE ICON CONSTANTS
    public static final ImageIcon LOGIN_BACKGROUND = new ImageIcon("src/main/resources/images/backgrounds/login_background.png");

    public static final ImageIcon ENSYNC_LOGO = new ImageIcon("src/main/resources/images/logos/ensync_logo.png");

    public static final ImageIcon ENSYNC_LOGO_WITH_NAME = new ImageIcon("src/main/resources/images/logos/ensync_logo_with_name.png");

    public static final ImageIcon SHOW_PASS_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/show_pass_button_icon.png");

    public static final ImageIcon SHOW_PASS_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/show_pass_button_icon_active.png");

    public static final ImageIcon LOGIN_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/login_button_icon.png");

    public static final ImageIcon LOGIN_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/login_button_pressed_icon.png");

    public static final ImageIcon REGISTER_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/create_wallet_button_icon.png");

    public static final ImageIcon REGISTER_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/create_wallet_button_pressed_icon.png");

    public static final ImageIcon GENERATE_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/generate_wallet_button_icon.png");

    public static final ImageIcon GENERATE_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/generate_wallet_button_pressed_icon.png");

    public static final ImageIcon BACK_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/back_button_icon.png");

    public static final ImageIcon BACK_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/back_button_pressed_icon.png");

    public static final ImageIcon DASHBOARD_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/dashboard_button_icon.png");

    public static final ImageIcon DASHBOARD_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/dashboard_button_pressed_icon.png");

    public static final ImageIcon RECEIVE_FUNDS_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/receive_funds_button_icon.png");

    public static final ImageIcon RECEIVE_FUNDS_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/receive_funds_button_pressed_icon.png");

    public static final ImageIcon SEND_FUNDS_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/send_funds_button_icon.png");

    public static final ImageIcon SEND_FUNDS_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/send_funds_button_pressed_icon.png");

    public static final ImageIcon SETTINGS_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/settings_button_icon.png");

    public static final ImageIcon SETTINGS_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/settings_button_pressed_icon.png");

    public static final ImageIcon LOGOUT_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/logout_button_icon.png");

    public static final ImageIcon LOGOUT_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/logout_button_pressed_icon.png");

    public static final ImageIcon POUND_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/pound_button_icon.png");

    public static final ImageIcon POUND_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/pound_button_pressed_icon.png");

    public static final ImageIcon EURO_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/euro_button_icon.png");

    public static final ImageIcon EURO_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/euro_button_pressed_icon.png");

    public static final ImageIcon DOLLAR_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/dollar_button_icon.png");

    public static final ImageIcon DOLLAR_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/dollar_button_pressed_icon.png");

    public static final ImageIcon AUD_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/aud_button_icon.png");

    public static final ImageIcon AUD_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/aud_button_pressed_icon.png");

    public static final ImageIcon LIRA_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/lira_button_icon.png");

    public static final ImageIcon LIRA_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/lira_button_pressed_icon.png");

    public static final ImageIcon ZLOTY_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/zloty_button_icon.png");

    public static final ImageIcon ZLOTY_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/zloty_button_pressed_icon.png");

    public static final ImageIcon UPDATE_NAME_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/update_name_button_icon.png");

    public static final ImageIcon UPDATE_NAME_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/update_name_button_pressed_icon.png");

    public static final ImageIcon DELETE_WALLET_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/delete_wallet_button_icon.png");

    public static final ImageIcon DELETE_WALLET_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/delete_wallet_button_pressed_icon.png");

    public static final ImageIcon VIEW_RECOVERY_PHRASE_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/view_recovery_phrase_button_icon.png");

    public static final ImageIcon VIEW_RECOVERY_PHRASE_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/view_recovery_phrase_button_pressed_icon.png");

    public static final ImageIcon COPY_ADDRESS_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/copy_address_button_icon.png");

    public static final ImageIcon COPY_ADDRESS_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/copy_address_button_pressed_icon.png");

    public static final ImageIcon SEND_TRANSACTION_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/send_transaction_button_icon.png");

    public static final ImageIcon SEND_TRANSACTION_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/send_transaction_button_pressed_icon.png");

    public static final ImageIcon VIEW_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/view_button_icon.png");

    public static final ImageIcon VIEW_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/view_button_pressed_icon.png");

    public static final ImageIcon EXPORT_BUTTON_ICON = new ImageIcon("src/main/resources/images/buttons/export_button_icon.png");

    public static final ImageIcon EXPORT_BUTTON_ICON_ACTIVE = new ImageIcon("src/main/resources/images/buttons/export_button_pressed_icon.png");

    // UI COLOUR CONSTANTS
    public static final Color GREY12 = new Color(31,31, 31);

    public static final Color GREY = new Color(80,80, 80);

    public static final Color DARK_GREY = new Color(60, 60, 60);

    public static final Color WHITE = new Color(255,255, 255);

    public static final Color ENSYNC_BLUE = new Color(0,209, 205);

    public static final Color DARK_RED = new Color(150, 0, 0);
}

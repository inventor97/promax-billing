package com.inventor.viewUtils;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NavButtons {

    private JFXButton teacherBtn;
    private JFXButton paymentBtn;
    private JFXButton casherBtn;
    private JFXButton hisBtn;
    private ImageView teacherIcon;
    private ImageView paymentIcon;
    private ImageView casherIcon;
    private ImageView hisIcon;


    public NavButtons(JFXButton teacherBtn, JFXButton paymentBtn, JFXButton casherBtn, JFXButton hisBtn, ImageView teacherIcon, ImageView paymentIcon, ImageView casherIcon, ImageView hisIcon) {
        this.teacherBtn = teacherBtn;
        this.paymentBtn = paymentBtn;
        this.casherBtn = casherBtn;
        this.hisBtn = hisBtn;
        this.teacherIcon = teacherIcon;
        this.paymentIcon = paymentIcon;
        this.casherIcon = casherIcon;
        this.hisIcon = hisIcon;
    }

    public void setFocus(ActionEvent event) {
        if (event.getSource() == teacherBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            teacherBtn.setStyle(styles.NAV_BUTTON);
            teacherIcon.setImage(new Image("data_recovery_96px_fill.png", 30, 30, false, false));
        } else if (event.getSource() == paymentBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            paymentBtn.setStyle(styles.NAV_BUTTON);
            paymentIcon.setImage(new Image("check_fill.png", 30, 30, false, false));
        } else if (event.getSource() == casherBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            casherBtn.setStyle(styles.NAV_BUTTON);
            casherIcon.setImage(new Image("member_96px_fill.png", 30, 30, false, false));
        } else if (event.getSource() == hisBtn) {
            setTransparentBkg();
            setDefaultNavBtnIcons();
            hisBtn.setStyle(styles.NAV_BUTTON);
            hisIcon.setImage(new Image("order_history_96px _fill.png", 30, 30, false, false));
        }
    }

    public void setTransparentBkg() {
        teacherBtn.setStyle("-fx-background-color: transparent");
        paymentBtn.setStyle("-fx-background-color: transparent");
        casherBtn.setStyle("-fx-background-color: transparent");
        hisBtn.setStyle("-fx-background-color: transparent");
    }

    public void setDefaultNavBtnIcons() {
        teacherIcon.setImage(new Image("data_recovery_96px.png", 30,30,false, false));
        paymentIcon.setImage(new Image("check.png", 30,30,false, false));
        casherIcon.setImage(new Image("member_96px.png", 30,30,false, false));
        hisIcon.setImage(new Image("order_history_96px.png", 30,30,false, false));
    }
}

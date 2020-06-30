package com.huli.jformatter.intellij.ui;

import com.github.wnameless.json.flattener.FlattenMode;
import com.huli.jformatter.intellij.config.Constant;
import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.VariableKind;
import org.apache.http.util.TextUtils;
import com.huli.jformatter.intellij.config.Config;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton fieldPublicRadioButton;
    private JRadioButton fieldPrivateRadioButton;
    private JCheckBox arrayFromDataCB;
    private JCheckBox objectFromData1CB;
    private JCheckBox objectFromDataCB;
    private JButton object1Button;
    private JButton objectButton;
    private JButton arrayButton;
    private JButton array1Button;
    private JCheckBox arrayFromData1CB;
    private JTextField suffixEdit;
    private JCheckBox reuseEntityCB;
    private JRadioButton gsonJRB;
    private JRadioButton jackRB;
    private JRadioButton fastJsonRB;
    private JRadioButton otherRB;
    private JRadioButton loganSquareCB;
    private JRadioButton autoValueRadioButton;
    private JRadioButton lombokRB;
    private JTextField annotationFT;
    private JCheckBox virgoModelCB;
    private JTextField filedPrefixTF;
    private JCheckBox filedPrefixCB;
    private JCheckBox generateCommentsCT;
    private JCheckBox splitGenerateMode;
    private JCheckBox useSerializedNameCheckBox;
    private JCheckBox useWrapperClassCB;
    private String annotaionStr;
    private JRadioButton flattenNormalJRB;
    private JRadioButton flattenKeepArraysJRB;
    private JRadioButton flattenMongoDbJRB;
    private JRadioButton flattenKeepPrimitiveArraysJRB;

    public SettingsDialog(Project project) {
        setContentPane(contentPane);
//        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setAlwaysOnTop(true);
        setTitle("Setting");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        if (Config.getInstant().isFieldPrivateMode()) {
            fieldPrivateRadioButton.setSelected(true);
        } else {
            fieldPublicRadioButton.setSelected(true);
        }

        virgoModelCB.setSelected(Config.getInstant().isVirgoMode());
        generateCommentsCT.setSelected(Config.getInstant().isGenerateComments());
        filedPrefixCB.setSelected(Config.getInstant().isUseFieldNamePrefix());
        filedPrefixTF.setEnabled(Config.getInstant().isUseFieldNamePrefix());
        useSerializedNameCheckBox.setSelected(Config.getInstant().isUseSerializedName());
        objectFromDataCB.setSelected(Config.getInstant().isObjectFromData());
        objectFromData1CB.setSelected(Config.getInstant().isObjectFromData1());
        arrayFromDataCB.setSelected(Config.getInstant().isArrayFromData());
        arrayFromData1CB.setSelected(Config.getInstant().isArrayFromData1());
        reuseEntityCB.setSelected(Config.getInstant().isReuseEntity());
        objectButton.setEnabled(objectFromDataCB.isSelected());
        object1Button.setEnabled(objectFromData1CB.isSelected());
        arrayButton.setEnabled(arrayFromDataCB.isSelected());
        array1Button.setEnabled(arrayFromData1CB.isSelected());
        suffixEdit.setText(Config.getInstant().getSuffixStr());
        splitGenerateMode.setSelected(Config.getInstant().isSplitGenerate());
        useWrapperClassCB.setSelected(Config.getInstant().isUseWrapperClass());
        objectFromDataCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                objectButton.setEnabled(objectFromDataCB.isSelected());
            }
        });
        objectFromData1CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                object1Button.setEnabled(objectFromData1CB.isSelected());
            }
        });
        arrayFromDataCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                arrayButton.setEnabled(arrayFromDataCB.isSelected());
            }
        });
        arrayFromData1CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                array1Button.setEnabled(arrayFromData1CB.isSelected());
            }
        });
        filedPrefixCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                filedPrefixTF.setEnabled(filedPrefixCB.isSelected());
            }
        });
        otherRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                annotationFT.setText("@{filed}");
                annotationFT.setEnabled(otherRB.isSelected());
                objectFromDataCB.setEnabled(false);
                objectFromData1CB.setEnabled(false);
                arrayFromDataCB.setEnabled(false);
                arrayFromData1CB.setEnabled(false);
                objectFromDataCB.setSelected(false);
                objectFromData1CB.setSelected(false);
                arrayFromDataCB.setSelected(false);
                arrayFromData1CB.setSelected(false);
                objectButton.setEnabled(false);
                object1Button.setEnabled(false);
                arrayButton.setEnabled(false);
                array1Button.setEnabled(false);

            }
        });
        loganSquareCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (loganSquareCB.isSelected()) {
                    annotationFT.setText(Constant.loganSquareAnnotation);
                }
                annotationFT.setEnabled(otherRB.isSelected());
                objectFromDataCB.setEnabled(false);
                objectFromData1CB.setEnabled(false);
                arrayFromDataCB.setEnabled(false);
                arrayFromData1CB.setEnabled(false);
                objectFromDataCB.setSelected(false);
                objectFromData1CB.setSelected(false);
                arrayFromDataCB.setSelected(false);
                arrayFromData1CB.setSelected(false);
                objectButton.setEnabled(false);
                object1Button.setEnabled(false);
                arrayButton.setEnabled(false);
                array1Button.setEnabled(false);
            }
        });
        lombokRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (lombokRB.isSelected()) {
                    annotationFT.setText(Constant.lombokAnnotation);
                }
                annotationFT.setEnabled(otherRB.isSelected());
                objectFromDataCB.setEnabled(false);
                objectFromData1CB.setEnabled(false);
                arrayFromDataCB.setEnabled(false);
                arrayFromData1CB.setEnabled(false);
                objectFromDataCB.setSelected(false);
                objectFromData1CB.setSelected(false);
                arrayFromDataCB.setSelected(false);
                arrayFromData1CB.setSelected(false);
                objectButton.setEnabled(false);
                object1Button.setEnabled(false);
                arrayButton.setEnabled(false);
                array1Button.setEnabled(false);
            }
        });
        String filedPrefix = null;
        filedPrefix = Config.getInstant().getFiledNamePreFixStr();
        if (TextUtils.isEmpty(filedPrefix)) {
            JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
            filedPrefix = styleManager.getPrefixByVariableKind(VariableKind.FIELD
            );
        }
        filedPrefixTF.setText(filedPrefix);
        gsonJRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (gsonJRB.isSelected()) {
                    annotationFT.setText(Constant.gsonAnnotation);
                }
                objectFromDataCB.setEnabled(true);
                objectFromData1CB.setEnabled(true);
                arrayFromDataCB.setEnabled(true);
                arrayFromData1CB.setEnabled(true);
                annotationFT.setEnabled(false);
            }
        });
        fastJsonRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (fastJsonRB.isSelected()) {
                    annotationFT.setText(Constant.fastAnnotation);
                }
                objectFromDataCB.setEnabled(false);
                objectFromData1CB.setEnabled(false);
                arrayFromDataCB.setEnabled(false);
                arrayFromData1CB.setEnabled(false);
                annotationFT.setEnabled(false);
                objectFromDataCB.setSelected(false);
                objectFromData1CB.setSelected(false);
                arrayFromDataCB.setSelected(false);
                arrayFromData1CB.setSelected(false);
                objectButton.setEnabled(false);
                object1Button.setEnabled(false);
                arrayButton.setEnabled(false);
                array1Button.setEnabled(false);
            }
        });
        jackRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jackRB.isSelected()) {
                    annotationFT.setText(Constant.jackAnnotation);
                }
                annotationFT.setEnabled(false);
                objectFromDataCB.setEnabled(false);
                objectFromData1CB.setEnabled(false);
                arrayFromDataCB.setEnabled(false);
                arrayFromData1CB.setEnabled(false);
                annotationFT.setEnabled(false);
                objectFromDataCB.setSelected(false);
                objectFromData1CB.setSelected(false);
                arrayFromDataCB.setSelected(false);
                arrayFromData1CB.setSelected(false);
                objectButton.setEnabled(false);
                object1Button.setEnabled(false);
                arrayButton.setEnabled(false);
                array1Button.setEnabled(false);
            }
        });
        autoValueRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoValueRadioButton.isSelected()) {
                    annotationFT.setText(Constant.autoValueAnnotation);
                }
            }
        });


        annotaionStr = Config.getInstant().getAnnotationStr();
        if (annotaionStr.equals(Constant.gsonAnnotation)) {
            gsonJRB.setSelected(true);
            annotationFT.setEnabled(false);
        } else if (annotaionStr.equals(Constant.fastAnnotation)) {
            fastJsonRB.setSelected(true);
            annotationFT.setEnabled(false);
        } else if (annotaionStr.equals(Constant.jackAnnotation)) {
            jackRB.setSelected(true);
            annotationFT.setEnabled(false);
        } else if (annotaionStr.equals(Constant.loganSquareAnnotation)) {
            loganSquareCB.setSelected(true);
            annotationFT.setEnabled(false);
        } else if (annotaionStr.equals(Constant.autoValueAnnotation)) {
            autoValueRadioButton.setSelected(true);
            annotationFT.setEnabled(false);
        } else {
            otherRB.setSelected(true);
            annotationFT.setEnabled(true);
        }
        annotationFT.setText(annotaionStr);
        objectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditDialog editDialog = new EditDialog(EditDialog.Type.OBJECT_FROM_DATA);
                editDialog.setSize(600, 360);
                editDialog.setLocationRelativeTo(null);
                editDialog.setResizable(false);
                editDialog.setVisible(true);
            }
        });
        object1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditDialog editDialog = new EditDialog(EditDialog.Type.OBJECT_FROM_DATA1);
                editDialog.setSize(600, 360);
                editDialog.setLocationRelativeTo(null);
                editDialog.setResizable(false);
                editDialog.setVisible(true);
            }
        });
        arrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditDialog editDialog = new EditDialog(EditDialog.Type.ARRAY_FROM_DATA);
                editDialog.setSize(600, 600);
                editDialog.setLocationRelativeTo(null);
                editDialog.setResizable(false);
                editDialog.setVisible(true);
            }
        });
        array1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditDialog editDialog = new EditDialog(EditDialog.Type.ARRAY_FROM_DATA1);
                editDialog.setSize(600, 600);
                editDialog.setLocationRelativeTo(null);
                editDialog.setResizable(false);
                editDialog.setVisible(true);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Config.getInstant().setFieldPrivateMode(fieldPrivateRadioButton.isSelected());
        Config.getInstant().setUseSerializedName(useSerializedNameCheckBox.isSelected());
        Config.getInstant().setArrayFromData(arrayFromDataCB.isSelected());
        Config.getInstant().setArrayFromData1(arrayFromData1CB.isSelected());
        Config.getInstant().setObjectFromData(objectFromDataCB.isSelected());
        Config.getInstant().setObjectFromData1(objectFromData1CB.isSelected());
        Config.getInstant().setReuseEntity(reuseEntityCB.isSelected());
        Config.getInstant().setSuffixStr(suffixEdit.getText());
        Config.getInstant().setVirgoMode(virgoModelCB.isSelected());
        Config.getInstant().setGenerateComments(generateCommentsCT.isSelected());
        Config.getInstant().setFiledNamePreFixStr(filedPrefixTF.getText());
        Config.getInstant().setAnnotationStr(annotationFT.getText());
        Config.getInstant().setUseFieldNamePrefix(filedPrefixCB.isSelected());
        Config.getInstant().setSplitGenerate(splitGenerateMode.isSelected());
        Config.getInstant().setUseWrapperClass(useWrapperClassCB.isSelected());
        if (flattenKeepArraysJRB.isSelected()) {
            Config.getInstant().setFlattenMode(FlattenMode.KEEP_ARRAYS);
        } else if (flattenMongoDbJRB.isSelected()) {
            Config.getInstant().setFlattenMode(FlattenMode.MONGODB);
        } else if (flattenKeepPrimitiveArraysJRB.isSelected()) {
            Config.getInstant().setFlattenMode(FlattenMode.KEEP_PRIMITIVE_ARRAYS);
        }
        Config.getInstant().save();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}

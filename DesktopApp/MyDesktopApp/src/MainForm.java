import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {

    private JPanel mainPanel;
    private JPanel familyPanel;
    private JTextArea textFamily;
    private JPanel namePanel;
    private JTextArea textName;
    private JPanel middleNamePanel;
    private JTextArea textMiddleName;
    private JPanel fNMnPanel;
    private JTextArea textFNMn;
    private JButton buttonCollapse;
    private JButton buttonExpand;
    private JPanel expandButtonPanel;
    private JPanel collapseButtonPanel;
    private JLabel FNMn;

    public MainForm(){
        buttonCollapse.addActionListener(new Action() {

            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (col()){
                    collapseButtonPanel.setVisible(false);
                    familyPanel.setVisible(false);
                    namePanel.setVisible(false);
                    middleNamePanel.setVisible(false);
                    fNMnPanel.setVisible(true);
                    expandButtonPanel.setVisible(true);
                }
            }
        });
        buttonExpand.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                familyPanel.setVisible(true);
                namePanel.setVisible(true);
                middleNamePanel.setVisible(true);
                fNMnPanel.setVisible(false);
                expandButtonPanel.setVisible(false);
                collapseButtonPanel.setVisible(true);

            }
        });

    }

    public boolean col(){
        if(!textName.getText().isEmpty() && !textFamily.getText().isEmpty()){
            textFNMn.setText(textFamily.getText() + " " + textName.getText() + " " + textMiddleName.getText());
            return true;
        }
        JOptionPane.showMessageDialog(
                mainPanel,
                "Введите данные пользователя",
                "",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

}

package Main;

import Lib.Login;
import Lib.XFile;
import Model.Drink;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class Cafemain extends JFrame{
    private JPanel MainPanel;
    private JTextField textID;
    private JTextField textName;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButtion;
    private JTable tbCan;
    private JComboBox cbType;
    private JRadioButton rdYes;
    private JRadioButton rdNo;
    private JTextField textPrice;

    //abc
    DefaultTableModel tbModel;
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    ArrayList<Drink> canList;
    String filePath = "./src/Data/cand.dat";
    int currentRow = -1;
JFrame frontScreen;
    public Cafemain(String title, Login aThis) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        frontScreen = aThis;

        //2. First load Combobox, table
        initTable();
        loadCb();
        tbCan.setDefaultEditor(Object.class, null);


        //load data file(in your project)
        canList = new ArrayList<>();
        boolean file = loadFile();
        if (file) {
            fillToTable();
        } else {
            showMess("No Infomation to show");
        }
        this.setLocationRelativeTo(null);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCan();
            }
        });
        tbCan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbCan.getSelectedRow();
                showDetail(currentRow);
            }

            private void showDetail(int currentRow) {
                Drink c = canList.get(currentRow);
                String id = c.getID();
                textID.setText(id);
                String name = c.getName();
                textName.setText(name);
            }
        });
        tbCan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbCan.getSelectedRow();
                showDetail(currentRow);
            }

            private void showDetail(int currentRow) {
                Drink c = canList.get(currentRow);
                String id = c.getID();
                textID.setText(id);
                String name = c.getName();
                textName.setText(name);
                String Type = c.getType();
                cbType.setSelectedItem(Type);
                Boolean Status = c.getStatus();
                rdYes.setSelected(Status);
                rdNo.setSelected(!Status);


            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateList();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteList();
            }
        });
        exitButtion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exit();
            }
        });
    }
   private void Exit() {
       int re = JOptionPane.showConfirmDialog(this,"Do you wanna exit?","Exit",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.WARNING_MESSAGE);

       if(re == JOptionPane.YES_OPTION){
           System.exit(0);
           this.dispose();
           frontScreen.setVisible(true);
       }
   }
    private void deleteList() {
        deleteCan();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList candidate
        saveFile();
    }

    private void deleteCan() {
        int re =JOptionPane.showConfirmDialog(this, ""+ "do you want to delete this one?", "Delete Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(re==JOptionPane.YES_OPTION){
            canList.remove(currentRow);
            resetForm();
        }
    }
    private void resetForm(){
        textID.setText("");
        textName.setText("");
        textPrice.setText("");
        cbType.setSelectedIndex(0);
        rdYes.isSelected();
        rdNo.isSelected();
    }
    private void updateList(){
        //1.add new candidate to arraylist
        updateCan();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList candidate
        saveFile();
    }

    private void updateCan(){
        Drink c = canList.get(currentRow);
        String id = textID.getText();
        c.setID(id);
        String name = textName.getText();
        c.setName(name);
        Double price = Double.parseDouble(textPrice.getText());
        c.setPrice(price);
        Boolean status = rdYes.isSelected();
        c.setStatus(status);
        String Type =cbType.getSelectedItem().toString();
        c.setType(Type);
    }

    private void addCan() {
        //1.add new Cafemain to arraylist
        addToLish();
        //2.fill to table (renew table)
        fillToTable();
        //3.Save arrayList Cafemain
        saveFile();
    }

    private void saveFile() {
        XFile.writeObject(filePath, canList);
    }

    private void addToLish() {
        String id = textID.getText();
        String name = textName.getText();
        String Type =cbType.getSelectedItem().toString();
        Boolean Status = rdYes.isSelected();
        Double price = Double.parseDouble(textPrice.getText());
        Drink c = new Drink( id, name, Type, Status, price);
        canList.add(c);
    }

    private void fillToTable(){
        //Clear old date in the table
        tbModel.setRowCount(0);
        for(Drink c : canList){
            Object[] row = new Object[]{
                    c.getID(), c.getName(), c.getType(), c.getStatus(), c.getPrice(),
            };
            tbModel.addRow(row);
        }
    }

    private boolean loadFile() {
        if(XFile.readObject(filePath)==null){
            return false;
        }
        canList = (ArrayList<Drink>) XFile.readObject(filePath);
        return true;
    }

    private void loadCb() {
        String[] TypeLst = {"Choose your Type","COFFEE","FRUIT JUICE","SMOOTHIE"};
        for(String s:TypeLst){
            cbModel.addElement(s);
        }
        cbType.setModel(cbModel);
    }

    private void initTable() {
        String[] columnNames ={"ID","Name","Type","Status","Price"};
        tbModel = new DefaultTableModel(columnNames,0);
        tbCan.setModel(tbModel);
    }
    private void showMess(String mess) {
        JOptionPane.showMessageDialog(Cafemain.this,mess);
    }

    public static void main(String[] args) {
        Cafemain c = new Cafemain("Drink Management",new Login(""));
        c.setVisible(true);
        c.setLocationRelativeTo(null);
    }

}


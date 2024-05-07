/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jakubjagla.Simulation;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Jake
 */
public class guiMain extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    private boolean guistart = false;
    private static Simulation s;

    private String sessionFilePath = null;
    
    private ArrayList<String> userNameArray = new ArrayList<String>();
    private ArrayList<User> usersDisplayed = new ArrayList<User>();
    
    private ArrayList<String>  contentNameArray =  new ArrayList<String>();
    private ArrayList<Watchable> contentDisplayed = new ArrayList<Watchable>();
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                guiMain m = new guiMain();
                m.setVisible(true);
                s = new Simulation();
                s.startSimulation();
                m.startGUI();
                
            }
        });      
        
    }
    
    public guiMain() {
        try {
            try {
                UIManager.setLookAndFeel(
                        new FlatMacDarkLaf());
            }catch(Exception e){
                System.out.println("Somehow failed to set LaF.");
            }
            initComponents();
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    s.stopSimulation();
                }
            });
            
        }catch(Exception ex){
            Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startGUI(){
        /*
        Starts loop responsible for updating GUI elements.
        Calling more than once won't do anything.
        */
        if(this.guistart){return;}
        this.guistart = true;
        
        this.updateUsernames();
        this.updateContent();
        Thread t = new Thread(() -> this.runGUI());
        t.start();
    }
    
    private void runGUI(){
        /*
        update gui elements every 50 milis
        */
        while(true){
            try {
                Thread.sleep(50);
                this.updateGUI();
            } catch (InterruptedException ex) {
                    ex.printStackTrace();
            }
            
         }
    }

    private void updateGUI(){
        this.updateUsernames();
        this.updateContent();
        this.infoPanel1.refresh();
    }
    
    private synchronized void updateUsernames(){
        String usearch = userSearch.getText().toLowerCase();
        
        ArrayList<String> olddisplay = new ArrayList<String>(this.userNameArray);
        this.userNameArray = new ArrayList<String>();
        this.usersDisplayed = new ArrayList<User>();
        
        for(User u : s.getUsers()){
            String uname = u.getUsername();
            if(String.format("type:%s %s", (u instanceof Channel)? "Channel": "User",uname).toLowerCase().contains(usearch)){
                this.userNameArray.add(uname);
                this.usersDisplayed.add(u);
            }
        }
        if(!(olddisplay.containsAll(this.userNameArray) && this.userNameArray.containsAll(olddisplay))){
            
            this.fillJListWithNewValues(userList,this.userNameArray);
        }
    }
    
    private synchronized void updateContent(){
        String wsearch = videoSearch.getText().toLowerCase();
        
        ArrayList<String> olddisplay = new ArrayList<String>(this.contentNameArray);
        this.contentNameArray = new ArrayList<String>();
        this.contentDisplayed = new ArrayList<Watchable>();
        
        for(Watchable w : s.getAllWatchables()){
            String wname = w.getName();
            if(wname != null && String.format("%s %s",w.getCreator().getUsername(),wname).toLowerCase().contains(wsearch)){
                this.contentNameArray.add(String.format("%s",wname));
                this.contentDisplayed.add(w);
            }
        }
        if(!(olddisplay.containsAll(this.contentNameArray) && this.contentNameArray.containsAll(olddisplay))){
            
            this.fillJListWithNewValues(videoList,this.contentNameArray);
        }
    }
    
    private synchronized void fillJListWithNewValues(javax.swing.JList list, ArrayList<String> values){
        String[] s = new String[values.size()];
        s = values.toArray(s);
        final String[] fs = s;
        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = fs;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadFileChooser = new javax.swing.JFileChooser();
        saveFileChooser = new javax.swing.JFileChooser();
        content = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        lists = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        userSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        videoSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        videoList = new javax.swing.JList<>();
        infoPanel1 = new com.jakubjagla.Simulation.infoPanel();
        bottomBar = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        speedSlider = new javax.swing.JSlider();
        speedText = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();

        loadFileChooser.setDialogTitle("Choose simulation file to load...");
        loadFileChooser.setFileFilter(null);

        saveFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveFileChooser.setDialogTitle("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Youtube (but better)");
        setPreferredSize(new java.awt.Dimension(1000, 800));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        content.setRequestFocusEnabled(false);
        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.LINE_AXIS));

        lists.setForeground(new java.awt.Color(255, 102, 102));
        lists.setMinimumSize(new java.awt.Dimension(200, 200));
        lists.setName(""); // NOI18N
        lists.setOpaque(true);
        lists.setPreferredSize(new java.awt.Dimension(200, 680));

        jPanel1.setMinimumSize(new java.awt.Dimension(64, 212));
        jPanel1.setPreferredSize(new java.awt.Dimension(128, 213));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        userSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userSearch.setToolTipText("Search");
        userSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        userSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userSearch.setDoubleBuffered(true);
        userSearch.setMaximumSize(new java.awt.Dimension(50000, 16));
        userSearch.setMinimumSize(new java.awt.Dimension(68, 12));
        jPanel1.add(userSearch);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(25, 200));

        userList.setForeground(new java.awt.Color(153, 204, 255));
        userList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        userList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                userListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(userList);

        jPanel1.add(jScrollPane1);

        lists.addTab("Users", jPanel1);

        jPanel3.setPreferredSize(new java.awt.Dimension(128, 213));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        videoSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        videoSearch.setToolTipText("Search");
        videoSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        videoSearch.setMaximumSize(new java.awt.Dimension(50000, 16));
        videoSearch.setMinimumSize(new java.awt.Dimension(68, 12));
        jPanel3.add(videoSearch);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(22, 200));

        videoList.setForeground(new java.awt.Color(153, 204, 255));
        videoList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        videoList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                videoListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(videoList);

        jPanel3.add(jScrollPane3);

        lists.addTab("Content", jPanel3);

        jSplitPane1.setLeftComponent(lists);
        lists.getAccessibleContext().setAccessibleName("Users");

        jSplitPane1.setRightComponent(infoPanel1);

        content.add(jSplitPane1);

        getContentPane().add(content);

        bottomBar.setMaximumSize(new java.awt.Dimension(50000000, 37));
        bottomBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        startButton.setText("Start new");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        bottomBar.add(startButton);

        stopButton.setText("Terminate");
        stopButton.setActionCommand("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        bottomBar.add(stopButton);

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        bottomBar.add(pauseButton);

        jLabel1.setText("Speed:");
        bottomBar.add(jLabel1);

        speedSlider.setMajorTickSpacing(1);
        speedSlider.setMaximum(8);
        speedSlider.setMinimum(1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setValue(4);
        speedSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        speedSlider.setFocusCycleRoot(true);
        speedSlider.setMinimumSize(new java.awt.Dimension(50, 22));
        speedSlider.setName(""); // NOI18N
        speedSlider.setPreferredSize(new java.awt.Dimension(100, 22));
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        speedSlider.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                speedSliderMouseWheelMoved(evt);
            }
        });
        bottomBar.add(speedSlider);

        speedText.setText("1x");
        bottomBar.add(speedText);

        getContentPane().add(bottomBar);
        bottomBar.getAccessibleContext().setAccessibleName("");

        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 102, 102));

        jMenu1.setForeground(new java.awt.Color(255, 51, 102));
        jMenu1.setText("File");
        jMenu1.setToolTipText("");

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);

        saveAsMenuItem.setText("Save as");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveAsMenuItem);

        loadMenuItem.setText("Load");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(loadMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        s.stopSimulation();
        s = new Simulation();
        s.startSimulation();
    }//GEN-LAST:event_startButtonActionPerformed

    private void pauseSim(){
        s.pause();
        pauseButton.setText("Unpause");
    }
    private void unpauseSim(){
        s.unpause();
        pauseButton.setText("Pause");
    }
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        if(s.isPaused()){
            this.unpauseSim();
        }
        else{
            this.pauseSim();
        }
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        s.stopSimulation();
        try {
            s.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        speedText.setText(String.format("%.2fx",speedSlider.getValue()/4.0));
        s.setSpeed(speedSlider.getValue()/4.0);
    }//GEN-LAST:event_speedSliderStateChanged

    private void speedSliderMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_speedSliderMouseWheelMoved
        speedSlider.setValue(speedSlider.getValue() + -1*evt.getUnitsToScroll()/Math.abs(evt.getUnitsToScroll()));
    }//GEN-LAST:event_speedSliderMouseWheelMoved
    
    
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        if(this.sessionFilePath != null){
            try {
                Simulation.SaveSimulation(s, sessionFilePath);
            } catch (IOException ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            this.saveSimAs();
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        this.saveSimAs();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed
    
    private void saveSimAs(){
        int path = saveFileChooser.showDialog(this, "Save");
        if(path == javax.swing.JFileChooser.APPROVE_OPTION){
            File f = saveFileChooser.getSelectedFile();
            String absoluteFilePath = f.getAbsolutePath();
            try {
                Simulation.SaveSimulation(s, absoluteFilePath);
                this.sessionFilePath = absoluteFilePath;
                saveMenuItem.setText(String.format("Save to %s",absoluteFilePath));
            } catch (IOException ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        int path = loadFileChooser.showDialog(this, "Open");
        if(path == javax.swing.JFileChooser.APPROVE_OPTION){
            File f = loadFileChooser.getSelectedFile();
            String absoluteFilePath = f.getAbsolutePath();
            try {
                s.stopSimulation();
                s = Simulation.LoadSimulation(absoluteFilePath);
                this.sessionFilePath = absoluteFilePath;
                saveMenuItem.setText(String.format("Save to %s",absoluteFilePath));
                s.startSimulation();
                this.pauseSim();
                speedSlider.setValue(4);
            } catch (IOException ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void userListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_userListValueChanged
        int idx = userList.getSelectedIndex();
        if(idx >= 0&& idx < this.usersDisplayed.size()){
            try {
                infoPanel1.setInfoObject((Object) this.usersDisplayed.get(idx));
                this.userList.repaint();
            } catch (Exception ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_userListValueChanged

    private void videoListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_videoListValueChanged
        int idx = videoList.getSelectedIndex();
        if(idx >= 0 && idx < this.contentDisplayed.size()){
            try {
                infoPanel1.setInfoObject((Object) this.contentDisplayed.get(idx));
            } catch (Exception ex) {
                Logger.getLogger(guiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_videoListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomBar;
    private javax.swing.JPanel content;
    private com.jakubjagla.Simulation.infoPanel infoPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane lists;
    private javax.swing.JFileChooser loadFileChooser;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JButton pauseButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JFileChooser saveFileChooser;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JLabel speedText;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JList<String> userList;
    private javax.swing.JTextField userSearch;
    private javax.swing.JList<String> videoList;
    private javax.swing.JTextField videoSearch;
    // End of variables declaration//GEN-END:variables
}
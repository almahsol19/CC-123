
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.HashMap;

import java.util.Set;
import java.util.StringTokenizer;  
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Christian Jode
 */
public class chat_server extends javax.swing.JFrame {

    /**
     * Creates new form chat_server
     */
    ServerSocket ss;
    HashMap clientColl= new HashMap();
    public chat_server() {
        try{
        initComponents();
        ss=new ServerSocket(2089);
            this.sStatus.setText("Server Started. ");
            
            new ClientAccept(). start();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
     }
    class ClientAccept extends Thread{
        public void run(){
            while(true){
                try{
                    Socket s=ss.accept();
                  String i=new DataInputStream(s.getInputStream()).readUTF();
                  if(clientColl.containsKey(i)){
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                      dout.writeUTF("You are already Registered....!!");
                  }else{
                      clientColl.put(i,s);
                   msgBox.append(i+" Joined! \n");
                   DataOutputStream dout=new DataOutputStream(s.getOutputStream());        
                    dout.writeUTF("");
                        new MsgRead(s,i).start();
                    new PrepareClientList().start();
                    }
                }catch (Exception ex)
                {
                   ex.printStackTrace();
                }
            }
        }
    }
    class MsgRead extends Thread{
        Socket s;
        String ID;
        MsgRead(Socket s, String i) {
            this.s = s;
            this.ID = i;
            
        }
        public void run(){
           while(!clientColl.isEmpty()){
               try{
                   String i=new DataInputStream(s.getInputStream()).readUTF();
                   if(i.equals("qwertyuioplkjhgfdsazxcvbnm096785542AXTY")){
                      clientColl.remove(ID); 
                       msgBox.append(ID+": removed! \n");
                       new PrepareClientList().start();
                       Set<String> k = clientColl.keySet();
                               
                       Iterator itr = k.iterator();
                       while(itr.hasNext()){ 
                           String key = (String) itr.next();
                        if(!key.equalsIgnoreCase(ID)){
                            try{
                            new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF("");
                            
                            } catch(Exception ex){
                                clientColl.remove(key);
                                msgBox.append(key+": removed!");
                                new PrepareClientList().start();
                            }
                            
                    }    
                    }
               }  
                else if(i.contains("#4344554@@@@67667@@")){  
                      i=i.substring(20);
                       StringTokenizer st=new StringTokenizer(i, ":");
                       String id=st.nextToken();
                       i=st.nextToken();
                     try{
                         new DataOutputStream(((Socket)clientColl.get(id)).getOutputStream()).writeUTF("< "+ID+" to "+id+" > "+i);
                         }catch (Exception ex) {
                            clientColl.remove(ID);
                            msgBox.append(id+ ": removed!");
                            new PrepareClientList().start();
                             
                             
                         }
                   
                   
                   
                    }
                     else{
                    Set k = clientColl.keySet();
                    Iterator itr = k.iterator();
                    while(itr.hasNext()) {
                        String key=(String)itr.next();
                        if(!key.equalsIgnoreCase(ID)) {
                            try{
                             new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF("< "+ID+" to All  > ");    
                            }catch (Exception ex) {
                                clientColl.remove(key);
                                msgBox.append(key+" : removed!");
                                new PrepareClientList().start();
                                
                            }}
                            
                            
                        }
                     
                    }
                    
            
               }catch(Exception ex){
                  ex.printStackTrace();
               }
           }        
        } 

        private void writeUTF(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
   
    }
    class PrepareClientList extends Thread{
         
        public void run(){
          try{
              String ids="";
              Set k=clientColl.keySet();
              Iterator itr=k.iterator();
              while(itr.hasNext()){
                  String key= (String)itr.next();
                  ids+=key+",";
              }
              if(ids.length()!=0)
                  ids=ids.substring(0, ids.length()-1);
                  
                itr=k.iterator();
              while(itr.hasNext()){
                  String key= (String)itr.next();
                  try{
                    new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF(":;.,/=" +ids);         
                  }catch (Exception ex) {
                    clientColl.remove(key);
                    msgBox.append(key+" : removed!");
                  
                  }
                  
                         
              }    
                  
                  
          }catch (Exception ex)
          {   
            ex.printStackTrace();
          }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        label1 = new javax.swing.JLabel();
        sStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyServer");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        msgBox.setColumns(20);
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        label1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label1.setText("Server Status");

        sStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sStatus.setText(".........................................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGap(26, 26, 26)
                        .addComponent(sStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(sStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat_server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JTextArea msgBox;
    private javax.swing.JLabel sStatus;
    // End of variables declaration//GEN-END:variables
}

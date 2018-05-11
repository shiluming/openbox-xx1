package com.zhihu.openbox;



import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Adam
 */
public class RuoKuaiTest extends javax.swing.JFrame {

    public DocumentBuilderFactory dbf;
    public DocumentBuilder db;
    /**
     * Creates new form RuoKuaiTest
     */
    public RuoKuaiTest() {
        dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabelImage = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jTextField6.setText("http://captcha.qq.com/getimage");
        jLabel1.setText("软件ID");

        jLabel2.setText("软件Key");

        jLabel3.setText("题目类型");

        jLabel4.setText("用户名");

        jLabel5.setText("密码");

        jTextField1.setText("b40ffbee5c1cf4e38028c197eb2fc751");

        jTextField2.setText("1");

        jTextField3.setText("2040");

        jLabelImage.setText("--");

        jButton1.setText("上传答题");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("图片地址");

        jLabel7.setText("图片ID");

        jButton7.setText("单线程测试");
        jButton2.setText("多线程测试");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton3.setText("报错提交");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("注册:");

        jLabel9.setText("用户名");

        jLabel10.setText("密码:");

        jLabel11.setText("邮箱:");

        jButton4.setText("提交");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("查询账户信息");

        jButton5.setText("查询");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setText("充值:");

        jLabel14.setText("卡号");

        jLabel15.setText("密码");

        jButton6.setText("充值");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(10);
        jTextArea1.setAutoscrolls(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(1, 1, 1))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTextField2))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextField3))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField4)
                                                                        .addComponent(jTextField5))))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jButton1).addComponent(jButton7).addGap(18, 18, 18)
                                                        )


                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel7))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                                                        .addComponent(jTextField7).addComponent(jLabelImage))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton2)
                                                                        .addComponent(jButton3))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(jLabel14)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jTextField11))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                                .addComponent(jLabel12)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(jButton5))
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                                .addComponent(jLabel9)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel10)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel11)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jButton4))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel15)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jButton6)))
                                                                .addGap(0, 9, Short.MAX_VALUE))
                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel13))
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1).addComponent(jButton7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelImage))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jButton5))
                                .addGap(26, 26, 26)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(jButton6)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    // 注册
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String username = jTextField8.getText();
        String password = jTextField9.getText();
        String email = jTextField10.getText();
        jTextArea1.setText("");
        String result = "";
        result = RuoKuai.register(username, password, email);

        this.jTextArea1.setText(result);
        displayXmlResult(result);

    }

    // 答题上传
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String typeid =  jTextField3.getText();

        String username = jTextField4.getText();
        String password = jTextField5.getText();
        jTextArea1.setText("");
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(true);
        int ret = chooser.showOpenDialog(null);
        if(ret == JFileChooser.APPROVE_OPTION)
        {
            String imagePath = chooser.getSelectedFile().getAbsolutePath();
            String result = "";
            result = RuoKuai.createByPost(username, password, typeid, "90", softId, softKey, imagePath);

            this.jTextArea1.setText(result);
            displayXmlResult(result);
        }
    }

    // 答题URL
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // URL
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String typeid =  jTextField3.getText();

        String username = jTextField4.getText();
        String password = jTextField5.getText();

        String imageUrl = jTextField6.getText();
        jTextArea1.setText("");
        String result = "";
        result = RuoKuai.createByUrl(username, password, typeid, "90", softId, softKey, imageUrl);
        this.jTextArea1.setText(result);
        displayXmlResult(result);
    }

    // 上报错题
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String username = jTextField4.getText();
        String password = jTextField5.getText();
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String id = jTextField7.getText();
        jTextArea1.setText("");
        String result = "";
        result = RuoKuai.report(username, password, softId, softKey, id);
        this.jTextArea1.setText(result);
        displayXmlResult(result);
    }

    // 查询用户信息
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String username = jTextField4.getText();
        String password = jTextField5.getText();
        jTextArea1.setText("");
        String result = "";
        result = RuoKuai.getInfo(username, password);
        this.jTextArea1.setText(result);
        if(result.length() <= 0) {
            jTextArea1.append("未知问题");
            return ;
        }
        Document dm;
        try {
            dm = db.parse(new ByteArrayInputStream(result.getBytes("utf-8")));
            NodeList resultNl = dm.getElementsByTagName("Score");
            NodeList idNl = dm.getElementsByTagName("HistoryScore");
            NodeList errorNl = dm.getElementsByTagName("TotalTopic");
            NodeList errorNl1 = dm.getElementsByTagName("Error");

            if(resultNl.getLength() > 0 )
                this.jTextArea1.append(String.format("快豆：%s\r\n", resultNl.item(0).getFirstChild().getNodeValue()));
            if(idNl.getLength()>0)
                this.jTextArea1.append(String.format("历史快豆:%s\r\n", idNl.item(0).getFirstChild().getNodeValue()));
            if(errorNl.getLength()>0)
                this.jTextArea1.append(String.format("答题总数:%s\r\n", errorNl.item(0).getFirstChild().getNodeValue()));
            if(errorNl1.getLength()>0)
                this.jTextArea1.append(String.format("错误:%s\r\n", errorNl1.item(0).getFirstChild().getNodeValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 充值
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String username = jTextField4.getText();
        String id = jTextField11.getText();
        String password = jTextField12.getText();
        jTextArea1.setText("");
        if(username.length() == 0 || password.length() == 0 || id.length() == 0) {
            JOptionPane.showMessageDialog(null, "用户名、卡号、卡密不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = "";
        result = RuoKuai.recharge(username, id, password);
        this.jTextArea1.setText(result);
        displayXmlResult(result);
    }

    // 多线程
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String typeid =  jTextField3.getText();

        String username = jTextField4.getText();
        String password = jTextField5.getText();

        String imageUrl = jTextField6.getText();
        jTextArea1.setText("");
        if(username.length() == 0 || password.length() == 0) {
            JOptionPane.showMessageDialog(null, "用户名密码不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(imageUrl.length() == 0) {
            JOptionPane.showMessageDialog(null, "图片URL不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 加载图片
        BufferedImage image =null;
        try {
            URL url = new URL(imageUrl);
            image = ImageIO.read(url);

            BufferedImage buffered = (BufferedImage) image;
            baos.reset();
            ImageIO.write(buffered,"jpg", baos);

            ImageIcon icon = new ImageIcon(image);
            icon.getImage().flush();
            jLabelImage.setIcon(icon);
        }catch(IOException ex){
        }


        index = 0;
        for(int i=0;i<50;i++) {
            Thread t = new MyThread();
            t.start();

        }

    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String typeid =  jTextField3.getText();

        String username = jTextField4.getText();
        String password = jTextField5.getText();

        String imageUrl = jTextField6.getText();
        jTextArea1.setText("");
        if(username.length() == 0 || password.length() == 0) {
            JOptionPane.showMessageDialog(null, "用户名密码不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(imageUrl.length() == 0) {
            JOptionPane.showMessageDialog(null, "图片URL不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 加载图片
        BufferedImage image =null;
        try {
            URL url = new URL(imageUrl);
            image = ImageIO.read(url);

            BufferedImage buffered = (BufferedImage) image;

            baos.reset();
            ImageIO.write(buffered,"jpg", baos);

            ImageIcon icon = new ImageIcon(image);
            icon.getImage().flush();
            jLabelImage.setIcon(icon);
        }catch(IOException ex){
        }


        String result = "";
        index = 0;
        Thread t = new MyThread();
        t.start();


    }

    int index = 0;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public void test() {
        String softId = jTextField2.getText();
        String softKey = jTextField1.getText();
        String typeid =  jTextField3.getText();

        String username = jTextField4.getText();
        String password = jTextField5.getText();

        String imageUrl = jTextField6.getText();
        imageUrl = "http://captcha.qq.com/getimage";
        String result = "";


        result = RuoKuai.createByPost(username, password, typeid, "90", softId, softKey, baos.toByteArray());
        this.jTextArea1.append("线程" + ++index + "结果：\r\n");
        this.jTextArea1.append(result + "\r\n");

        if(result.length() <= 0) {
            jTextArea1.append("未知问题\r\n");
            return ;
        }
        Document dm;
        try {
            dm = db.parse(new ByteArrayInputStream(result.getBytes("utf-8")));
            NodeList resultNl = dm.getElementsByTagName("Result");
            NodeList idNl = dm.getElementsByTagName("Id");
            NodeList errorNl = dm.getElementsByTagName("Error");

            if(resultNl.getLength() > 0 ) {
                this.jTextArea1.append(String.format("结果：%s\r\n", resultNl.item(0).getFirstChild().getNodeValue()));
                if(idNl.getLength()>0) this.jTextArea1.append(String.format("ID:%s\r\n", idNl.item(0).getFirstChild().getNodeValue()));
            } else if (errorNl.getLength() > 0) {
                jTextArea1.append(String.format("错误：%s\r\n", errorNl.item(0).getFirstChild().getNodeValue()));
            } else {
                jTextArea1.append("未知问题\r\n");
            }


            jTextArea1.append("------------------------------------------------------\r\n\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class MyThread extends Thread{
        public int x = 0;

        public void run(){
            test();

        }
    }

    /**
     * 解析xml结果
     * @param xml xml返回结果字符串
     */
    public void displayXmlResult(String xml) {
        if(xml.length() <= 0) {
            jTextArea1.append("未知问题");
            return ;
        }
        Document dm;
        try {
            dm = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
            NodeList resultNl = dm.getElementsByTagName("Result");
            NodeList idNl = dm.getElementsByTagName("Id");
            NodeList errorNl = dm.getElementsByTagName("Error");

            if(resultNl.getLength() > 0 ) {
                this.jTextArea1.append(String.format("结果：%s\r\n", resultNl.item(0).getFirstChild().getNodeValue()));
                if(idNl.getLength()>0) this.jTextArea1.append(String.format("ID:%s\r\n", idNl.item(0).getFirstChild().getNodeValue()));
            } else if (errorNl.getLength() > 0) {
                jTextArea1.append(String.format("错误：%s\r\n", errorNl.item(0).getFirstChild().getNodeValue()));
            } else {
                jTextArea1.append("未知问题\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
            java.util.logging.Logger.getLogger(RuoKuaiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RuoKuaiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RuoKuaiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RuoKuaiTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RuoKuaiTest().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

package com.company;

public class dome {
    Connectionlain c = new Connectionlain();
    Connection conn = c.getConnect();
    PreparedStatement ps = null;
    ResultSet rs = null;
		          try {
        ps = conn.prepareStatement("select *from userlist where  name=?");
        ps.setString(1, textloginName.getText());
        rs = ps.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "该昵称已存在,请重新填写昵称");
        } else {
            save();
        }
    } catch (SQLException e1) {
        // TODO Auto-generated catch block
        //e1.printStackTrace();
    }
	 finally{
        try {
            rs.close();
            ps.close();
            //    ct.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    list = new JList(listModel);
	   	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 设置单 一选择模式（每次只能有一个元素被选中）
		list.setCellRenderer(new MyCellRenderer(icons));//使用自己的CellRenderer，使用图片功能
    listScroller = new JScrollPane(list);
		listScroller.setBounds(20,300,465,350);//添加带滚动条的list
    //list.setBounds(20,300,450,350);
		listScroller.setBackground(new Color(30, 144, 255));
		listScroller.setOpaque(false);
		listScroller.setBorder(null);
    //this.add(listScroller,BorderLayout.CENTER);//添加带滚动条的list
    //this.add(list);
		this.add(listScroller);

    listModel = new DefaultListModel();
	    try {
        Class.forName("com.mysql.jdbc.Driver");
        //(2)获取数据库连接
        Connection ct=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/check?useUn   icode=true&amp;characterEncoding=utf_8","root", "123");
        //（3）创建SQL语句对象
        Statement ps=ct.createStatement();
        //(4)执行查询，返回结果集
        ResultSet rs=ps.executeQuery("SELECT * FROM userlist2");
        String []data1=new String [1];
        while(rs.next()){
            //if(rs.next()){
            //rowData可以存放多行
            String st1=rs.getString(1);
            data1[0] =rs.getString(1);
            for(int i=0;i<data1.length;i++){
                listModel.add(i, data1[i]);//添加登录的用户到列表框
            }
            System.out.println(data1[0]);
        }} catch (Exception e1) {
        e1.printStackTrace();
    } finally{
        try {
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(ct!=null){
                ct.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String serverIp="127.0.0.1";
        int serverPort=8000;
        //连接服务器，获取套接字io流
        socket=new Socket(serverIp,serverPort);
        dis=new DataInputStream(socket.getInputStream());
        dos=new DataOutputStream(socket.getOutputStream());
        //获取用户名，构建，发送登陆报文
        String   id2="好友"+id1;
        String msgLogin="LOGIN#"+id2;
        dos.writeUTF(msgLogin);
        dos.flush();
        //读取服务器返回的信息，判断是否登录成功
        String response=dis.readUTF();
        //登陆失败
        if(response.equals("FAIL")){
            addMsg("登陆失败");
            System.out.println("登陆失败");
            socket.close();
            return;
        }
        //登陆成功
        if(response.equals("SUCCESS")){
            addMsg("登陆成功");
            isLogged=true;
            btnSend.setEnabled(true);
        }
    }
    public void run(){
        //连接服务器并登录
        try{
            login();
        }catch(IOException e){
            //addMsg("连接服务器时出现异常");
            e.printStackTrace();
            System.out.println("连接服务器时出现异常");
            return;
        }
        while(isLogged){
            try{
                String msg=dis.readUTF();
                String[] parts=msg.split("#");
                //处理服务器发送过来的报文
                if(parts[0].equals("USERLIST")){
                    for(int i=1;i<parts.length;i++){
                    }
                }

                if(parts[0].equals("LOGIN")){
                    addMsg(parts[1]+"上线了");

                }
                if(parts[0].equals("LOGOUT")){
                }
                if(parts[0].equals("TALKTO_ALL")){
                    addMsg(parts[1]+"跟所有人说:"+parts[2]);
                }
                if(parts[0].equals("TALKTO")){
                    addMsg(parts[1]+"跟我说:"+parts[2]);
                }
            }catch(IOException e){
                isLogged=false;
                e.printStackTrace();
            }
        }
    }
}
    //启动线程
    private void start(){
        clientThread=new ClientThread();
        clientThread.start();
    }
    private void addMsg(String msg){
	textAreaRecord.append(msg+"\n");
            //自动滚动到文本区的最后一行
            textAreaRecord.setCaretPosition(textAreaRecord.getText().length());
            new save();
            }
private void addMsg2(String msg){
        textAreaRecord.append("\t"+"\t"+"\t"+"\t"+msg+":"+"用户"+id1+"\n");
        //自动滚动到文本区的最后一行
        textAreaRecord.setCaretPosition(textAreaRecord.getText().length());
        new save()；
        }

class save{
    public save(){
        try {
            // 注册mysql驱动程序
            Class.forName("com.mysql.jdbc.Driver");//driver
            System.out.println("找到Mysql数据库驱动程序");

        } catch (Exception e1) {
            System.out.println("在类路径上找不到Mysql驱动程序，" + "请检查类路径上是否加载mysql的jar包!");
        }
        // (3)获取数据库连接
        Connection conn = null;// 同时按下CTRL+SHIFT+O
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/check?charcterEncoding=utf-8", "root", "123");//(url,username,password)
            System.out.println("建立数据库连接成功");


        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("创建数据库连接失败！");
        }

        //     （4）创建一个SQL语句执行（需要在Java执行SQL语句）
        Statement stmt = null;
        try {
            // 通过conn对象创建SQL语句对象
            stmt = conn.createStatement();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        String sql = "INSERT INTO usermessage2 (message2) VALUES('"+textAreaRecord.getText()+"')";


        try {
            // 执行SQL语句
            stmt.executeUpdate(sql);
            //  JOptionPane op7=new JOptionPane();
            //  op7.showMessageDialog(null, "注册成功");
            System.out.println("数据插入成功");
            //	   	JOptionPane.showMessageDialog(this, "注册成功");

        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("插入失败");
        }

        //(6)关闭资源
        try {
            stmt.close();
            conn.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try{
            //获取serverip和serverPoet
            String serverIp=textServerIP.getText();
            int serverPort=Integer.parseInt(textPort.getText());
            //创建套接字地址
            SocketAddress socketAddress=new InetSocketAddress(serverIp,serverPort);
            //创建ServerSocket，绑定套接字地址
            server=new ServerSocket();
            server.bind(socketAddress);
            //修改判断服务器是否运行的标识变量
            isRunning=true;

            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            addMsg("服务器启动成功");
            System.out.println("服务器启动成功");
        }catch(IOException e){
            System.out.println("服务器启动失败");
            e.printStackTrace();
            isRunning=false;
        }
    }
    public void run(){
        startServer();
        //当服务器处于运行状态时，循环监听客户端的连接请求
        while(isRunning){
            try{
                Socket socket=server.accept();
                //创建与客户端交互的线程
                Thread thread=new Thread(new ClientHandler(socket));
                thread.start();
            }catch(IOException e){
                System.out.println("还没连接");
            }
        }
    }
}

class ClientHandler implements Runnable{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isConnected;
    private String username;
    public ClientHandler(Socket socket){
        this.socket=socket;
        try{
            this.dis=new DataInputStream(socket.getInputStream());
            this.dos=new DataOutputStream(socket.getOutputStream());
            isConnected=true;
        }catch(IOException e){
            isConnected=false;
            e.printStackTrace();
        }
    }
    public void run(){
        while(isRunning&&isConnected){
            try{
                //读取客户端发送的报文
                String msg=dis.readUTF();
                String[] parts=msg.split("#");
                if(parts[0].equals("LOGIN")){
                    String loginUsername=parts[1];
                    Date date3=new Date();
                    SimpleDateFormat f2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String d3=f2.format(date3);
                    String q="用户:"+parts[1]+""+"已登录"+"\t"+d3;
                    addMsg(q);
                    //如果该用户已登录，则返回失败报文，否则返回成功报文
                    if(clientHandlerMap.containsKey(loginUsername)){
                        dos.writeUTF("FAIL");
                    }else{
                        dos.writeUTF("SUCCESS");
                        //将此客户端处理线程的信息添加到clientHandlerMap中
                        clientHandlerMap.put(loginUsername, this);
                        //将现有用户的信息发给新用户
                        StringBuffer msgUserList=new StringBuffer();
                        msgUserList.append("USERLIST");

                        for(String username : clientHandlerMap.keySet()){
                            msgUserList.append(username +"#");
                        }
                        dos.writeUTF(msgUserList.toString());
                        //将新登录的用户信息发给其他用户
                        String msgLogin="LOGIN#"+loginUsername;
                        broadcastMsg(loginUsername,msgLogin);
                        //存储登录的用户名
                        this.username=loginUsername;
                    }
                }
                if(parts[0].equals("LOGOUT")){
                    clientHandlerMap.remove(username);
                    String msgLogout="LOGOUT#"+username;
                    //broadcastMsg(username,msgLogout);
                    isConnected=false;
                    socket.close();
                }

                if(parts[0].equals("TALKTO_ALL")){
                    String msgTalkToAll="TALKTO_ALL#"+username+"#"+parts[1];	 Date  date3=new Date();
                    SimpleDateFormat f2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String d3=f2.format(date3);
                    String p="用户 "+username+"对所有人说"+parts[1]+"\t"+d3;
                    addMsg2(p);
                    broadcastMsg(username,msgTalkToAll);}
                if(parts[0].equals("TALKTO")){
                    ClientHandler clientHandler=clientHandlerMap.get(parts[1]);
                    if(null!=clientHandler){
                        String msgTalkTo="TALKTO#"+username+"#"+parts[2];
                        Date date3=new Date();
                        SimpleDateFormat f2=new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String d3=f2.format(date3);
                        String p2="用户 "+clientHandler+" 对"+username+"说: "+parts[2]+"\t"+d3;
                        addMsg2(p2);

                        clientHandler.dos.writeUTF(msgTalkTo);
                        clientHandler.dos.flush();
                    }
                }
            }catch(IOException e){
                isConnected=false;
                e.printStackTrace();
            }
        }
    }

    /**
     * 将某个用户发来的信息广播个其他用户
     */
    private void broadcastMsg(String fromUsername,String msg) throws IOException{
        for(String toUserName : clientHandlerMap.keySet()){
            if(fromUsername.equals(toUserName)==false){
                DataOutputStream dos=clientHandlerMap.get(toUserName).dos;
                dos.writeUTF(msg);
                dos.flush();
            }
        }
    }
}
    /**
     * 添加消息到文本框
     */
    private void addMsg(String msg) {
        //在文本区添加一条消息，并加上换行
        textAreaRecord.append(msg + "\n");
        textAreaRecord.setCaretPosition(textAreaRecord.getText().length());
    }
}}

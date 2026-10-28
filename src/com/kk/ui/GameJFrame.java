package com.kk.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // JFrame 界面 窗口
    // 规定：GameJFrame这个界面表示的就是游戏的主界面
    // 以后跟游戏相关的所有逻辑都在这个类中
    //创建一个二维数组
    int[][] data = new int[4][4];

    // 记录空白方块二维数组中的位置
    int x = 0;
    int y = 0;

    // 图片路径
    String path = "puzzlegame\\image\\animal\\animal3\\";

    //存储正确的二维数组
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    // 步数
    int count = 0;

    // 创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    // 更换图片的选项对象
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem sport = new JMenuItem("运动");


    public GameJFrame() {
        // 初始化界面
        initJf();
        // 初始化菜单
        initJMenuBar();
        // 初始化数据（打乱图片）
        initData();
        // 初始化图片（显示打乱后的图片）
        initImage();



        // 设置界面的显示，建议写再最后一行
        this.setVisible(true);

    }
    //初始化数据（打乱图片）
    private void initData() {
        //1.定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //2.打乱数组中的数据的顺序
        //遍历数组,得到每一个元素,拿着每一个元素跟随机索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //获取到随机索引
            int index = r.nextInt(tempArr.length);
            //拿着遍历到的每一个数据,跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;

        }


        //4.给二维数组添加数据
        //遍历一维数组tempArr得到每一个元素,把每一个元素依次添加到二维数组当中
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }

    }

    // 初始化图片
    private void initImage() {
        this.getContentPane().removeAll();
        // 返回true显示胜利的图标
        if (victory()) {
            JLabel winLabel = new JLabel(new ImageIcon("puzzlegame\\image\\win.png"));
            winLabel.setBounds(203, 283, 197, 73);
            getContentPane().add(winLabel);
        }

        JLabel stepCount = new JLabel("步数：" + count);
        stepCount.setBounds(50, 30, 100, 20);
        getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int l = 0; l < 4; l++) {
                int num = data[i][l];
                // 创建一个JLabel 的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path +num+ ".jpg"));// 指定图片位置
                jLabel.setBounds(105*l + 83,105*i + 143,105,105);
                // 给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理器添加到界面中
                getContentPane().add(jLabel);
            }
        }

        // 添加背景图片
        JLabel bgLabel = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        bgLabel.setBounds(40, 40, 508, 560);
        getContentPane().add(bgLabel);

        // 刷新页面
        getContentPane().repaint();

    }
    // 初始化/创建菜单
    private void initJMenuBar() {
        // 创建整个的菜单对象
        JMenuBar jmb = new JMenuBar();

        // 创建菜单上的两个选项的对象（功能、关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeImageItem = new JMenu("更换图片");





        // 将条目放到选项里
        functionJMenu.add(changeImageItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        changeImageItem.add(animal);
        changeImageItem.add(girl);
        changeImageItem.add(sport);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        animal.addActionListener(this);
        girl.addActionListener(this);
        sport.addActionListener(this);

        // 将选项添加到菜单上
        jmb.add(functionJMenu);
        jmb.add(aboutJMenu);

        // 放到页面上
        this.setJMenuBar(jmb);
    }
    // 初始化界面
    private void initJf() {
        // 设置界面宽高
        this.setSize(603,680);
        // 设置页面标题
        this.setTitle("拼图单机版");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 取消默认的居中设置
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 按下不松时会调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code  = e.getKeyCode();
        if(code == 65) {
            // 把界面中所有图片删除
            getContentPane().removeAll();
            JLabel allImage = new JLabel(new ImageIcon(path + "all.jpg"));
            allImage.setBounds(84, 143, 420,420);
            getContentPane().add(allImage);

            JLabel bgLabel = new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            bgLabel.setBounds(40, 40, 508, 560);
            getContentPane().add(bgLabel);

            getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否胜利，胜利后不可以在操作
        if (victory()) {
            return;
        }

         // 对 上，下，左，右，进行判断
        // 左：37，上：38，下：40，右：39
        int code = e.getKeyCode();
        if(code == 38) {
            // 控制边界
            if(x == 3) {
                return;
            }
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            count++;
            // 重新加载
            initImage();
        } else if(code == 39) {
            if(y == 0) {
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            count++;
            initImage();

        } else if(code == 37) {
            if(y == 3) {
                return;
            }
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            count++;
            initImage();

        } else if(code == 40) {
            if(x == 0) {
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            count++;
            initImage();
        } else if(code == 65) {
            // 按键 A
            initImage();
        }else if(code == 87) {
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };

            initImage();
        }

    }

    // 比较wim数组中数据是否跟data数组相同
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            closeFun();


        } else  if (obj == reLoginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            // 直接关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            // 创建一个弹窗对象
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            // 设置位置
            jLabel.setBounds(0, 0, 258, 258);
            // 把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            // 弹窗设置大小
            jDialog.setSize(344, 344);
            // 让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            // 让弹窗居中
            jDialog.setLocationRelativeTo(null);
            // 弹窗不关闭无法操作下面的界面
            jDialog.setModal(true);
            // 让弹窗显示出来
            jDialog.setVisible(true);
        } else if (obj == animal) {
            extracted(8,"animal");
        } else if (obj == girl) {
            extracted(13,"girl");
        } else if (obj == sport) {
            extracted(10,"sport");
        }
    }
    // 重置游戏
    private void closeFun() {
        count = 0;
        initData();
        initImage();
    }
    // 随机筛选类型
    private void extracted(int num,String type) {
        Random rand = new Random();
        int rendInt = rand.nextInt(num) + 1;
        path = "puzzlegame\\image\\"+type+"\\"+ type + rendInt +"\\";
        closeFun();
    }
}

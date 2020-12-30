package Test;

import domain.City;
import domain.Country;
import service.CityService;
import service.CountryService;
import service.Impl.CityServiceImpl;
import service.Impl.CountryServiceImpl;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class main extends JFrame {
    public static final int HEIGHT=650;
    public static final int WIDTH=1000;

    static List<String> countryIDMap=new ArrayList<>();
    static {
        countryIDMap.add("France");
        countryIDMap.add("China");
        countryIDMap.add("Japan");
        countryIDMap.add("US");
        countryIDMap.add("United Kingdom");
    }

    private static CityService cityService=new CityServiceImpl();
    private static CountryService countryService=new CountryServiceImpl();

    //GUI界面，时间仓促，未美化，仅实现了基本功能
    public static void main(String[] args) {
        main main=new main();
        main.launchFrame();
    }

    class MyThread extends Thread{
        public void run() {
            while (true) {
                repaint();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void paint(Graphics g) {// 暂未开发
        super.paint(g);
    }


    public void launchFrame(){
        new MyThread().start();
        JFrame frame = new JFrame();
        frame.setTitle("疫情数据查询");
        frame.setBackground(new Color(0,0,0));
        frame.setSize(WIDTH,HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //System.out.println(this.getClass().getClassLoader().getResource("pic\\bg.jpg").getPath().replace("%5c","/"));

        JButton refresh=new JButton("更新数据");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "因为网络延迟，请稍等，暂不要进行操作");
                for (int i = 0; i < countryIDMap.size(); i++) {
                    String name = countryIDMap.get(i);
                    Country country=countryService.getCountry(name);
                    if(country==null){
                        countryService.insertAllCountry();
                        for(Country country1: countryService.getAllCountry()){
                            cityService.insertAll(cityService.getAllCityByWeb(country1));
                        }
                    }else {
                        countryService.updateCountry(country);
                        cityService.updateAllCityByWeb(country);
                    }
                }
                JOptionPane.showMessageDialog(null, "更新成功");
            }
        });
        refresh.setSize(100,50);
        refresh.setLocation(890,0);
        refresh.setFont(new Font("黑体", Font.BOLD, 18));
        refresh.setForeground(new Color(60, 2, 2));
        refresh.setBorderPainted(false);// 不打印边框
        refresh.setBorder(null);// 除去边框
        refresh.setFocusPainted(false);// 除去焦点的框
        refresh.setContentAreaFilled(false);
        frame.add(refresh);

        JTable cInfo=new JTable();
        String[] infoColumns = {  "国家名字", "确诊人数", "康复人数", "死亡人数","首都","所在州","所在地区","缩写"};

        JTable table=new JTable();
        JPanel jp=new JPanel();
        jp.setLayout(null);

        String[] columns = {  "城市名字", "确诊人数", "康复人数", "死亡人数"};
        for (int i = 0; i < countryIDMap.size(); i++) {
            String name=countryIDMap.get(i);
            JButton countryButton=new JButton(name);
            countryButton.setSize(150,50);
            countryButton.setLocation(i*180,0);
            countryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Country country=countryService.getCountry(name);
                    if(country==null){
                        country=countryService.getCountryByWeb(name);
                        countryService.insertAllCountry();
                        for(Country country1: countryService.getAllCountry()){
                            cityService.insertAll(cityService.getAllCityByWeb(country1));
                        }
                    }
                    List<City> cities=cityService.getAllCity(country);

                    DefaultTableModel cmodel = new DefaultTableModel(infoColumns, 0);
                    cmodel.insertRow(0, new String[] {country.getName(), country.getConfirmed() + "", country.getRecovered() + "", country.getDeaths() + "" ,country.getCapital_city(),country.getContinent(),country.getLocation(),country.getAbbreviation()});
                    cInfo.setModel(cmodel);

                    changeInfo(columns,cities,table);
                    table.setSize(980,19*cities.size());
                    jp.setPreferredSize(new Dimension(1000,19*cities.size()));
                }
            });
            countryButton.setFont(new Font("黑体", Font.BOLD, 18));
            countryButton.setForeground(new Color(74, 39, 39));
            countryButton.setBorderPainted(false);// 不打印边框
            countryButton.setBorder(null);// 除去边框
            countryButton.setFocusPainted(false);// 除去焦点的框
            countryButton.setContentAreaFilled(false);
            frame.add(countryButton);
        }
        jp.setLocation(0,40);
        jp.setPreferredSize(new Dimension(1000,450));
        jp.setSize(1000,500);


        JScrollPane jScrollPane_userInfo=new JScrollPane(jp,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(jScrollPane_userInfo);
        jScrollPane_userInfo.setBounds(0, 40, 980, 550);
        jScrollPane_userInfo.setFont(new Font("Dialog", 0, 20));

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(true);
        table.setEnabled(false);
        cInfo.getTableHeader().setReorderingAllowed(false);
        cInfo.setEnabled(false);

        DefaultTableModel cmodel = new DefaultTableModel(infoColumns, 0);
        cInfo.setModel(cmodel);


        table.setOpaque(false);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setOpaque(false);
        table.setDefaultRenderer(Object.class, render);
        table.setModel(model);
        JTableHeader myt = table.getTableHeader();
        //jp.setLayout(null);
        //myt
        jp.add(myt);
        JTableHeader myCt = cInfo.getTableHeader();
        jp.add(myCt);
        myCt.setBackground(new Color(17, 63, 61));
        myCt.setForeground(new Color(137,190,178));
        myCt.setSize(1000,30);
        myCt.setLocation(0,0);
        myCt.setFont(new Font("华文行楷", Font.BOLD, 23));

        myt.setSize(1000,30);
        myt.setLocation(0,50);
        myt.setBackground(new Color(17, 63, 61));
        myt.setForeground(new Color(137,190,178));
        myt.setFont(new Font("华文行楷", Font.BOLD, 23));
        jp.add(table);
        jp.add(cInfo);

        cInfo.setSize(1000,18);
        cInfo.setLocation(0,32);
        table.setSize(1000,500);
        table.setLocation(0,80);

        frame.setVisible(true);
    }

    public void changeInfo(String[] columns,List<City> cities,JTable table){
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for(int i=0;i<cities.size();i++){
            City city=cities.get(i);
            model.insertRow(i, new String[] {city.getName(), city.getConfirmed() + "", city.getRecovered() + "", city.getDeaths() + "" });
        }
        table.setModel(model);
    }



    //测试功能能否正常使用
    /*public static void main(String[] args) {
        CountryService service=new CountryServiceImpl();
        service.insertAllCountry();
        List<Country> list=service.getAllCountry();
        CityService cityService=new CityServiceImpl();
        for (int i = 0; i < list.size(); i++) {
            List<City> cities=cityService.getAllCityByWeb(list.get(i));
            cityService.insertAll(cities);
        }
        service.getAllCountry();
        service.insertAllCountry();
    }*/
}

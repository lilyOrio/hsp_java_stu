package com.lilystu.mhl.view;

import com.lilystu.mhl.utils.Utility;

/**
 * @author lily
 * @version 1.0
 * 主界面
 */
public class MHLView {
    //是否退出主界面
    private boolean loop = true;
    private String key = "";

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    public void mainMenu() {
        while (loop) {
            System.out.println("=========满汉楼==============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.println("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("========登录满汉楼========");
                    System.out.print("输入员工号: ");
                    String empId = Utility.readString(50);
                    System.out.print("输入密  码: ");
                    String pwd = Utility.readString(50);
                    while (loop) {
                        System.out.println("\n===============满汉楼(二级菜单)================");
                        System.out.println("\t\t 1 显示餐桌状态");
                        System.out.println("\t\t 2 预定餐桌");
                        System.out.println("\t\t 3 显示所有菜品");
                        System.out.println("\t\t 4 点餐服务");
                        System.out.println("\t\t 5 查看账单");
                        System.out.println("\t\t 6 结账");
                        System.out.println("\t\t 9 退出满汉楼");
                        System.out.print("请输入你的选择: ");
                        key = Utility.readString(1);
                        switch (key) {
                            case "1":
                                listDiningTable();//显示餐桌状态
                                break;
                            case "2":
                                orderDiningTable();
                                break;
                            case "3":
                                listMenu();
                                break;
                            case "4":
                                orderMenu();
                                break;
                            case "5":
                                listBill();//显示所有账单
                                break;
                            case "6":
                                payBill();
                                break;
                            case "9":
                                loop = false;
                                break;
                            default:
                                System.out.println("你的输入有误，请重新输入");
                                break;
                        }
                    }
                    break;
                case "2":
                    System.out.println("========退出满汉楼========");
                    loop = false;
                    break;
                default:
                    System.out.println("重新输入。。。");
                    break;

            }
        }
    }

    private void payBill() {
    }

    private void listBill() {
    }

    private void orderMenu() {
    }

    private void listMenu() {
    }

    private void orderDiningTable() {
    }

    private void listDiningTable() {
    }
}

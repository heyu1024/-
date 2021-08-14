package com.cxw.datesource.recursion;

/**
 * @author shkstart
 * @date 2020-12-16 22:59
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map=new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }

        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }

        //设置挡板
        map[3][1]=1;
        map[3][2]=1;

//        map[1][2]=1;
//        map[2][2]=1;

        //输出地图
        System.out.println("地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归回溯给小球走路
//        setWay(map,1,1);

        setWay2(map,1,1);

        //输出新的地图，小球走过
        System.out.println("最终地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * 说明：
     * i，j表示从哪个位置开始出发（1，1）
     * 如果小球能到（6，5），说明通路找到
     * 当map[i][j]为0表示该点没有找过，
     * 当为1的时表示墙，
     * 当为2表示通路可以走，
     * 当为3表示该位置已经走过，但是走不通
     * 在走迷宫时，需要确定一个策略：下-右-上-左，如果该走不通，再回溯
     * @param map 地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){//说明通路已经找到
            return true;
        }else {
            if(map[i][j]==0){
                //如果当前这个点还没有走过
                //按照策略 下-右-上-左
                map[i][j]=2;//假定该点是可以走通

                //输出新的地图，小球走过
                System.out.println("---------小球路径----------");
                for (int oo = 0; oo < 8; oo++) {
                    for (int pp = 0; pp <7 ; pp++) {
                        System.out.print(map[oo][pp]+" ");
                    }
                    System.out.println();
                }


                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if(setWay(map,i-1,j)){//向上走
                    return true;
                }else if(setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else {
                //如果mapp[i][j]!+0,可能是1，2，3
                return false;
            }
        }
    }


    //修改找路策略，改成 上-右-下-左
    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5]==2){//说明通路已经找到
            return true;
        }else {
            if(map[i][j]==0){
                //如果当前这个点还没有走过
                //按照策略 上-右-下-左
                map[i][j]=2;//假定该点是可以走通

                //输出新的地图，小球走过
                System.out.println("---------小球路径----------");
                for (int oo = 0; oo < 8; oo++) {
                    for (int pp = 0; pp <7 ; pp++) {
                        System.out.print(map[oo][pp]+" ");
                    }
                    System.out.println();
                }


                if(setWay2(map,i-1,j)){//向上走
                    return true;
                }else if(setWay2(map,i,j+1)){//向右走
                    return true;
                }else if(setWay2(map,i+1,j)){//向下走
                    return true;
                }else if(setWay2(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点是走不通的，是死路
                    map[i][j]=3;
                    return false;
                }
            }else {
                //如果mapp[i][j]!+0,可能是1，2，3
                return false;
            }
        }
    }
}

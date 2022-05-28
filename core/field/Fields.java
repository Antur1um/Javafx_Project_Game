//import java.util.Scanner;
package Field;
public class Tile   
{
    final int HEIGHT = 10;
    final int WIDTH = 30;
    public string skin = "0"; 
    public int side = 0;  
    public int profit = 0; 
};

public class CapturedTile extends Tile // ������ ���� ������� ���� �����
{
    public void CapturedTile(int side)
        {
            this.side = side;
            profit = 1;
            
            if (side == 1)          // � ������� 1 ������� 0(False)
                skin = "#";
            else if (side == 2)    //  � ������� 2 ������� 1(Trues)
                skin = "X";
        }
};

public class Field  // ������� ���� �������, ������ ������ Tile.
{
    public Tile[][] tiles;
    public int h, w;

        Field(int h, int w)
        {
            this.h = h;
            this.w = w;

            Tile[][] tiles = new Tile [h][w];
            for (int i=0; i < h; i++)
                tiles[i] = new Tile[w];
            for (int i=0; i < h; i++)
                for (int j=0; j < w; j++)
                    tiles[i][j] = new Tile();
        }

        int Get_square(bool side)   /// �������� ��������� ������ ������, 0-������� 1-����� 
        {
            int value = 0;

            for (int i=0; i < h ; i++)
                for (int j=0; j < w; j++)
                    if (tiles[i][j].side == side)
                        value++;
                
            return value;
        }

};

///////////******  ���� ������ ********///////////
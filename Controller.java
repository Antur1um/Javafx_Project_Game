package PlayerController;
import java.util.Scanner;
public class PlayerController   
{
    public bool side;                  // 0-�������, 1-�����
        Field fil;                // ���� ����� ��� ���� ������
        CollectionUnit colunt;   // ������ �� ���� ������ ������

        public void PlayerController(bool s, Field fil)
        {
            side = s;
            this.fil = fil;
            colunt = new CollectionUnit();
        }

        bool Check_path(int start_x, int start_y, int end_x, int end_y)  // ��������� ����� �� ����� ���� �� �����
        {
            int[][] grid = grid[HEIGHT][WIDTH];
            for (int i=0; i < HEIGHT ; i++)
            {
                for (int j=0; j < WIDTH; j++)
                {
                    if (fil.tiles[i][j].side == 2)
                        grid[i][j] = -2;
                    else 
                        grid[i][j] = -1;
                }
            }

            int dx[] = {1, 0, -1, 0, -1, 1, -1, 1 };
            int dy[] = {0, 1, 0, -1, 1, 1, -1, -1};
            int len;
            int x, y, k;
            int d = 0;
            bool stop;

            grid[start_y][start_x] = 0;
            do 
            {
                stop = true;
                for (y = 0; y < fil.h; y++)
                    for(x = 0; x < fil.w; x++)
                        if (grid[y][x] == d)
                        {
                            for(k=0; k < 8; k++)
                            {
                                int iy=y + dy[k], ix = x + dx[k];
                                if(iy >= 0 && iy < fil.h && ix >= 0 && ix < fil.w && grid[iy][ix] == -2)
                                {
                                    stop = false;
                                    grid[iy][ix] = d + 1;
                                }
                            }
                        }
                d++;
            } while(!stop && grid[end_y][end_x] == -2);

            if (grid[end_y][end_x] == -2) return false;

            len = grid[end_y][end_x];
            system("pause");

            if( len > 3) return false;  // 3 - ������� ���������� �.k. ��� ������ ����, � �� ���� ������� ����� ������.
            else return true;
        }

        bool Check_zone(int x, int y)  // ��������� ���������� ����� �� �������������� � ����� ����������
        {                             //  � +� �������.(����� ���� ���� �� ������ ������ ����� �������)
            if(fil.tiles[x][y].side == 2)
                return true;
            else
            {
                for (int i=x-1; i <= x+1 && i >= 0 && i<fil.h; i++)
                    for(int j=y-1; j <= y+1 && j >= 0 && j<fil.w; j++)
                        if(fil.tiles[i][j].side == 2)
                            return true;
            }
            return false;
        }

//// �������� �������, ������ ��� ������������� ����� ��������. ////

public void SetColor(int text)
{
   HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
   SetConsoleTextAttribute(hStdOut, (text));
}

void show_field(Field f, CollectionUnit ur, CollectionUnit ub)   /// ��������� ������.
{
  
    for (int i=0; i < f.h ; i++)
    {
        for (int j=0; j < f.w; j++)
        {
            bool v = 1;

            if(v)
            {
                for (int k=0; k < ur.quantity; k++)
                    if(ur.v_unt[k].position_x == j && ur.v_unt[k].position_y == i)
                    {
                        SetColor(4);
                        v = 0;
                        break;
                    }
            
                for (int k=0; k < ub.quantity; k++)
                    if(ub.v_unt[k].position_x == j && ub.v_unt[k].position_y == i)
                    {
                        SetColor(3);
                        //cout<< setw(4) << ub.v_unt[k].skin;
                        v = 0;
                        break;
                    }
            }
            
                    
            if(v)
            {
                SetColor(8);
                if (f.tiles[i][j].side == 2)
                    SetColor(4);
                else if (f.tiles[i][j].side == 1)
                    SetColor(3);
                //cout<< setw(4) << f.tiles[i][j].skin;
            }

        }
    }
}
}

public class Supervisor
{
    public Field fid;
    public PlayerController p1;
    public PlayerController p2;
    Supervisor(Field fid, PlayerController p1, PlayerController p2)
    {
        this.fid = fid;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void menu()
    {
        Scanner input = new Scanner(System.in);
        system("cls");
        show_field(fid, p1.colunt, p2.colunt);
        //<< "1. ������� ����" << endl
        //<< "2. ������� ����" << endl
        //<< "3. ��������� ���" << endl;
        int s;
        //Scanner.input(s);
        if (s == 1)
        {
            Scanner.out.println("���������� X: ");
            int x, y;
            Scanner.input(x);
            Scanner.input(y);

            if (p1.colunt.check_point(x, y))
            {
                int x1, y1;
                Scanner.input(1);
                Scanner.input(y1);

                if (p1.Check_path(x, y, x1, y1) && p1.Check_zone(y1, x1))
                {
                    if(p1.colunt.check_point(x1, y1) )
                    {
                        p1.colunt.merge_unit(x, y, x1, y1);

                    }
                    else
                    {
                        Unit unit_mv = p1.colunt.get_unit(x, y);
                        unit_mv.position_x = x1;
                        unit_mv.position_y = y1;
                        unit_mv.action = 0;

                        if(fid.tiles[y1][x1].side != 2)
                            fid.tiles[y1][x1] = new CapturedTile(2);
                    }
                    
                }
                else
                {
                    system("pause");
                } 
                
            }
            else
            {
                system("pause");
            }                

            menu();
        }
        else if(s == 2)
        {
            int x, y;
            Scanner.input(x);
            Scanner.input(y);

            if (p1.Check_zone(y, x) && !p1.colunt.check_point(x,y))
            {
                
                Unit ut = new Wanderer(x, y);

                if (fid.tiles[y][x].side == 0)
                {
                    ut.action = 0;
                    fid.tiles[y][x] = new CapturedTile(2);
                }
            
                p1.colunt.add_unit(ut);
            }
            else if (p1.Check_zone(y, x) && p1.colunt.check_point(x,y))
            {
                Unit ut = new Wanderer(-1, -1);  // ������ ����� ��� ����, ��� ��������
                p1.colunt.add_unit(ut);   
                p1.colunt.merge_unit(-1, -1, x, y);
            }

            menu();
            
        }
        else if(s == 3)
        {
            menu();

        }
        else menu();

    }
}

public class Main
{
    public static void main(String[] args) 
    {
        //system("chcp 1251"); system("cls");
    
        Field fid = new Field(10, 30);
        fid.tiles[5][7] = new CapturedTile(2);
        fid.tiles[4][7] = new CapturedTile(2);
        PlayerController p1 = new PlayerController(2, fid);
        PlayerController p2 = new PlayerController(1, fid);
    
    
        Supervisor visor = new Supervisor(fid, p1, p2);
        
        visor.menu();
    }
}




/*

    ������ ���������� �������� ����� ��������.

    Field* fid = new Field(10, 30);
    delete fid.tiles[1][1];
    fid.tiles[1][1] = new CapturedTile(0);


*/
/*

    Unit* unt1 = new Wanderer(1, 2);
    Unit* unt2 = new Spearman(1,1);
    Unit* unt3 = new Guardian(1,0);
    CollectionUnit* collunt = new CollectionUnit;
    collunt.add_unit(unt1);
    collunt.add_unit(unt2);
    collunt.add_unit(unt3);

    Unit* unt1b = new Wanderer(4, 4);
    Unit* unt2b = new Spearman(4,2);
    Unit* unt3b = new Guardian(5,5);
    CollectionUnit* collunt1 = new CollectionUnit;
    collunt1.add_unit(unt1b);
    collunt1.add_unit(unt2b);
    collunt1.add_unit(unt3b);

    delete fid.tiles[4][20];
    fid.tiles[4][20] = new CapturedTile(2);
    delete fid.tiles[1][20];
    fid.tiles[1][20] = new CapturedTile(2);
    delete fid.tiles[5][20];
    fid.tiles[2][19] = new CapturedTile(2);
    delete fid.tiles[2][19];
    fid.tiles[3][19] = new CapturedTile(2);
    delete fid.tiles[3][19];

    PlayerController* p1 = new PlayerController(0, fid);
    

    show_field(fid, collunt, collunt1);

    cout<< p1.Check_path(20, 4, 19, 0);

*/
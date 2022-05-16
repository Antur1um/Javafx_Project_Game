package Unit;

public class Unit
{
    public string skin;
    public int position_x, position_y, rank; 
    public bool side;
    public bool action = 1;  // 0-������, 1-�� ������

    public void Unit(int x, int y)  // ��������� ���������� ����� ���������� ����
    {                  //������� ���� ������ ����� ��� �� ����� ���������� �� � ������ ��� ��������.
        position_x = x;
        position_y = y;
    }
};

public class Wanderer extends Unit  
{
    public void Wanderer(int x, int y)
    {
        super.Unit(x,y);
        skin = "@";
        rank = 1;
    }

};

class Spearman extends Unit 
{
    public Spearman(int x, int y)
        {
            super(x,y);
            skin = "^";
            rank = 2;
        }
     
};

class Guardian extends Unit
{
    public Guardian(int x, int y)
        {
            super(x,y);
            skin = "%";
            rank = 3;
        }
        
};

class CollectionUnit  // ������, ��������� ��� ����� ������.
{
    public int quantity=0;
        vector<Unit> v_unt;    

        void add_unit(Unit new_unit)    // ���������� ��������
        {
            v_unt.push_back(new_unit);
            quantity = v_unt.size();            
        }

        Unit get_unit(int x, int y)
        {
            for (int i=0; i < quantity; i++)
                if(v_unt[i].position_x == x && v_unt[i].position_y == y)
                    return v_unt[i];

            return null;
        }

        void del_unit(int x, int y)       // �������� �� ����������� 
        {
            for (int i=0; i < quantity; i++)
                if(v_unt[i].position_x == x && v_unt[i].position_y == y)
                    v_unt.erase(v_unt.begin()+i);  
            quantity = v_unt.size();
        }

        bool check_point(int x, int y)   // ��������� ���� �� �� ������ ����������� ���� �� ��� ������.
        {
            for (int i=0; i < quantity; i++)
                if(v_unt[i].position_x == x && v_unt[i].position_y == y)
                    return true;
            return false;
        }

        void merge_unit(int x1, int y1, int x2, int y2)  
        {
            Unit unit1 = get_unit(x1, y1);  // unit1 ����� �� unit2
            Unit unit2 = get_unit(x2, y2);

            if (unit1.rank == unit2.rank && unit1.rank != 3)
            {
                bool action = unit2.action;     

                if(unit1.rank == 1)
                {
                    unit2 = new Spearman(x2, y2);  
                }
                else if(unit1.rank == 2)
                {
                    unit2 = new Guardian(x2, y2);  
                }
                unit2.action = action;
                del_unit(x1, y1);
            }
            else return; // ����� ����������� ������ �� ������

        }

};
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayerDB 
{

    private static ObservableList<Player> getPlayersList(ResultSet rSet) 
    {
        ObservableList<Player> pList = FXCollections.observableArrayList();
        try
        {
            while(rSet.next()) 
            {
                Player player = new Player(rSet.getString("NICKNAME"), rSet.getInt("SCORE"), rSet.getInt("ID"));
                pList.add(player);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return pList;
    }

    public static ObservableList<Player> getAll() throws Exception 
    {
        String query = "SELECT NICKNAME, SCORE, ID FROM RATING;";
        try 
        {
            ResultSet rSet = DBUtil.dbExecuteQuery(query);
            ObservableList<Player> pList = getPlayersList(rSet);
            pList.sort(new Comparator<Player>() 
            {
                @Override
                public int compare(Player o1, Player o2) {
                    if (o1.getScore() == o2.getScore()) return 0;
                    else if (o1.getScore() > o2.getScore()) return -1;
                    else return 1;
                }
            });
            return pList;
        } 
        catch (Exception e) {
            throw e;
        }
    }
}
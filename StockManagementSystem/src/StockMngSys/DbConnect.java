
package StockMngSys;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DbConnect {
    public static Connection connect(){
    
        Connection conn = null;
        
            try {
            Class.forName("org.sqlite.JDBC");
            conn=DriverManager.getConnection("jdbc:sqlite:mydatabase.sqlite");
           // Class.forName("org.sqlite.JDBC");
         //   conn=DriverManager.getConnection("jdbc:sqlite:SellList.sqlite");
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
        }
        return conn;
    }

  
    
}

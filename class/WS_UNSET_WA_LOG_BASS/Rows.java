package wa_unset_wa;

import java.util.Vector;

public class Rows {
    private Vector<Row> row ; 
    private Row result =null;
    
    public Rows() {
        
    }

    public void setRow(Vector<Row> row) {
        this.row = row;
    }
    
    public Row addRow() { 
   
            Row r = new Row(); 
 
            if (row== null) { 

               row = new Vector<Row>(); 
  
            } 
  
            row.add(r); 
  
            return row.lastElement(); 
   
        } 
     
    public void addRow(Row r) { 
     
            if ( row == null) { 
     
                row= new Vector<Row>(); 
    
            } 
    
            row.add(r); 
        }

    public Vector<Row> getRow() {
        if ( row == null) { 
        
            row= new Vector<Row>(); 
        
        } 
        
        return row;
    }

    public void setResult(Row result) {
        this.result = result;
    }

    public Row getResult() {
        return result;
    }
    
}
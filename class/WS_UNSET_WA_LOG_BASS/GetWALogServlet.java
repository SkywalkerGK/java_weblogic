package wa_unset_wa;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Vector;

import javax.jws.WebService;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetWALogServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

   
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        String var0 = "";
        String var1 = "";
        Vector<Row> row;
        Rows echo_return = new Rows();
        Row echo_return_line_row = new Row();
        try {
            var0 = request.getParameter("Username");
            GetWALog q = new GetWALog();
            if(! "null".equals(""+var0)){
                        echo_return = q.Invoke(var0);
                        row = echo_return.getRow();
                        response.setContentType(CONTENT_TYPE);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");
                        StringBuilder return_format_json = new StringBuilder();
                        PrintWriter out = response.getWriter();
                        if(row.size() != 0){    
                            return_format_json.append("{").append('\n');
                            for(int i =0; i < row.size(); i++){
                                echo_return_line_row = row.get(i); 
                                return_format_json.append("[").append('\n');
                                return_format_json.append("\" username \":\""+ echo_return_line_row.get_value_username() +"\",").append('\n');
                                return_format_json.append("\" ro \":\""+ echo_return_line_row.get_value_ro() +"\",").append('\n');
                                return_format_json.append("\" paydue \":\""+ echo_return_line_row.get_value_paydue() +"\",").append('\n');
                                return_format_json.append("\" group_id \":\""+ echo_return_line_row.get_value_group_id() +"\",").append('\n');
                                return_format_json.append("\" date_alert \":\""+ echo_return_line_row.get_value_date_alert() +"\",").append('\n');
                                return_format_json.append("\" url \":\""+ echo_return_line_row.get_value_url() +"\",").append('\n');
                                return_format_json.append("\" comments \":\""+ echo_return_line_row.get_value_comments() +"\",").append('\n');
                                return_format_json.append("\" lastdate \":\""+ echo_return_line_row.get_value_lastdate() +"\",").append('\n');
                                return_format_json.append("\" useroper \":\""+ echo_return_line_row.get_value_useroper() +"\",").append('\n');
                                return_format_json.append("\" desclog \":\""+ echo_return_line_row.get_value_desclog() +"\"").append('\n');
                                return_format_json.append("]").append('\n');           
                            }
                            return_format_json.append("}").append('\n');
                        }
                        out.println(return_format_json);
                        out.close();                
    
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}

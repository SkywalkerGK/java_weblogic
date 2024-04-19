package wa_unset_wa;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.StringTokenizer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import oracle.jdbc.OraclePreparedStatement;


public class GetWALog {
    private InitialContext context = null;
    private DataSource jdbcURL = null;
    
    public GetWALog()
    {
            try {
                   context = new InitialContext();
//                   jdbcURL = (DataSource) context.lookup("jdbc/OracleCoreDS_WebPush");
                   jdbcURL = (DataSource) context.lookup("jdbc/Oraclecodetestjson1");
           }
                catch(NamingException e)
            {
                  e.printStackTrace();
            }
    }
      
    /**
     * 
     * @webmethod 
     */
    @WebMethod
     public Rows Invoke (@WebParam(name="Username") String Username) 
    {
          //String IPAddress_T = IPAddress.trim();
          String output = "-";
          Rows rows = new Rows();
          //String output[] = new String[3];
          if ( ! Username.equals("") ) {
                Connection conn = null;
                OraclePreparedStatement stmt = null;
                try {
                          conn = jdbcURL.getConnection();
                          //String qry = "select username from tt_radius.tbl_acct where acctstoptime=to_date('01-01-1800 00:00:00','DD-MM-YYYY HH24:MI:SS') and framedipaddress = :framedipaddress ";
//                            String  qry = "select  zz.username,aa.zone_id as RO,zz.wa_status as PAYDUE,aa.group_id,zz.date_alert,aa.url, ";
//                            qry  = qry  + "( CASE when aa.comments in ('bcs_offline','bcs_disconnect') Then 'bcs_payment' else aa.comments end) COMMENTS ";
//                            qry  = qry  + ",aa.lastdate,bb.useroper,bb.desclog ";
//                            qry  = qry  + "from tt_mims.SCH_TMP_WEBLAERT_ONE_MONTH zz ";
//                            qry  = qry  + "full join tt_radius.tbl_user_job_history aa on zz.username=aa.username ";
//                            qry  = qry  + "full join tt_helpdesk.useroper_actlog_wa bb on aa.username=bb.maxnetuser and trunc(aa.lastdate)=trunc(bb.datop)";
//                            qry  = qry  + "where zz.username = :username order by zz.date_alert desc,aa.lastdate desc";
                        String  qry = "SELECT zz.username ,zz.zone_id as RO ,nvl(zz.wa_status,'-') as PAYDUE ,zz.group_id ,zz.date_alert ";
                                qry  = qry  + ",( Case when (aa.url is null or aa.url='') THEN 'http://helpdesk.triplet.co.th/helpdesk' else nvl(aa.url,'-') end) userch ";
                                qry  = qry  + ",( Case when aa.comments in ('bcs_offline','bcs_disconnect') Then 'bcs_payment' ";
                                qry  = qry  + "when (aa.comments is null or aa.comments = '') Then bb.USERACTION else nvl(aa.comments,'-') end) COMMENTS ";
                                qry  = qry  + ",(Case when (aa.lastdate is null or aa.lastdate='') Then bb.datop else aa.lastdate end) lastdate ";
                                qry  = qry  + ",(CASE WHEN aa.comments in ('bcs_offline','bcs_disconnect') THEN '-' ELSE nvl(bb.useroper,'-') END) useroper ";
                                qry  = qry  + ",(CASE WHEN aa.comments in ('bcs_offline','bcs_disconnect') THEN '-' ELSE nvl(bb.desclog,'-') END) desclog ";
                                qry  = qry  + "FROM tt_mims.SCH_TMP_WEBLAERT_ONE_MONTH zz ";
                                qry  = qry  + "full join (select * from tt_radius.tbl_user_job_history) aa on zz.username=aa.username ";
                                qry  = qry  + "and aa.lastdate between date_alert and zz.date_alert+1 full join tt_helpdesk.useroper_actlog_wa bb on zz.username=bb.maxnetuser ";
                                qry  = qry  + "and bb.datop between zz.date_alert  and zz.date_alert+1 where zz.username = :username and zz.date_alert >= SYSTIMESTAMP - INTERVAL '30' DAY ";
                                qry  = qry  + "order by zz.date_alert desc, aa.lastdate desc ";

                          stmt  = (OraclePreparedStatement) conn.prepareStatement(qry);
                          stmt.setStringAtName("username", Username);
                          ResultSet value_query = stmt.executeQuery();
//                          rs.next();  
                            
                            
                            while(value_query.next()){
                                Row Ans_value = rows.addRow();
                                Ans_value.set_type_username(value_query.getString(1));
                                Ans_value.set_type_ro(value_query.getString(2));
                                Ans_value.set_type_paydue(value_query.getString(3));
                                Ans_value.set_type_group_id(value_query.getString(4));
                                Ans_value.set_type_date_alert(value_query.getString(5));
                                Ans_value.set_type_url(value_query.getString(6));
                                Ans_value.set_type_comments(value_query.getString(7));
                                Ans_value.set_type_lastdate(value_query.getString(8));
                                Ans_value.set_type_useroper(value_query.getString(9));
                                Ans_value.set_type_desclog(value_query.getString(10));       
                            }
                    
                    
//                        output = "\n             <username>" + rs.getString(1) + "</username>\n ";
//                        output = output + "             <ro>" + rs.getString(2) + "</ro>\n ";
//                        output = output + "             <paydue>" + rs.getString(3) + "</paydue>\n ";
//                        output = output + "             <groupid>" + rs.getString(4) + "</groupid>\n ";
//                        output = output + "             <date_alert>" + rs.getString(5) + "</date_alert>\n ";
//                        output = output + "             <url>" + rs.getString(6) + "</url>\n";
//                        output = output + "             <comment>" + rs.getString(7) + "</comment>\n";
//                        output = output + "             <lastdate>" + rs.getString(8) + "</lastdate>\n";
//                        output = output + "             <operation>" + rs.getString(9) + "</operation>\n";
//                        output = output + "             <desclog>" + rs.getString(10) + "</desclog>\n";
                    
                          try {
                            value_query.close();
                          } catch (SQLException e)
                          {
                            value_query = null;
                          }
                          stmt.close();
                          conn.close();
                } catch (Exception ex3) {
                          ex3.printStackTrace();
                }
                finally 
                {
                       try {
                                if(stmt != null) { stmt.close(); }
                                if (conn != null) {   conn.close();   }
                        }
                        catch (SQLException e) {
                                    stmt = null;
                                    conn = null;
                                    e.printStackTrace();
                        }
                }
          }
         return rows;             
    } // end function
}

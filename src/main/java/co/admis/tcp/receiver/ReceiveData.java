///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.admis.tcp.receiver;
//
//import static co.admis.config.ServerConfiguration.IP_WHITE_LIST_TCP;
//import co.admis.controller.HibernateUtil;
//import co.admis.model.DeviceConfigure;
//import co.admis.model.IpList;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//
///**
// *
// * @author dell
// */
//@WebListener
//public class ReceiveData  implements ServletContextListener {
//    ServerSocket serverSocket;
//    Socket socket;
//    
//    @Override
//   public void contextInitialized(ServletContextEvent contextEvent) {
//        System.out.println("TCP Started");
//        List<DeviceConfigure> deviceConfigures =  getDeviceConfigure();
//        for(DeviceConfigure dc:deviceConfigures){
//            try{
//                    // EXECUTE YOUR CODE HERE 
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            openTCPPortAndReceiveData(dc.getPort());
//                        } 
//                    }.start();
//            }catch(Exception ex ){
//                System.out.println(ex);
//            }
//        }
//   }
//
//   @Override
//   public void contextDestroyed(ServletContextEvent contextEvent) {
//        try {
//            System.out.println("TCP Stoped");
//            serverSocket.close();
//            socket.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ReceiveData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
//   
//  private void openTCPPortAndReceiveData(int port){
//     try {
//         serverSocket = new ServerSocket(port, 10);
//         
//         while(true){
//             socket = serverSocket.accept();
//             // Get Ip address
//             InetSocketAddress sockaddr = (InetSocketAddress)socket.getRemoteSocketAddress();
//             InetAddress inaddr = sockaddr.getAddress();
//             Inet4Address in4addr = (Inet4Address)inaddr;
//             byte[] ip4bytes = in4addr.getAddress(); // returns byte[4]
//             String ip4string = in4addr.toString();
//             System.out.println(ip4string);
//             if(checkIp(ip4string)){
//                InputStream is = socket.getInputStream();
//                OutputStream os = socket.getOutputStream();
//
//                // Receiving
//                byte[] lenBytes = new byte[4];
//                is.read(lenBytes, 0, 4);
//                int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
//                        ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
//                byte[] receivedBytes = new byte[len];
//                is.read(receivedBytes, 0, len);
//                String received = new String(receivedBytes, 0, len);
//                System.out.println(received);
//                ObjectMapper mapper = new ObjectMapper();
//                ReceivedData rd = mapper.readValue(received, ReceivedData.class);
//                System.out.println(rd);
//                String response = "";
//                if(rd!=null){
//                    if(rd.getMachineName().equalsIgnoreCase("micros60")){
//                       response = updatePatientTest(rd);
//                   }else{
//                       response = "Error ! Incorrect Values";
//                   }
//                }else{
//                    response = "Error ! Incorrect Values";
//                }
//
//                // Sending
//                String toSend = response;
//                byte[] toSendBytes = toSend.getBytes();
//                int toSendLen = toSendBytes.length;
//                byte[] toSendLenBytes = new byte[4];
//                toSendLenBytes[0] = (byte)(toSendLen & 0xff);
//                toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
//                toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
//                toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
//                os.write(toSendLenBytes);
//                os.write(toSendBytes);
//             }
//         }
//     } catch (IOException ex) {
//         System.out.println(ex);
//     }
//  }
//   
//  String updatePatientTest(ReceivedData rd){
//      boolean verify = false;
//      try{
//      PatientData pd = getPatientById(rd.getPatientId());
//      if(pd==null){
//          return "Warning ! Patient id is incorrect";
//      }
//      List<PatientTest> tests = getPatientTestsByStatus(pd.getId(), "done");
//      if(tests!= null && tests.size()>0){
//          for(PatientTest t: tests){
//            if(t.getTest().getName().equalsIgnoreCase(rd.getTestName())){
//                for(TestParameter tp: t.getTest().getTestParameters()){
//                    for(MachineData md: rd.getData()){
//                        if(tp.getName().equalsIgnoreCase(md.getName()) || tp.getName().equalsIgnoreCase(md.getFullName())){
//                            tp.setResult(md.getValue());
//                            verify = true;
//                        }
//                    }
//                }
//                if(verify){
//                    t.setStatus("reported");
//                    updatePatientTest(t);
//                    return "Result Updated";
//                }else{
//                    return "Try Again";
//                }
//            }
//        }
//      }
//      return "Warning ! Test Not Found";
//      }catch(Exception ex){
//          System.out.println(ex);
//          return "Error";
//      }
//  }
//  
//  public PatientData getPatientById(int patientId) {
//        try{
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            Query query = session.createQuery("from PatientData patient where patient.patientId = ?0");
//            query.setInteger(0, patientId);
//            PatientData patient = (PatientData) query.uniqueResult();
//            session.getTransaction().commit();
//            session.close();
//            return patient;
//        }catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
//  }
//  
//  public List<PatientTest> getPatientTestsByStatus(int patientId, String status) {
//        try{
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            Query query = session.createQuery("from PatientTest patientTest where patientTest.patientId = ?0 and patientTest.status = ?1");
//            query.setInteger(0, patientId);
//            query.setString(1, status);
//            List<PatientTest> patientTest = query.list();
//            session.getTransaction().commit();
//            session.close();
//            return patientTest;
//        }catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }
//  
//  public PatientTest updatePatientTest(PatientTest patientTest) {
//        try{
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            session.update(patientTest);
//            session.getTransaction().commit();
//            session.close();
//            return patientTest;
//        }catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }
//  
//  private boolean checkIp(String ip){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try{
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            Query q = session.createQuery("FROM IpList ip where ip.ip = ?0 and ip.type = ?1");
//            q.setString(0, ip);
//            q.setString(1, "tcp");
//            q.setMaxResults(1);
//            IpList data = (IpList)q.uniqueResult();
//            session.getTransaction().commit();
//            session.close();
//            return data!=null || IP_WHITE_LIST_TCP==0;
//        }catch(Exception e){
//            session.close();
//            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
//  
//    public List<DeviceConfigure> getDeviceConfigure() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try{
//            session.beginTransaction();
//            @SuppressWarnings("unchecked")
//            Query q = session.createQuery("FROM DeviceConfigure obj");
//            List<DeviceConfigure> data = q.list();
//            session.getTransaction().commit();
//            session.close();
//            return data;
//        }catch(Exception e){
//            session.close();
//            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
//
//}

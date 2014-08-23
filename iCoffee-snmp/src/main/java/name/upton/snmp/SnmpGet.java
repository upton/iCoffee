package name.upton.snmp;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpGet {
    private static int version = SnmpConstants.version2c;
    private static String protocol = "udp";
    private static int port = 161;

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        new SnmpGet().snmpGet("10.28.170.58", "public", "1.3.6.1.2.1.6.2");
    }

    private void snmpGet(String ip, String community, String oid) {

        String address = protocol + ":" + ip + "/" + port;
        CommunityTarget target = SnmpUtil.createCommunityTarget(address, community, version, 1000L, 1);
        DefaultUdpTransportMapping udpTransportMapping = null;
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();
            // pdu.add(new VariableBinding(new OID(new int[]
            // {1,3,6,1,2,1,1,2})));
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            udpTransportMapping = new DefaultUdpTransportMapping();
            udpTransportMapping.listen();
            snmp = new Snmp(udpTransportMapping);

            // 发送同步消息
            ResponseEvent response = snmp.send(pdu, target);
            System.out.println(Thread.currentThread().getName() + "PeerAddress:" + response.getPeerAddress());
            PDU responsePdu = response.getResponse();

            if (responsePdu == null) {
                System.out.println(ip + ":Request time out");
            } else {
                Vector vbVect = responsePdu.getVariableBindings();
                System.out.println("vb size:" + vbVect.size());
                if (vbVect.size() == 0) {
                    System.out.println(" pdu vb size is 0 ");
                } else {
                    Object obj = vbVect.firstElement();
                    VariableBinding vb = (VariableBinding) obj;
                    System.out.println(vb.getOid() + " = " + vb.getVariable());
                }

            }
        } catch (Exception e) {
            System.out.println("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
            if (udpTransportMapping != null) {
                try {
                    udpTransportMapping.close();
                } catch (IOException ex2) {
                    udpTransportMapping = null;
                }
            }
        }
    }
}

package main;

import okhttp3.Dns;
import qiniu.happydns.DnsClient;
import qiniu.happydns.IResolver;
import qiniu.happydns.http.DnspodFree;
import qiniu.happydns.local.Resolver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OkHttpDns implements Dns {
    private DnsClient dnsClient;
    public OkHttpDns(){
        try{
            IResolver[] iResolvers = new IResolver[2];
            iResolvers[0] = new DnspodFree();
            iResolvers[1] = new Resolver(InetAddress.getByName("119.29.29.29"));
            dnsClient = new DnsClient(iResolvers);
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException{
        System.out.println("HttpDns lookup=="+hostname);
        if(dnsClient==null){
            return Dns.SYSTEM.lookup(hostname);
        }

        try{
            String[] ips = dnsClient.query(hostname);

            if(ips == null || ips.length == 0){
                return Dns.SYSTEM.lookup(hostname);
            }
            List<InetAddress> result = new ArrayList<InetAddress>();
            for(String ip : ips){
                System.out.println("get Dns=="+ip);
                result.addAll(Arrays.asList(InetAddress.getAllByName(ip)));
            }
            return result;
        } catch (IOException e){
            e.printStackTrace();
        }

        return Dns.SYSTEM.lookup(hostname);
    }
}

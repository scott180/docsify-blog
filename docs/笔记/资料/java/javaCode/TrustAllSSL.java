package com.sudytech.auth.basic.ids.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 信任所有ssl证书，无需验证
 * @author Administrator
 *
 */
public class TrustAllSSL {
	
	/**
	 * 信任所有ssl
	 * @param httpclient
	 */
	 public static void enableSSL(DefaultHttpClient httpclient){  
	        //调用ssl  
	         try {  
	                SSLContext sslcontext = SSLContext.getInstance("TLS");  
	                sslcontext.init(null, new TrustManager[] { truseAllManager }, null);  
	                SSLSocketFactory sf = new SSLSocketFactory(sslcontext);  
	                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	                Scheme https = new Scheme("https", sf, 443);  
	                httpclient.getConnectionManager().getSchemeRegistry().register(https);  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	    }  
	    /** 
	     * 重写验证方法，取消检测ssl 
	     */  
	    private static TrustManager truseAllManager = new X509TrustManager(){  
	  
	        public void checkClientTrusted(  
	                java.security.cert.X509Certificate[] arg0, String arg1)  
	                throws CertificateException {  
	            // TODO Auto-generated method stub  
	              
	        }  
	  
	        public void checkServerTrusted(  
	                java.security.cert.X509Certificate[] arg0, String arg1)  
	                throws CertificateException {  
	            // TODO Auto-generated method stub  
	              
	        }  
	  
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
	            // TODO Auto-generated method stub  
	            return null;  
	        }  
	          
	    }; 
	    
	    /**
	     * 获取 HttpClient (org.apache.http.client.HttpClient);
	     * @return
	     * @throws Exception
	     */
	    public static CloseableHttpClient buildSSLCloseableHttpClient()  
	            throws Exception {  
	        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,  
	                new TrustStrategy() {  
	                    // 信任所有  
	                    public boolean isTrusted(X509Certificate[] chain,  
	                            String authType) throws CertificateException {  
	                        return true;  
	                    }  
	                }).build();  
	        // ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。  
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
	                sslContext, new String[] { "TLSv1" }, null,  
	                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	        return HttpClients.custom().setSSLSocketFactory(sslsf).build();  
	    } 
	    
}

//package com.kokoharry.site.util;
//
//import net.sourceforge.tess4j.Tesseract1;
//import net.sourceforge.tess4j.TesseractException;
//import org.apache.http.Consts;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class CodeProcess {
//
//	private static final String fileName = "captcha.jpg";
//
//	//根据实际验证码的色彩来判断哪里要变成白色
//	public static int isWhite(int colorInt) {
//		Color color = new Color(colorInt);
//		if (color.getRed() + color.getGreen() + color.getBlue() > 300) {  //根据实际情况修改这里的300
//			return 1;
//		}
//		return 0;
//	}
//
//	//根据实际验证码的色彩来判断哪里要变成黑色
//	public static int isBlack(int colorInt) {
//		Color color = new Color(colorInt);
//		if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {  //根据实际情况修改这里的300
//			return 1;
//		}
//		return 0;
//	}
//
//	//扫描验证码所有的像素颜色过滤掉不要的颜色
//	public static BufferedImage removeBackgroud4Tone(String picFile)
//			throws Exception {
//		BufferedImage img = ImageIO.read(new File(picFile));
//		int width = img.getWidth();
//		int height = img.getHeight();
//		for (int x = 0; x < width; ++x) {
//			for (int y = 0; y < height; ++y) {
//				if (isWhite(img.getRGB(x, y)) == 1) {
//					img.setRGB(x, y, Color.WHITE.getRGB());
//				} else {
//					img.setRGB(x, y, Color.BLACK.getRGB());
//				}
//			}
//		}
//		return img;
//	}
//
//
//
//	//将过滤都的黑白图片保存
//	public static void handleImg(String file) {
//		BufferedImage img;
//		try {
//			img = removeBackgroud4Tone(file);
//
//			ImageIO.write(img, "JPG", new File(fileName));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//
//
//	//识别验证码，这里如果验证码处理不好识别率很低
//	public static String identifyCode() {
//		handleImg(fileName);
//		Tesseract1 instance = new Tesseract1();
//		File imageFile = new File(fileName); // instance.setLanguage("chi_sim");
//		String result = null;
//		try {
//			result = instance.doOCR(imageFile);
//		} catch (TesseractException e) { // TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//	//获得网站的验证码及COOKIE
//	public static HashMap<String, String> getCode(String uri) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		try {
//			URL url = new URL(uri);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET"); // 以Post方式提交表单，默认get方式
//			String cookie = con.getHeaderField("set-cookie");
//			ImageIO.write(ImageIO.read(con.getInputStream()), "JPG", new File(
//					fileName));
//			String code = identifyCode();
//			map.put("cookie", cookie); //cookie=JSESSIONID=16yjdmlj4l1g81jqe39c41nooc;
//			map.put("code", code);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return map;
//	}
//
//	//模拟登录
//	public static void postForm(String uri,String username,String password, String code, String cookie) {
//		String result = null;
//
//		try {
//			URL url = new URL(uri);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
//			con.setDoInput(true);
//			con.setDoOutput(true);
//			con.setUseCaches(false); // post方式不能使用缓存
//			con.setRequestProperty("Cookie", cookie);
//			con.setRequestProperty("Connection", "Keep-Alive");
//			con.setRequestProperty("Charset", "UTF-8");
//
//			String BOUNDARY = "----------" + System.currentTimeMillis();
//			con.setRequestProperty("Content-Type",
//					"multipart/form-data; boundary=" + BOUNDARY);
//			// 请求正文信息
//			// 第一部分：
//			StringBuilder sb = new StringBuilder();
//			sb.append("--"); // 必须多两道线
//								// 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，不懂的可以看看http
//								// 协议头
//			sb.append(BOUNDARY);
//			sb.append("\r\n");
//			sb.append("Content-Disposition: form-data;name=\"username\" \r\n\r\n"); // 这里是参数名，参数名和值之间要用两次
//			sb.append(username + "\r\n"); // 参数的值
//
//			sb.append("--"); // 必须多两道线
//			sb.append(BOUNDARY);
//			sb.append("\r\n");
//			sb.append("Content-Disposition: form-data;name=\"password\" \r\n\r\n");
//			sb.append(password + "\r\n");
//
//			sb.append("--"); // 必须多两道线
//			sb.append(BOUNDARY);
//			sb.append("\r\n");
//			sb.append("Content-Disposition: form-data;name=\"code\" \r\n\r\n");
//			sb.append(code + "\r\n");
//
//			byte[] head = sb.toString().getBytes("utf-8");
//			// 获得输出流
//			OutputStream out = new DataOutputStream(con.getOutputStream());
//			// 输出表头
//			out.write(head);
//			// 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
//			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
//			out.write(foot);
//			out.flush();
//			out.close();
//			StringBuffer buffer = new StringBuffer();
//			BufferedReader reader = null;
//			// 定义BufferedReader输入流来读取URL的响应
//			reader = new BufferedReader(new InputStreamReader(
//					con.getInputStream(), "utf-8"));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			if (result == null) {
//				result = buffer.toString();
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(result);
//	}
//
//
//	//模拟登录方式2
//	public static void postForm2(String uri,String username,String password,String code,String cookie){
//		try {
//			CloseableHttpClient httpclient = HttpClients.createDefault();
//			HttpClientContext context = HttpClientContext.create();
//			HttpPost httpPost = new HttpPost(uri);
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//	        nvps.add(new BasicNameValuePair("username", username));
//	        nvps.add(new BasicNameValuePair("password", password));
//	        nvps.add(new BasicNameValuePair("code", code));
//	        httpPost.setHeader("Cookie", cookie);
//	        httpPost.setHeader("Host", "newhome.400gb.com");
//	        httpPost.setHeader("Origin", "http://newhome.400gb.com");
//	        httpPost.setHeader("Referer", "http://newhome.400gb.com/?item=files&action=index");
//	        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");
//	        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//	        CloseableHttpResponse response = httpclient.execute(httpPost, context);
//	        String result="";
//	        StringBuffer buffer = new StringBuffer();
//			BufferedReader reader = null;
//			// 定义BufferedReader输入流来读取URL的响应
//			reader = new BufferedReader(new InputStreamReader(
//					 response.getEntity().getContent(), "utf-8"));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			result = buffer.toString();
//			System.out.println(result);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	//根绝uri和cookie 下载整个页面
//	public static void getPage(String uri,String cookie) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		try {
//			URL url = new URL(uri);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET"); // 以Post方式提交表单，默认get方式
//			con.setRequestProperty("Cookie", cookie);
//			String result="";
//	        StringBuffer buffer = new StringBuffer();
//			BufferedReader reader = null;
//			// 定义BufferedReader输入流来读取URL的响应
//			reader = new BufferedReader(new InputStreamReader(
//					con.getInputStream(), "utf-8"));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			result = buffer.toString();
//			System.out.println(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//
//		//System.out.println(CodeProcess.getCode("换成获得验证码的地址"));//cookie=JSESSIONID=16yjdmlj4l1g81jqe39c41nooc;
//		//降上面的cookie 传入下面方面中即可
//		//CodeProcess.postForm("登录地址","admin","123123","ppbuc", "JSESSIONID=ot1hycqeqc8x3hbner0fehsr");
//		//CodeProcess.postForm2("登录地址","admin","123123","TFCVL", "JSESSIONID=xb47iet45b9mg9xyz0jqcn0y");
//		//CodeProcess.getPage("下载页面的地址", "JSESSIONID=ot1hycqeqc8x3hbner0fehsr");
//	}
//}